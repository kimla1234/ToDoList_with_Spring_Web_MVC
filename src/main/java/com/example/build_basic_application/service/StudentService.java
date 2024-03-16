package com.example.build_basic_application.service;


import com.example.build_basic_application.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();
    public Student findById(Integer id);
    public void addStudent(Student student);
    public void deleteStudent(Integer id);
    public void updateStudent(Student product);


}
