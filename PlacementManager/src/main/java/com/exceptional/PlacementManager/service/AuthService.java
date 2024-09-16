package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.LoginRequestDto;
import com.exceptional.PlacementManager.dto.RegistrationDto;
import com.exceptional.PlacementManager.dto.UpdatePasswordDto;
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
    private final DepartmentService departmentService;
    private final CollegeService collegeService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(
            UserRepository userRepository,
            RoleService roleService,
            DepartmentService departmentService,
            CollegeService collegeService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.departmentService = departmentService;
        this.collegeService = collegeService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> registerUser(RegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            return ResponseEntity.badRequest().body("This email id is already registered.");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userEntity.setDepartment(departmentService.findOrCreateDepartment(registrationDto.getDepartment()));
        userEntity.setCollege(collegeService.findOrCreateCollege(registrationDto.getCollege()));
        Set<RoleEntity> roles = new HashSet<>();
        for (String role : registrationDto.getRoles()) {
            roles.add(roleService.findOrCreateRole(role));
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
        return ResponseEntity.ok("User registered successfully.");
    }

    public ResponseEntity<String> authenticateUser(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("User signed in successfully!");
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }
    }

    public ResponseEntity<String> updateUserPassword(UpdatePasswordDto updatePasswordDto) {
        UserEntity userEntity = userRepository.findByEmail(updatePasswordDto.getEmail());
        if (userEntity == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        userEntity.setPassword(passwordEncoder.encode(updatePasswordDto.getNew_password()));
        userRepository.save(userEntity);
        return ResponseEntity.ok("Password updated successfully");
    }

    public UserEntity getCurrentUserByEmail() {
        return userRepository.findByEmail(getCurrentEmail());
    }

    public String getCurrentEmail() {
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
