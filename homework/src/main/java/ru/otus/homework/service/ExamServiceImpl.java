package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    int minCorrectAnswer;

    public ExamServiceImpl(ExamServiceDao examServiceDao, PrintService printService, StudentService studentService, @Value("${min.correct.answer:3}") int minCorrectAnswer) {
        this.examServiceDao = examServiceDao;
        this.printService = printService;
        this.studentService = studentService;
        this.minCorrectAnswer = minCorrectAnswer;
    }

    @Override
    public void startTest() {
        Student student = studentService.getStudentName();
        int result = runTest();
        printService.writeInfo("Your result: " + result + " Minimal for success: " + minCorrectAnswer);
        if (result>=minCorrectAnswer){
            printService.writeInfo("Congratulation " + student.toString() + "! Test Passed!");
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
                    answer = printService.readLine("Enter your answer: ");
                } else {
                    answer = printService.readLine(String.format("Choose the right answers %s:", allAnswers));
                }
                if (checkAnswer(question, answer)) {
                    counter++;
                    printService.writeInfo("CORRECT!");
                }
                else{
                    printService.writeInfo("WRONG!");
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
