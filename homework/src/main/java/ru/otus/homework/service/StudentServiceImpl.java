package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    PrintService printService;

    @Override
    public Student getStudentName() {
        Student student = new Student();
        student.setFirstName(printService.readLine("Enter your first name: "));
        student.setLastName(printService.readLine("Enter your last name: "));
        return student;
    }
}
