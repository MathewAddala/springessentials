package com.skill7.coursecrud;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {

    private List<Course> courses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(int id) {
        return courses.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    public Course addCourse(Course course) {
        courses.add(course);
        return course;
    }

    public Course updateCourse(int id, Course updatedCourse) {
        Course existing = getCourseById(id);
        if (existing != null) {
            existing.setTitle(updatedCourse.getTitle());
            existing.setDuration(updatedCourse.getDuration());
            existing.setFee(updatedCourse.getFee());
            return existing;
        }
        return null;
    }

    public boolean deleteCourse(int id) {
        return courses.removeIf(c -> c.getCourseId() == id);
    }

    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}