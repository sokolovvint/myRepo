package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.dao.ExamServiceDao;
import ru.otus.homework.dto.Question;
import ru.otus.homework.dto.Student;

import java.util.Arrays;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private ExamServiceDao examServiceDao;

    private PrintService printService;

    private StudentService studentService;

    private int minCorrectAnswer;

    public ExamServiceImpl(ExamServiceDao examServiceDao,
                           PrintService printService,
                           StudentService studentService,
                           ApplicationConfig applicationConfig) {
        this.examServiceDao = examServiceDao;
        this.printService = printService;
        this.studentService = studentService;
        this.minCorrectAnswer = applicationConfig.getMinCorrectAnswer();
    }

    @Override
    public void startTest() {
        Student student = studentService.getStudentName();
        int result = runTest();
        printService.writeLocalizedInfo("your.result", result, minCorrectAnswer);
        if (result>=minCorrectAnswer){
            printService.writeLocalizedInfo("congratulation.test.passed", student.toString());
        }
    }

    @Override
    public int runTest() {
        int counter=0;
        List<Question> questionList = examServiceDao.getAllQuestions();
        if(questionList!=null) {
            for (Question question: questionList) {
                printService.writeInfo(question.getText());

                List<String> allAnswers = question.getAnswers();
                String answer = "";
                if (allAnswers.isEmpty()) {
                    answer = printService.readLocalizedLine("enter.your.answer");
                } else {
                    answer = printService.readLocalizedLine("choose.right.answers", allAnswers);
                }
                if (checkAnswer(question, answer)) {
                    counter++;
                    printService.writeLocalizedInfo("correct");
                }
                else{
                    printService.writeLocalizedInfo("wrong");
                }
            }
        }
        return counter;
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        List<String> answers = Arrays.asList(answer.split(","));
        answers = answers.stream().map(String::trim).toList();
        return answers.containsAll(question.getCorrectAnswers()) && question.getCorrectAnswers().containsAll(answers);
    }

}
