package com.skill15.exp15;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeRecordRepository employeeRecordRepository;

    public AdminController(EmployeeRecordRepository employeeRecordRepository) {
        this.employeeRecordRepository = employeeRecordRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeRecord> addEmployee(@RequestBody EmployeeRecord employeeRecord) {
        return ResponseEntity.ok(employeeRecordRepository.save(employeeRecord));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (!employeeRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeRecordRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
