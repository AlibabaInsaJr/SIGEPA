package dev.example.SIGEPA.auth.controller;

import dev.example.SIGEPA.auth.dto.LoginRequestDTO;
import dev.example.SIGEPA.auth.dto.LoginResponseDTO;
import dev.example.SIGEPA.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO response = authService.login(dto);

        return ResponseEntity.ok(response);
    }
}