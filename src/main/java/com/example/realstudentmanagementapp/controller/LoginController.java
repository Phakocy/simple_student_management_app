package com.example.realstudentmanagementapp.controller;

import com.example.realstudentmanagementapp.model.Student;
import com.example.realstudentmanagementapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private StudentService studentService;

    @Autowired
    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new Student());
        model.addAttribute("invalid", null);
        model.addAttribute("newRegistration", null);
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpSession httpSession, Student student, Model model) {
        Student student1 = studentService.getStudentByEmail(student.getEmail()); //validation
        if (student1 == null) {
            model.addAttribute("invalid", "User does not exist. Check login details or register.");
            return "login";
        }
        student1 = studentService.getStudentByEmailAndPassword(student.getEmail(), student.getPassword());
        if (student1 == null) {
            model.addAttribute("invalid", "Incorrect password");
            return "login";
        }
        Student student2 = new Student();
        httpSession.setAttribute("user", student1);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", student);
        return "students";
    }

}
