package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.LoginRequestDto;
import com.exceptional.PlacementManager.dto.RegistrationDto;
import com.exceptional.PlacementManager.entity.UserEntity;
import com.exceptional.PlacementManager.service.AuthService;
import com.exceptional.PlacementManager.service.OTPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final AuthService authService;
    private final OTPService otpService;

    public AuthController(AuthService authService, OTPService otpService) {
        this.authService = authService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody String email) {
        String otp = otpService.generateOTP(email);
        otpService.sendOtpMessage(email,
                "Registration OTP", "Your 6 digit unique verification code is: " + otp);
        return "OTP sent to your email!";
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTPRegistration(@RequestBody RegistrationDto registrationDto) throws Exception {
        if (otpService.validateOTP(registrationDto.getEmail(), registrationDto.getOtp())) {
            otpService.clearOTP(registrationDto.getEmail());
            return authService.registerUser(registrationDto);
        } else {
            throw new Exception("Entered wrong OTP, registration failed");
        }
    }

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.authenticateUser(loginRequestDto);
    }


    @PostMapping("/logout")
    public String logout() {
        return "User logged out successfully!";
    }


}
