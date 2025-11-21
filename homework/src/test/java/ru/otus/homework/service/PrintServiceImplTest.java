package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {


    @Test
    void writeInfo() {
        PrintService  printService = new PrintServiceImpl();
        assertDoesNotThrow(() -> printService.writeInfo("test"));
    }


    @Test
    void readLine() {
        Scanner scanner = new Scanner("Test input line");
        PrintService  printService = new PrintServiceImpl(scanner);
        assertEquals("Test input line", printService.readLine("Test input line"));
    }
}