package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.dto.enums.Country;
import com.banquemisr.moneytransactionservice.dto.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Account> account;

    private Country country;
    private String phone;
    private Gender gender;

    public UserDTO toUserDTO() {
        return UserDTO
                .builder()
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }

//    @Column(nullable = false)
//    private String firstName;
//
//    @Column(nullable = false)
//    private String lastName;

//    @Column(nullable = false)
//    private String address;
}
