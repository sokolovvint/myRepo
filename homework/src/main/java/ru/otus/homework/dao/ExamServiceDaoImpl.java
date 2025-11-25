package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.dto.Question;
import ru.otus.homework.service.PrintService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ExamServiceDaoImpl implements ExamServiceDao {

    private Resource resource;

    private PrintService printService;

    public ExamServiceDaoImpl(ResourceLoader resourceLoader,
                              PrintService printService,
                              ApplicationConfig applicationConfig) {
        this.printService = printService;
        this.resource = resourceLoader.getResource("classpath:"+applicationConfig.getQuestionPathByLocale().get(applicationConfig.getLocale()));
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            for (String[] row :  reader.readAll()){
                Question question = new Question(row[0], new ArrayList<>(), new ArrayList<>());
                boolean isCorrectAnswer=false;
                for (String item : Arrays.asList(row).subList(1, row.length)){
                    if (item.startsWith("%")){
                        isCorrectAnswer = true;
                    }
                    if (isCorrectAnswer){
                        question.getCorrectAnswers().add(item.replace("%", "").trim());
                    }
                    else {
                        question.getAnswers().add(item.trim());
                    }
                }
                questions.add(question);
            }

        } catch (IOException | CsvException e) {
            printService.writeInfo(printService.getLocalizedMessage("error.reading.csv", String.valueOf(e)));
        }
        return questions;
    }

}
