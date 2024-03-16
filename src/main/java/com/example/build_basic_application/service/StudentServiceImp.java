package com.example.build_basic_application.service;

import com.example.build_basic_application.model.Student;
import com.example.build_basic_application.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public List<Student> findAll() {
        return studentRepository.getStudents();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.getStudents()
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addStudent(Student student) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        student.setCreatedAt(formattedDateTime);

        List<Student> students = studentRepository.getStudents();
        Student lastStudent = students.isEmpty() ? null : students.get(students.size() - 1);
        int newId = (lastStudent != null) ? lastStudent.getId() + 1 : 1;
        student.setId(newId);
        students.add(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        List<Student> students = studentRepository.getStudents();
        students.removeIf(student -> student.getId().equals(id));
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        List<Student> students = studentRepository.getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                break;
            }
        }
    }
}
