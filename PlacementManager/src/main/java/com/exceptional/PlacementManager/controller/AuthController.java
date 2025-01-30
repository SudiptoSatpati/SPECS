package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.*;
import com.exceptional.PlacementManager.entity.UserEntity;
import com.exceptional.PlacementManager.service.AuthService;
import com.exceptional.PlacementManager.service.OTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.module.ResolutionException;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OTPService otpService;

    @PostMapping("/register")
    public Response<String> registerUser(@RequestBody EmailDto emailDto) {
        String otp = otpService.generateOTP(emailDto.getEmail());
        otpService.sendOtpMessage(emailDto.getEmail(),
                "Registration OTP", "Your 6 digits unique verification code is: " + otp);
        return new Response<>(true, "OTP sent to your email!", null);
    }

    @PostMapping("/register-verify-otp")
    public Response<String> verifyOTPRegistration(@RequestBody RegistrationDto registrationDto) throws Exception {
        try {
            if (otpService.validateOTP(registrationDto.getEmail(), registrationDto.getOtp())) {
                otpService.clearOTP(registrationDto.getEmail());
                return authService.registerUser(registrationDto);
            } else {
                return new Response<>(false, "Entered wrong OTP", null);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/update-password")
    public Response<String> updateUserPassword(@RequestBody String email) {
        String otp = otpService.generateOTP(email);
        otpService.sendOtpMessage(email,
                "OTP for Update password", "Your 6 digits unique verification code is: " + otp);
        return new Response<>(true, "OTP sent to your email!", null);
    }

    @PostMapping("/update-password-verify-otp")
    public Response<String> verifyOTPUpdateUserPassword(@RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        if (otpService.validateOTP(updatePasswordDto.getEmail(), updatePasswordDto.getOtp())) {
            otpService.clearOTP(updatePasswordDto.getEmail());
            return authService.updateUserPassword(updatePasswordDto);
        } else {
            return new Response<>(false, "Entered wrong OTP", null);
        }
    }

    @PostMapping("/signin")
    public Response<String> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.authenticateUser(loginRequestDto);
    }

    @PostMapping("/logout")
    public Response<String> logout() {
        return new Response<>(true, "User logged out successfully!", null);
    }


}
