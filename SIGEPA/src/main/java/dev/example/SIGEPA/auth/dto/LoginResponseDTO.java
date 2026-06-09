package dev.example.SIGEPA.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private Long usuarioId;

    private String nome;

    private String email;

    private String cargo;

    private Long roleId;

    private String roleNome;

    private Boolean activo;

    private String token;
}