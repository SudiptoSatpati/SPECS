package com.exceptional.PlacementManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Random random = new Random();
    private final JavaMailSender mailSender;



    public String generateOTP(String key) {
        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        otpStorage.put(key, otp);
        return otp;
    }

    public boolean validateOTP(String key, String otp) {
        return otp.equals(otpStorage.get(key));
    }

    public void clearOTP(String key) {
        otpStorage.remove(key);
    }

    public void sendOtpMessage(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

}
