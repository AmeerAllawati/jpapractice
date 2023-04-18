package com.hibernateims.jpapractice.Repository;

import com.hibernateims.jpapractice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
