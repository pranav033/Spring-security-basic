package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.entities.Student;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    List<Student> students = new ArrayList<>();


    @GetMapping("/hello")
    public String welcome(HttpServletRequest httpServletRequest)
    {
        return "Welcome to Spring Security. You are logged in. Your JSESSSION ID is "+httpServletRequest.getSession().getId();
    }

    @GetMapping("/csrftoken")
    public CsrfToken getcsrftoken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/addstudent")
    public Student addStudent(@RequestBody Student s)
    {
        students.add(s);
        return s;
    }

    @GetMapping("/getallstudents")
    public List<Student> getAllStudents()
    {
        return students;
    }

}
