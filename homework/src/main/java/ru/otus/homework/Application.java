package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.service.ExamServiceImpl;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ExamServiceImpl testRunnerService = ctx.getBean(ExamServiceImpl.class);
        testRunnerService.startTest();
    }
}