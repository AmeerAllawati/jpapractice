package com.hibernateims.jpapractice.Controller;

import com.hibernateims.jpapractice.Model.Student;
import com.hibernateims.jpapractice.Services.StudentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(path = "/api/studentImages")
@CrossOrigin("*")
public class StudentImagesController {
    @Autowired
    StudentService studentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Student createStudentWithImage(@RequestParam String name,
                                          @RequestParam String email,
                                          @RequestParam(required = false) MultipartFile image) throws IOException {
        Student newStudent = new Student();
        newStudent.name = name;
        newStudent.email = email;
        Student savedStudent = studentService.addAStudent(newStudent);

        if (image != null) {
            savedStudent.imageName = Integer.toString(savedStudent.id) + "_" + savedStudent.name + ".jpg";
            FileUtils.writeByteArrayToFile(new File("./src/main/resources/static/studentImages/" + savedStudent.imageName), image.getBytes());
            studentService.updateStudent(savedStudent.id, savedStudent);
        }

        return newStudent;
    }

    @PostMapping(path = "/check")
    public void checkAuth() {

    }
}
