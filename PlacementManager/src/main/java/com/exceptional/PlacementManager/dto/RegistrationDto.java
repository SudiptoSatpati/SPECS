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
    private Long id;
    private String email;
    private String password;
    private String otp;
    private Set<String> roles = new HashSet<>();
}
