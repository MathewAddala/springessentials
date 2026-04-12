package com.skill16.exp16;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DataInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            Student student = new Student();
            student.setName("Asha Reddy");
            student.setEmail("asha@example.com");
            student.setCourse("Spring Boot");
            studentRepository.save(student);
        }
    }
}
