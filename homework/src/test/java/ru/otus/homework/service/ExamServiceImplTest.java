package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.dao.ExamServiceDao;
import ru.otus.homework.dao.ExamServiceDaoImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    private static final String TEST_CSV ="Calculate 2x2?,%4\n" +
            "To be or Not to be?,%To be\n" +
            "Select European countries:,France,Mexico,Brazil,Nigeria,Italy,%France,Italy\n" +
            "Is there life on Mars?,Yes,No,%No\n" +
            "Select the summer months:,February,March,July,November,January,August,December,June,%July,August,June";

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private PrintService printService;

    private ExamServiceDao examServiceDao;

    private StudentService studentService;

    private ApplicationConfig applicationConfig;

    @Test
    void startTest() {
        Resource resource = new ByteArrayResource(TEST_CSV.getBytes(), "testResource");
        when(resourceLoader.getResource("classpath:questions.csv")).thenReturn(resource);

        applicationConfig = new ApplicationConfig();
        applicationConfig.setLocale("en-US");
        applicationConfig.setMinCorrectAnswer(3);
        Map<String, String> map = new HashMap<>();
        map.put("en-US","questions.csv");
        applicationConfig.setQuestionPathByLocale(map);
        examServiceDao = new ExamServiceDaoImpl(resourceLoader, printService, applicationConfig);
        studentService = new StudentServiceImpl(printService);
        ExamService examService = new ExamServiceImpl(examServiceDao, printService, studentService, applicationConfig);

        when(printService.readLocalizedLine(Mockito.anyString())).thenReturn("Mock");
        when(printService.readLocalizedLine(Mockito.anyString(), Mockito.anyCollection())).thenReturn("Mock");

        assertDoesNotThrow(() -> examService.startTest());
    }
}