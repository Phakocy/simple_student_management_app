package com.example.realstudentmanagementapp.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "students")
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})
public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, updatable = false)
        private Long id;

        @Column(nullable = false, columnDefinition = "TEXT")
        private String firstName;

        @Column( nullable = false, columnDefinition = "TEXT")
        private String lastName;

        @Column (nullable = false, columnDefinition = "TEXT")
        private String email;

        @Column(columnDefinition = "TEXT")
        private String password;

        private String gender;
        private Date dateOfBirth;
}

