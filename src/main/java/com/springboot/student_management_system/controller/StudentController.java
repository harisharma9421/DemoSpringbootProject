package com.springboot.student_management_system.controller;

import com.springboot.student_management_system.Entity.StudentEntity;
import com.springboot.student_management_system.Repository.StudentRepository;
import com.springboot.student_management_system.service.StudentService;
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
    public StudentEntity saveStudent(@RequestBody StudentEntity student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getStudent/{id}")
    public StudentEntity getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAllStudents")
    public List<StudentEntity> getAllStudents(){
        return studentService.getAllStudent();
    }

    @PatchMapping("/patchStudent/{id}")
    public String patchStudent(@PathVariable Long id, @RequestBody StudentEntity student){
        StudentEntity patchedStudents = studentService.patchStudent(id, student);
        if(patchedStudents == null){
            return "Student failed to update";
        }
        return "Student with student ID "+id+" updated successfully!";
    }

    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentEntity student){
        StudentEntity updatedStudent = studentService.updateStudent(id,student);
        if(updatedStudent == null){
            return "Student failed to update!";
        }
        return "Details of students with student ID: "+id+" updated successfully!";
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "Student with "+id+" deleted successfully!";
    }
}
