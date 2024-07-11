package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }

    public Optional<Student> findStudentById(Integer id) {
        return repository.findById(id);
    }

    public void updateStudent(Student student) throws Exception {

        Optional<Student> std =repository.findByFirstnameAndLastname(student.getFirstname(),student.getLastname());
        if(std.isPresent()) {
            repository.delete(std.get());
            repository.save(student);
        }else{
            throw new Exception("Student Not Found");
        }
    }

    public void deleteStudent(Integer id) throws Exception {
        Optional<Student> std = repository.findById(id);
        if(std.isPresent()) {
            repository.delete(std.get());
        }else{
            throw new Exception("Student Not Found");
        }
    }
}
