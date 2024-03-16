package com.example.build_basic_application.controller;

import com.example.build_basic_application.model.Student;
import com.example.build_basic_application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students",students);
        model.addAttribute("student", new Student());
        return "index";
    }

    @GetMapping("/student/{id}")
    public String getStudentById(Model model,@PathVariable("id") Integer id) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student";
    }

    @PostMapping("/student/new")
    public String createNewStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @PostMapping("/student/update/{id}")
    public String updateStudent(@PathVariable("id") Integer id, @ModelAttribute("student") Student updatedStudent) {
        updatedStudent.setId(id); // Ensure the ID is set
        studentService.updateStudent(updatedStudent);
        return "redirect:/student/" + id;
    }

    @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}
