package com.hibernateims.jpapractice.Controller;

import com.hibernateims.jpapractice.Model.Student;
import com.hibernateims.jpapractice.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public Optional<Student> getAStudent(@PathVariable(name = "id") int id) {
        return studentService.getSpecificStudent(id);
    }

    @PostMapping
    public Student addAStudent(@RequestBody Student newStudent) {
        return studentService.addAStudent(newStudent);
    }

    @PutMapping
    public Optional<Student> updatStudent(@RequestBody Student currStudent) {
        return studentService.updateStudent(currStudent.id, currStudent);
    }

    @DeleteMapping(path = "/{id}")
    public Optional<Student> deleteAStudent(@PathVariable(name = "id") int id) {
        return studentService.deleteAStudent(id);
    }

}
