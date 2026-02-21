package com.skill5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id = 101;
    private String name = "Mathew";
    private String gender = "Male";

    @Autowired
    private Certification certification;

    public void display() {

        System.out.println("Student ID     : " + id);
        System.out.println("Student Name   : " + name);
        System.out.println("Gender         : " + gender);
        System.out.println();
        System.out.println(certification);
    }
}