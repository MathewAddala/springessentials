package com.skill16.exp16;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Schema(description = "Student model used in the CRUD API")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique student id", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Student full name", example = "Priya Sharma")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Schema(description = "Student email address", example = "priya@example.com")
    private String email;

    @NotBlank(message = "Course is required")
    @Schema(description = "Registered course name", example = "Full Stack Development")
    private String course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
