package com.skill7.coursecrud;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        Course course = service.getCourseById(id);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Title cannot be empty");
        }
        Course saved = service.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {

        boolean deleted = service.deleteCourse(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
        return ResponseEntity.ok("Course deleted successfully");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {
        return ResponseEntity.ok(service.searchByTitle(title));
    }
}