package com.example.realstudentmanagementapp.ServiceImp;

import com.example.realstudentmanagementapp.model.Student;
import com.example.realstudentmanagementapp.repository.StudentRepository;
import com.example.realstudentmanagementapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

 private StudentRepository studentRepository;

 @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student createStudent(Student student) {
       return  studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.getStudentByEmail(email);
    }

    @Override
    public Student getStudentByEmailAndPassword(String email, String password) {
        return studentRepository.getStudentByEmailAndPassword(email, password);
    }

}
