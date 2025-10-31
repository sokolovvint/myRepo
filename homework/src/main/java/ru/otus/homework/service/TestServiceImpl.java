package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.TestServiceDao;
import ru.otus.homework.dto.Question;
import ru.otus.homework.dto.Student;

import java.util.Arrays;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestServiceDao testServiceDao;

    @Autowired
    private PrintService printService;

    @Autowired
    private StudentService studentService;

    @Value("${min.correct.answer:3}")
    int minCorrectAnswer;

    @Override
    public void startTest() {
        Student student = studentService.getStudentName();
        int result = runTest(student);
        printService.writeInfo("Your result: " + result + " Minimal for success: " + minCorrectAnswer);
        if (result>=minCorrectAnswer){
            printService.writeInfo("Congratulation " + student.toString() + "! Test Passed!");
        }
    }

    @Override
    public int runTest(Student student) {
        int counter=0;
        List<Question> questionList = testServiceDao.getAllQuestions();
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
