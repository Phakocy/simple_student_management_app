package com.example.realstudentmanagementapp.service;

import com.example.realstudentmanagementapp.model.Student;

import java.util.List;

public interface StudentService {


    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Student registeredStudent);

    void removeStudentById(Long id);

    Student getStudentByEmail(String email);

    Student getStudentByEmailAndPassword(String email, String password);
}
