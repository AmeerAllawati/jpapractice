package com.hibernateims.jpapractice.Services;


import com.hibernateims.jpapractice.Model.Student;
import com.hibernateims.jpapractice.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getSpecificStudent(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addAStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    public Optional<Student> updateStudent(int id, Student newStudent) {
        Optional<Student> foundStudent = getSpecificStudent(id);
        foundStudent.ifPresent(
                (currentStudent) -> {
                    currentStudent.name = newStudent.name;
                    currentStudent.email = newStudent.email;
                    studentRepository.save(currentStudent);
                });
        return foundStudent;
    }

    public Optional<Student> deleteAStudent(int id) {
        Optional<Student> foundStudent = getSpecificStudent(id);
        studentRepository.deleteById(id);
        return foundStudent;
    }
}
