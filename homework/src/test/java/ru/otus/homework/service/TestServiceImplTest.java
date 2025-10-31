package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.homework.dao.TestServiceDao;
import ru.otus.homework.dao.TestServiceDaoImpl;
import ru.otus.homework.service.TestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    private static final String TEST_CSV ="Calculate 2x2?,%4\n" +
            "To be or Not to be?,%To be\n" +
            "Select European countries:,France,Mexico,Brazil,Nigeria,Italy,%France,Italy\n" +
            "Is there life on Mars?,Yes,No,%No\n" +
            "Select the summer months:,February,March,July,November,January,August,December,June,%July,August,June";

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private PrintService printService;

    private TestServiceDao testServiceDao;

    private StudentService studentService;

    @Test
    void startTest() {
        Resource resource = new ByteArrayResource(TEST_CSV.getBytes(), "testResource");
        when(resourceLoader.getResource("classpath:questions.csv")).thenReturn(resource);

        TestService testService = new TestServiceImpl();
        testServiceDao = new TestServiceDaoImpl(resourceLoader, "classpath:questions.csv");
        studentService = new StudentServiceImpl();

        when(printService.readLine(Mockito.anyString())).thenReturn("Mock");

        ReflectionTestUtils.setField(testService, "testServiceDao", testServiceDao);
        ReflectionTestUtils.setField(testServiceDao, "printService", printService);

        ReflectionTestUtils.setField(testService, "printService", printService);
        ReflectionTestUtils.setField(testService, "studentService", studentService);
        ReflectionTestUtils.setField(studentService, "printService", printService);

        assertDoesNotThrow(() -> testService.startTest());
    }
}