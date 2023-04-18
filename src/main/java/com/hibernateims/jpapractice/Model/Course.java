package com.hibernateims.jpapractice.Model;

import org.apache.logging.log4j.spi.CopyOnWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Course {
    int id;
    String name;
    List<Student> enrolledStudents= new ArrayList<>();
}
