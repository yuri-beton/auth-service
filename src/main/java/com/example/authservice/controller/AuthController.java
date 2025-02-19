package com.example.authservice.controller;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.models.User;
import com.example.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")  // Разрешаем запросы с фронтенда
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginRequest loginRequest) {
        String response = authService.register(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getEmail());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Map<String, String> token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(token == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        return ResponseEntity.ok(token);
    }
    @GetMapping("/status")
    public ResponseEntity<String> getStatus(@RequestBody LoginRequest loginRequest) {
        boolean isAuth = authService.validateToken(loginRequest.getToken());
        if(isAuth) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(401).body("Invalid token");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody LoginRequest loginRequest) {
        String response = authService.logout(loginRequest.getToken());
        if(response == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/confirm-email")
    public ResponseEntity<String> confirmEmail(@RequestParam String token) {
        String response = authService.confirmEmail(token);
        if(response == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        return ResponseEntity.ok(response);
    }
}
