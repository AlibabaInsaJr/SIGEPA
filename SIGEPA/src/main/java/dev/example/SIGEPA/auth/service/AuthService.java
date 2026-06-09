package dev.example.SIGEPA.auth.service;

import dev.example.SIGEPA.auth.dto.LoginRequestDTO;
import dev.example.SIGEPA.auth.dto.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO dto);
}