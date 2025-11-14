package ru.otus.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PrintServiceImpl implements PrintService {

    private static final Logger logger = LoggerFactory.getLogger(PrintServiceImpl.class.getName());

    private Scanner scanner;

    public PrintServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    public PrintServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine(String message) {
        logger.info(message);
        return scanner.nextLine();
    }

    @Override
    public void writeInfo(String message) {
        logger.info(message);
    }

}
