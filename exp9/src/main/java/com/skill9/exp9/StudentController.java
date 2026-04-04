package com.skill9.exp9;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        int studentId = parseStudentId(id);
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    private int parseStudentId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Student ID must be a valid number.");
        }
    }
}
