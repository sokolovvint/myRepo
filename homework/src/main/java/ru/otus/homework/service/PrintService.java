package ru.otus.homework.service;

public interface PrintService {

    String readLine(String message);

    String readLocalizedLine(String message, Object... params);

    void writeInfo(String message);

    void writeLocalizedInfo(String message, Object... params);

    String getLocalizedMessage(String message, Object... params);
}
