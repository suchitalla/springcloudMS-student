package com.example.student.controller;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }
    @GetMapping("/id")
    public ResponseEntity<List<Student>> findStudent(@PathVariable("id") Integer id) {
        if(service.findStudentById(id).isPresent()) {
            return new ResponseEntity(service.findStudentById(id), HttpStatus.FOUND);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody Student student
    ) {
        service.saveStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> update(
            @RequestBody Student student
    ) {
        try {
            service.updateStudent(student);
            return new ResponseEntity(student,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(student,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(
            @PathVariable("id") Integer id
    ) {
        try {
            service.deleteStudent(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
