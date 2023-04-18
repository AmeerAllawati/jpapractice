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
    @Pattern(regexp = "[a-z|A-Z|0-9|._%+-]+@[a-zA-Z0-9.-]+\\.[a-z|A-Z]{2,}", message="Invalid email")
    public String email;
    public String imageName;
}
