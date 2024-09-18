package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.LoginRequestDto;
import com.exceptional.PlacementManager.dto.RegistrationDto;
import com.exceptional.PlacementManager.dto.UpdatePasswordDto;
import com.exceptional.PlacementManager.entity.UserEntity;
import com.exceptional.PlacementManager.service.AuthService;
import com.exceptional.PlacementManager.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OTPService otpService;

    @PostMapping("/register")
    public String registerUser(@RequestBody String email) {
        String otp = otpService.generateOTP(email);
        otpService.sendOtpMessage(email,
                "Registration OTP", "Your 6 digits unique verification code is: " + otp);
        return "OTP sent to your email!";
    }

    @PostMapping("/register-verify-otp")
    public ResponseEntity<String> verifyOTPRegistration(@RequestBody RegistrationDto registrationDto) throws Exception {
        if (otpService.validateOTP(registrationDto.getEmail(), registrationDto.getOtp())) {
            otpService.clearOTP(registrationDto.getEmail());
            return authService.registerUser(registrationDto);
        } else {
            throw new Exception("Entered wrong OTP");
        }
    }

    @PostMapping("/update-password")
    public String updateUserPassword(@RequestBody String email) {
        String otp = otpService.generateOTP(email);
        otpService.sendOtpMessage(email,
                "OTP for Update password", "Your 6 digits unique verification code is: " + otp);
        return "OTP sent to your email!";
    }

    @PostMapping("/update-password-verify-otp")
    public ResponseEntity<String> verifyOTPUpdateUserPassword(@RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        if (otpService.validateOTP(updatePasswordDto.getEmail(), updatePasswordDto.getOtp())) {
            otpService.clearOTP(updatePasswordDto.getEmail());
            return authService.updateUserPassword(updatePasswordDto);
        } else {
            throw new Exception("Entered wrong OTP");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.authenticateUser(loginRequestDto);
    }

    @PostMapping("/logout")
    public String logout() {
        return "User logged out successfully!";
    }


}
