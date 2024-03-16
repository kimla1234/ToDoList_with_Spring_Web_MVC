package com.example.build_basic_application.repository;

import com.example.build_basic_application.model.Student;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class StudentRepository {
    private List<Student> students = new ArrayList<>();
    public StudentRepository (){
        students.add(new Student(001,"Kimla","Homework","Class DevOps",true,"12-03-2024 04:30"));
        students.add(new Student(002,"Roth","Homework","Class DevOps",true,"13-03-2024 01:30"));
        students.add(new Student(003,"Nika","Homework","Class DevOps",true,"4-03-2024 10:30"));
        students.add(new Student(004,"Smey","Homework","Class DevOps",true,"15-03-2024 12:30"));
    }


}
