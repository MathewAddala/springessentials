package com.skill9.exp9;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final Map<Integer, Student> students = Map.of(
            101, new Student(101, "Asha Reddy", "Computer Science"),
            102, new Student(102, "Rahul Verma", "Mechanical Engineering"),
            103, new Student(103, "Meera Nair", "Electronics"));

    public Student getStudentById(int id) {
        Student student = students.get(id);
        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + id + " was not found.");
        }
        return student;
    }
}
