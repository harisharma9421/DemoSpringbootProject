package com.springboot.student_management_system.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDTO {
    @NotBlank(message = "First Name is mandatory field")
    @Size(min = 2, max = 30, message = "First name must be between 2-30 characters only")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory field")
    @Size(min = 2, max = 30, message = "Last name must be between 2-30 characters only")
    private String lastName;

    @NotBlank(message = "Email is mandatory field")
    @Email(message = "The format of email is wrong!")
    private String email;

    @NotBlank(message = "Course field is mandatory")
    private String course;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%&*+=!]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character"
    )

    private String password;
}
