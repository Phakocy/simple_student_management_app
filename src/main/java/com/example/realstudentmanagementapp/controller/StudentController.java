package com.example.realstudentmanagementapp.controller;

import com.example.realstudentmanagementapp.model.Student;
import com.example.realstudentmanagementapp.ServiceImp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private StudentServiceImp studentServiceImp;

    @Autowired
    public StudentController(StudentServiceImp studentServiceImp) {
        this.studentServiceImp = studentServiceImp;
    }

    @GetMapping(value = "/students/register")
    public String getInsertStudentPage(Model model){
        Student student = new Student();
        model.addAttribute("New_Student", student);
        return "registration_form";
    }

    @PostMapping("/students/register")
    public String insertStudent(@ModelAttribute Student student){
        System.out.println("Registration for student: " + student);
        Student newStudent = new Student();

        newStudent.setEmail(student.getEmail());
        newStudent.setFirstName((student.getFirstName()));
        newStudent.setLastName(student.getLastName());
        newStudent.setPassword(student.getPassword());
        newStudent.setDateOfBirth(student.getDateOfBirth());
        newStudent.setGender(student.getGender());
        studentServiceImp.createStudent(newStudent);
        return "redirect:/login";
    }

    @GetMapping(value = "/students")
    public String getAllStudents(Model model){
        model.addAttribute("All students", studentServiceImp.getAllStudents());
        return "registration_form";
    }

    @GetMapping(value = "/students/{id}")
    public String getStudentById (@PathVariable Long id){
        studentServiceImp.getStudentById(id);
        return "redirect:/students";
    }

    @PutMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, Student student, Model model){

        Student registeredStudent = studentServiceImp.getStudentById(id);
        registeredStudent.setId(id);
        registeredStudent.setFirstName(student.getFirstName());
        registeredStudent.setLastName(student.getLastName());
        registeredStudent.setEmail(student.getEmail());

        studentServiceImp.updateStudent(registeredStudent);
        return "redirect:/students";
    }

    @DeleteMapping("/students/{id}")
    public String removeStudent(@PathVariable Long id){
        studentServiceImp.removeStudentById(id);
        return "redirect:/students";
    }

}

