package com.springboot.student_management_system.service;

import com.springboot.student_management_system.DTO.StudentRequestDTO;
import com.springboot.student_management_system.DTO.StudentResponseDTO;
import com.springboot.student_management_system.Entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentResponseDTO saveStudent(StudentRequestDTO dto);
    List<StudentResponseDTO> getAllStudent();
    StudentResponseDTO getStudentById(Long Id);
    void deleteStudent(Long Id);
    StudentResponseDTO updateStudent(Long Id, StudentRequestDTO dto);
    StudentResponseDTO patchStudent(Long Id, StudentRequestDTO dto);
}
