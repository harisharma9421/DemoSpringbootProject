package com.springboot.student_management_system.service;

import com.springboot.student_management_system.Entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentEntity saveStudent(StudentEntity student);
    List<StudentEntity> getAllStudent();
    StudentEntity getStudentById(Long Id);
    void deleteStudent(Long Id);
    StudentEntity updateStudent(Long Id, StudentEntity student);
    StudentEntity patchStudent(Long Id, StudentEntity student);
}
