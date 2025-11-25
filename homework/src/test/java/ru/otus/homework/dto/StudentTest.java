package ru.otus.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    @Test
    void testToString() {
        Student student = new Student();
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");

        assertEquals("Ivan Ivanov", student.toString());
    }
}