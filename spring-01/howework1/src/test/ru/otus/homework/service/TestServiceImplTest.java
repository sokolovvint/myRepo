package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    TestServiceImpl testService;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        testService = context.getBean(TestServiceImpl.class);
    }

    @Test
    void startTest() {
        assertDoesNotThrow(() -> testService.startTest());
    }

    @Test
    void getAllQuestions() {
        assertEquals(5, testService.getAllQuestions().size());
    }

}