package com.skill5;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 501;
    private String name = "Spring Boot Certification";
    private String dateOfCompletion = "10-June-2026";

    public String toString() {
        return "Certification ID   : " + id +
               "\nCertification Name : " + name +
               "\nCompleted On       : " + dateOfCompletion;
    }
}