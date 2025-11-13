package ru.otus.homework.service;

import ru.otus.homework.dto.Question;

public interface TestService {

    void startTest();

    int runTest();

    boolean checkAnswer(Question question, String answer);
}
