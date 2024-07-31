package com.banquemisr.moneytransactionservice.dto;

import com.banquemisr.moneytransactionservice.dto.enums.Gender;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private LocalDate birthdate;
    private String phone;
    private Gender gender;
}
