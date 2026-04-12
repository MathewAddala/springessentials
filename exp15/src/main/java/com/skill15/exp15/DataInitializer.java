package com.skill15.exp15;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final EmployeeRecordRepository employeeRecordRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository appUserRepository,
                           EmployeeRecordRepository employeeRecordRepository,
                           PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.employeeRecordRepository = employeeRecordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (appUserRepository.count() == 0) {
            appUserRepository.save(new AppUser("admin", passwordEncoder.encode("admin123"), Role.ADMIN));
            appUserRepository.save(new AppUser("employee", passwordEncoder.encode("employee123"), Role.EMPLOYEE));
        }
        if (employeeRecordRepository.count() == 0) {
            employeeRecordRepository.save(new EmployeeRecord("Anita Joseph", "HR"));
            employeeRecordRepository.save(new EmployeeRecord("Kiran Rao", "Engineering"));
        }
    }
}
