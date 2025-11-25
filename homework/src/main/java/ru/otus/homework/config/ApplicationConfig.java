package ru.otus.homework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "exam")
@Component
public class ApplicationConfig {

    private String locale;

    private int minCorrectAnswer;

    private Map<String, String> questionPathByLocale;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getMinCorrectAnswer() {
        return minCorrectAnswer;
    }

    public void setMinCorrectAnswer(int minCorrectAnswer) {
        this.minCorrectAnswer = minCorrectAnswer;
    }

    public Map<String, String> getQuestionPathByLocale() {
        return questionPathByLocale;
    }

    public void setQuestionPathByLocale(Map<String, String> questionPathByLocale) {
        this.questionPathByLocale = questionPathByLocale;
    }
}