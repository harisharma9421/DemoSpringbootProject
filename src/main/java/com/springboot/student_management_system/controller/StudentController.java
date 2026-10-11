package com.springboot.student_management_system.controller;

import com.springboot.student_management_system.DTO.StudentRequestDTO;
import com.springboot.student_management_system.DTO.StudentResponseDTO;
import com.springboot.student_management_system.Entity.StudentEntity;
import com.springboot.student_management_system.Repository.StudentRepository;
import com.springboot.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from student 1";
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentResponseDTO> saveStudent(@Valid @RequestBody StudentRequestDTO student){
        StudentResponseDTO savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PatchMapping("/patchStudent/{id}")
    public ResponseEntity<StudentResponseDTO> patchStudent(@PathVariable Long id, @RequestBody StudentRequestDTO student){
        return ResponseEntity.ok(studentService.patchStudent(id, student));
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@Valid @PathVariable Long id, @RequestBody StudentRequestDTO student){
       return ResponseEntity.ok(studentService.updateStudent(id,student));
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
