package ru.otus.homework.service;

import ru.otus.homework.dto.QuestionDto;

import java.util.List;

public interface TestService {

    void startTest();

    List<QuestionDto> getAllQuestions();

    void showAllQuestions();
}
