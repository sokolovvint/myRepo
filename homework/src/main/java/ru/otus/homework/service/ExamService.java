package ru.otus.homework.service;

import ru.otus.homework.dto.Question;

public interface ExamService {

    void startTest();

    int runTest();

    boolean checkAnswer(Question question, String answer);
}
