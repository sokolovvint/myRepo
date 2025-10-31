package ru.otus.homework.service;

import ru.otus.homework.dto.Question;
import ru.otus.homework.dto.Student;

public interface TestService {

    void startTest();

    int runTest();

    boolean checkAnswer(Question question, String answer);
}
