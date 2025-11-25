package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.homework.config.ApplicationConfig;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {


    @Mock
    private MessageSource messageSource;

    ApplicationConfig applicationConfig;

    @Test
    void writeLocalizedInfo() {
        applicationConfig = new ApplicationConfig();
        applicationConfig.setLocale("en-US");
        PrintService  printService = new PrintServiceImpl(messageSource, applicationConfig);
        when(messageSource.getMessage(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("localizedResource");
        assertDoesNotThrow(() -> printService.writeLocalizedInfo("test"));
    }


    @Test
    void readLocalizedLine() {
        applicationConfig = new ApplicationConfig();
        applicationConfig.setLocale("en-US");
        Scanner scanner = new Scanner("Test input line");
        PrintService  printService = new PrintServiceImpl(messageSource, applicationConfig);
        ReflectionTestUtils.setField(printService, "scanner", scanner);
        when(messageSource.getMessage(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("localizedResource");
        assertEquals("Test input line", printService.readLocalizedLine("Test input line"));
    }
}