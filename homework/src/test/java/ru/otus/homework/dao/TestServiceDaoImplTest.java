package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.homework.dao.TestServiceDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceDaoImplTest {

    private static final String TEST_CSV ="Calculate 2x2?,%4\n" +
            "To be or Not to be?,%To be\n" +
            "Select European countries:,France,Mexico,Brazil,Nigeria,Italy,%France,Italy\n" +
            "Is there life on Mars?,Yes,No,%No\n" +
            "Select the summer months:,February,March,July,November,January,August,December,June,%July,August,June";

    @Mock
    private ResourceLoader resourceLoader;

    @Test
    void getAllQuestions() {
        Resource resource = new ByteArrayResource(TEST_CSV.getBytes(), "testResource");
        when(resourceLoader.getResource("classpath:questions.csv")).thenReturn(resource);

        TestServiceDaoImpl testServiceDao = new TestServiceDaoImpl(resourceLoader, "classpath:questions.csv");
        assertEquals(5, testServiceDao.getAllQuestions().size());
    }

}