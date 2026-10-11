package com.springboot.student_management_system.DTO;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
}
