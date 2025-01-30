package com.exceptional.PlacementManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String name;
    private String email;
    private String password;
    private String otp;
    private String department;
    private String college;
    private Set<String> roles = new HashSet<>();
}
