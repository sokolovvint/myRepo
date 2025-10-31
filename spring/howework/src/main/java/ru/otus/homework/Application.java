package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.TestServiceImpl;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestServiceImpl testRunnerService = context.getBean(TestServiceImpl.class);
        testRunnerService.startTest();
    }
}