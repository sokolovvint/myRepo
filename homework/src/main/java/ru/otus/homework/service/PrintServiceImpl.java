package ru.otus.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.ApplicationConfig;

import java.util.Locale;
import java.util.Scanner;

@Service
public class PrintServiceImpl implements PrintService {

    private static final Logger logger = LoggerFactory.getLogger(PrintServiceImpl.class.getName());

    private Scanner scanner;

    private MessageSource messageSource;

    private String locale;

    public PrintServiceImpl(MessageSource messageSource,
                            ApplicationConfig applicationConfig) {
        this.messageSource = messageSource;
        this.locale = applicationConfig.getLocale();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine(String message) {
        writeInfo(message);
        return scanner.nextLine();
    }

    @Override
    public String readLocalizedLine(String message, Object... params) {
        return readLine(getLocalizedMessage(message, params));
    }

    @Override
    public void writeInfo(String message) {
        logger.info(message);
    }

    @Override
    public void writeLocalizedInfo(String message, Object... params) {
        writeInfo(getLocalizedMessage(message, params));
    }

    @Override
    public String getLocalizedMessage(String message, Object... params){
        return messageSource.getMessage(message, params, Locale.forLanguageTag(locale));
    }
}
