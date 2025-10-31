package ru.otus.homework.dto;

import java.util.List;

public class Question {

    private String text;

    private List<String> answers;

    private List<String> correctAnswers;

    public Question(String text, List<String> answers, List<String> correctAnswers) {
        this.text = text;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
    }

    public String getText() {
        return text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }
}
