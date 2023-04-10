package com.hibernateims.jpapractice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String name;
    @Column
    @Pattern(regexp = "")
    public String email;
}
