package com.springboot.student_management_system.service;

import com.springboot.student_management_system.Entity.StudentEntity;
import com.springboot.student_management_system.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    //Contructor Injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(Long Id) {
        return studentRepository.findById(Id).orElse(null);
    }

    @Override
    public void deleteStudent(Long Id) {
        studentRepository.deleteById(Id);
    }

    @Override
    public StudentEntity updateStudent(Long Id, StudentEntity student) {
        StudentEntity existingStudent = studentRepository.findById(Id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setStudentFirstName(student.getStudentFirstName());
            existingStudent.setStudentLastName(student.getStudentLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setCourse(student.getCourse());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    @Override
    public StudentEntity patchStudent(Long Id, StudentEntity student) {
        StudentEntity existingStudent = studentRepository.findById(Id).orElse(null);
        if (student != null) {
            if (student.getStudentFirstName() != null) {
                existingStudent.setStudentFirstName(student.getStudentFirstName());
            }
            if (student.getStudentLastName() != null) {
                existingStudent.setStudentLastName(student.getStudentLastName());
            }
            if (student.getEmail() != null) {
                existingStudent.setEmail(student.getEmail());
            }
            if (student.getCourse() != null) {
                existingStudent.setCourse(student.getCourse());
            }
            studentRepository.save(existingStudent);
        }
        return null;
    }
}
