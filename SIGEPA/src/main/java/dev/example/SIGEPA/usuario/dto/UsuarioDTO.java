package dev.example.SIGEPA.usuario.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private String cargo;

    private Long roleId;

    private String roleNome;

    private Boolean activo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataActualizacao;
}