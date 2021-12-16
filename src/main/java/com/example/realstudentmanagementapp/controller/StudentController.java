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
        return "login";
    }

    @GetMapping(value = "/students")
    public String getAllStudents(Model model){
        Student student = new Student();
        model.addAttribute("students", studentServiceImp.getAllStudents());
        model.addAttribute("student", student);
        return "students";
    }

    @GetMapping(value = "/students/edit/{id}")
    public String getStudentById (@PathVariable (value = "id") Long id, Model model){
        model.addAttribute("student", studentServiceImp.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable (value = "id") Long id, @ModelAttribute Student student){
        System.out.println("Update request: " + student);
        Student registeredStudent = studentServiceImp.getStudentById(id);

        if (registeredStudent != null) {
            registeredStudent.setFirstName(student.getFirstName());
            registeredStudent.setLastName(student.getLastName());
            registeredStudent.setEmail(student.getEmail());
            registeredStudent.setDateOfBirth(student.getDateOfBirth());

            studentServiceImp.updateStudent(registeredStudent);
            System.out.println("Student of id: " + registeredStudent.getId() + " is updated.");
        }
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String removeStudent(@PathVariable (value = "id") Long id){
        studentServiceImp.removeStudentById(id);
        return "redirect:/students";
    }

}

