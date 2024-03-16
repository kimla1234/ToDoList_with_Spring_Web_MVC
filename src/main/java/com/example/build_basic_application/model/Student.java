package com.example.build_basic_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String task;
    private String description;
    private boolean isDone;
    private String createdAt;
}
