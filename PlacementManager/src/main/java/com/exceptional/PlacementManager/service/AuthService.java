package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.LoginRequestDto;
import com.exceptional.PlacementManager.dto.RegistrationDto;
import com.exceptional.PlacementManager.entity.RoleEntity;
import com.exceptional.PlacementManager.entity.UserEntity;
import com.exceptional.PlacementManager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, RoleService roleService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }



    public ResponseEntity<String> registerUser(RegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            return ResponseEntity.badRequest().body("This email id is already registered.");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Set<RoleEntity> roles = new HashSet<>();
        for(String role: registrationDto.getRoles()) {
            roles.add(roleService.findOrCreateRole(role));
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
        return ResponseEntity.ok("User registered successfully.");
    }


    public String authenticateUser(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "User signed in successfully!";
        } catch (AuthenticationException e) {
            return "Invalid username or password!";
        }
    }

    public UserEntity findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }


}
