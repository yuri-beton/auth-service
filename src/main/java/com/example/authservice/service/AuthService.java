package com.example.authservice.service;

import com.example.authservice.models.User;
import com.example.authservice.repository.AuthRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final JavaMailSender mailSender;
    public AuthService(AuthRepository authRepository, JavaMailSender mailSender) {
        this.authRepository = authRepository;
        this.mailSender = mailSender;
    }

    public String register(String username, String password, String email) {
        if(authRepository.findByEmail(email) != null || authRepository.findByUsername(username) != null) {
            return "Email or username already in use";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole_id(3L);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        authRepository.save(user);
        String confirmationLink = "http://localhost:8081/api/auth/confirm-email?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Подтверждение регистрации");
        message.setText("Перейдите по ссылке для подтверждения регистрации " + confirmationLink);
        mailSender.send(message);
        return "Registration successful";
    }

    public Map<String, String> login(String username, String password) {
        User user = authRepository.findByUsernameAndPassword(username, password);
        if(user == null || !user.isConfirmed()) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        authRepository.save(user);
        return Collections.singletonMap("token", token);
    }

    public boolean validateToken(String token) {
        User user = authRepository.findByToken(token);
        return user != null && !Objects.equals(user.getToken(), "");
    }

    public String logout(String token) {
        User user = authRepository.findByToken(token);
        if(user == null) {
            return null;
        }
        user.setToken(null);
        authRepository.save(user);
        return "Logout successful";
    }
    public String confirmEmail(String token) {
        User user = authRepository.findByToken(token);
        if(user == null) {
            return null;
        }
        user.setToken(null);
        user.setConfirmed();
        authRepository.save(user);
        return "Confirm email successful";
    }
}
