package dev.example.SIGEPA.auth.serviceImpl;

import dev.example.SIGEPA.auth.dto.LoginRequestDTO;
import dev.example.SIGEPA.auth.dto.LoginResponseDTO;
import dev.example.SIGEPA.usuario.entity.Usuario;
import dev.example.SIGEPA.usuario.repository.UsuarioRepository;
import dev.example.SIGEPA.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthImpService implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new RuntimeException("Usuário inactivo");
        }

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        return LoginResponseDTO.builder()
                .usuarioId(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cargo(usuario.getCargo())
                .roleId(usuario.getRole().getId())
                .roleNome(usuario.getRole().getNome())
                .activo(usuario.getActivo())
                .token(null)
                .build();
    }
}