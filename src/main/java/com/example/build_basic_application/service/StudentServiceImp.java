package com.example.build_basic_application.service;

import com.example.build_basic_application.model.Student;
import com.example.build_basic_application.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                Student studentToUpdate = students.get(i);
                studentToUpdate.setName(updatedStudent.getName());
                studentToUpdate.setTask(updatedStudent.getTask());
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = now.format(formatter);
                updatedStudent.setCreatedAt(formattedDateTime);
                students.set(i, updatedStudent);
                break;
            }
        }
    }

    @Override
    public List<Student> search(String task, Boolean isDone) {
        List<Student> allStudents = studentRepository.getStudents();
        List<Student> searchResults = new ArrayList<>();

        for (Student student : allStudents) {
            if ((task == null || task.isEmpty() || student.getTask().equals(task)) &&
                    (isDone == null || student.isDone() == isDone)) {
                searchResults.add(student);
            }
        }

        return searchResults;
    }

    @Override
    public List<Student> searchByTask(String task) {
        List<Student> todoList = studentRepository.getStudents();
        return todoList.stream()
                .filter(todo -> todo.getTask().toLowerCase().contains(task.toLowerCase()))
                .toList();
    }

}
