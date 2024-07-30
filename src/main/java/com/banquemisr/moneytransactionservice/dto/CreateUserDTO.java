package com.banquemisr.moneytransactionservice.dto;

import com.banquemisr.moneytransactionservice.dto.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private LocalDate birthdate;
    private String phone;
    private Gender gender;
}
