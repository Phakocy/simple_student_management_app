package com.example.realstudentmanagementapp.repository;

import com.example.realstudentmanagementapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository <Student,Long> {

    Student getStudentByEmail(String email);

    Student getStudentByEmailAndPassword(String email, String password);
}
