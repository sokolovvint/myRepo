package ru.otus.homework.dao;

import ru.otus.homework.dto.Question;

import java.util.List;

public interface ExamServiceDao {

    List<Question> getAllQuestions();

}
