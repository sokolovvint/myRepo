package ru.otus.homework.dto;

import java.util.List;

public class QuestionDto {

    private String text;

    private List<String> answers;

    public QuestionDto(String text, List<String> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
