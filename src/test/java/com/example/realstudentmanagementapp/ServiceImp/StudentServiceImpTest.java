package com.example.realstudentmanagementapp.ServiceImp;

import com.example.realstudentmanagementapp.model.Student;
import com.example.realstudentmanagementapp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImp studentService;

    private Student student;


    @BeforeEach
    void setUp() {
        student = new Student();
        student.setEmail("phakocy@decagon.dev");
        student.setFirstName("Warith");
        student.setLastName("Omojola");
        student.setGender("male");
        student.setDateOfBirth(new Date(03-04-21));
        student.setPassword("1234");

    }

    @Test
    void shouldGetUserByEmail() {

        //mock userRepository

        when(studentRepository.getStudentByEmail(anyString())).thenReturn(student);

        //Call the method to be tested

        Student getStudent = studentService.getStudentByEmail("phakocy@decagon.dev");

        //assertions

        assertNotNull(getStudent);
        assertEquals("phakocy@decagon.dev" , getStudent.getEmail());

        verify(studentRepository, times(1)).getStudentByEmail(anyString());

    }

    @Test
    void shouldCreateStudentTest() {

        //mock userRepository

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        //Call the method to be tested
        studentService.createStudent(student);

        //Assertions

        verify(studentRepository, times(1)).save(any(Student.class));
    }
}