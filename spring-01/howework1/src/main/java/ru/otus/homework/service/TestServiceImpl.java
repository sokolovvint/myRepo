package ru.otus.homework.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import ru.otus.homework.dto.QuestionDto;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class.getName());

    private Resource resource;

    @Override
    public void startTest() {
        showAllQuestions();
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<QuestionDto> questions = null;
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            questions= reader.readAll().stream()
                    .map(row -> new QuestionDto(row[0], row[1].isEmpty()?new ArrayList<>():Arrays.asList(row).subList(1, row.length)))
                    .toList();
        } catch (IOException | CsvException e) {
            logger.error(String.format("Error reading CSV: %s", e));
        }
        return questions;
    }

    @Override
    public void showAllQuestions() {
        List<QuestionDto> questionDtoList = getAllQuestions();
        if(questionDtoList!=null) {
            questionDtoList.forEach(questionDto -> {
                logger.info(questionDto.getText());

                List<String> answers = questionDto.getAnswers();
                if (answers.isEmpty()) {
                    logger.info("Free Answer: _____");
                } else {
                    logger.info(String.format("Choose the right answers: %s", answers));
                }
                logger.info("");
            });
        }
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
