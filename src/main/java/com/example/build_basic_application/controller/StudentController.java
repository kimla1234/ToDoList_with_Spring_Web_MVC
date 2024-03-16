package com.example.build_basic_application.controller;

import com.example.build_basic_application.model.Student;
import com.example.build_basic_application.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class StudentController {
    private final StudentService studentService;

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



    @PostMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id, @ModelAttribute("student") Student updatedStudent) {
        studentService.updateStudent(updatedStudent);
        return "redirect:/"; // Redirect to the index page
    }

    @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping("/student/search")
    public String searchTodoByTask(Model model, @RequestParam(value = "task", required = false) String task, @RequestParam(value = "isDone", required = false) Boolean isDone){
        List<Student> todos;
        if(task != null && !task.isEmpty()) {
            todos = studentService.searchByTask(task);
        } else {
            todos = studentService.findAll();
        }
        // Filter todos by isDone if the checkbox is checked
        // Filter todos by isDone
        if(isDone == null) {
            isDone = false; // Set a default value if isDone is null
        }
        Boolean finalIsDone = isDone;
        todos = todos.stream().filter(todo -> todo.isDone() == finalIsDone).collect(Collectors.toList());
        model.addAttribute("todos",todos);
        return "result";
    }
}
