package com.springboot.student_management_system.service;

import com.springboot.student_management_system.DTO.StudentRequestDTO;
import com.springboot.student_management_system.DTO.StudentResponseDTO;
import com.springboot.student_management_system.Entity.StudentEntity;
import com.springboot.student_management_system.Exceptions.DuplicateEmailException;
import com.springboot.student_management_system.Exceptions.StudentNotFoundException;
import com.springboot.student_management_system.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    StudentEntity student = new StudentEntity();

    //Contructor Injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO dto) {
        if(studentRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateEmailException("Student with the email "+dto.getEmail()+" already exists in database");
        }
        StudentEntity student = new StudentEntity();
        student.setStudentFirstName(dto.getFirstName());
        student.setStudentLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setPassword(dto.getPassword());
        StudentEntity savedStudent = studentRepository.save(student);
        return mapToResponseDTO(savedStudent);
    }

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(Long Id) {
        StudentEntity student = studentRepository.findById(Id).orElseThrow(()->
                new StudentNotFoundException("Student not found with Id: "+Id));
        if(student == null){
            return null;
        }
        return mapToResponseDTO(student);
    }

    @Override
    public void deleteStudent(Long Id) {
        StudentEntity student = studentRepository.findById(Id).orElseThrow(()->
                new StudentNotFoundException("Student with id "+Id+" already exists in database"));
        studentRepository.deleteById(Id);
    }

    @Override
    public StudentResponseDTO updateStudent(Long Id, StudentRequestDTO dto) {
        StudentEntity existingStudent = studentRepository.findById(Id).orElseThrow(()->
                new StudentNotFoundException("Student with id "+Id+" already exists in Database"));

        if(studentRepository.existsByEmail(dto.getEmail()) && !existingStudent.getEmail().equals(dto.getEmail())){
            throw new DuplicateEmailException();
        }
        if (existingStudent != null) {
            existingStudent.setStudentFirstName(dto.getFirstName());
            existingStudent.setStudentLastName(dto.getLastName());
            existingStudent.setEmail(dto.getEmail());
            existingStudent.setCourse(dto.getCourse());
            existingStudent.setPassword(dto.getPassword());
            StudentEntity updatedStudent = studentRepository.save(existingStudent);
            return mapToResponseDTO(updatedStudent);
        }
        return null;
    }

    @Override
    public StudentResponseDTO patchStudent(Long Id, StudentRequestDTO dto) {
        StudentEntity existingStudent = studentRepository.findById(Id).orElseThrow(()->
                new StudentNotFoundException("Student with id "+Id+" already exists in Database"));
        if (existingStudent != null) {
            if (dto.getFirstName() != null) {
                existingStudent.setStudentFirstName(dto.getFirstName());
            }
            if (dto.getLastName() != null) {
                existingStudent.setStudentLastName(dto.getLastName());
            }
            if (dto.getEmail() != null) {
                if(studentRepository.existsByEmail(dto.getEmail()) && !existingStudent.getEmail().equals(dto.getEmail())){
                    throw new DuplicateEmailException();
                }
                existingStudent.setEmail(dto.getEmail());
            }
            if (dto.getCourse() != null) {
                existingStudent.setCourse(dto.getCourse());
            }
            if(dto.getPassword() != null){
                existingStudent.setPassword(dto.getPassword());
            }
            StudentEntity updatedStudent = studentRepository.save(existingStudent);
            return mapToResponseDTO(updatedStudent);
        }
        return null;
    }

    private StudentResponseDTO mapToResponseDTO(StudentEntity student){
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getStudentId());
        dto.setFirstName(student.getStudentFirstName());
        dto.setLastName(student.getStudentLastName());
        dto.setEmail(student.getEmail());
        return dto;
    }
}
