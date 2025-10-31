package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {

    PrintService printService;

    @BeforeEach
    void init(){
        printService = new PrintServiceImpl();
    }

    @Test
    void writeInfo() {
        assertDoesNotThrow(() -> printService.writeInfo("test"));
    }
}