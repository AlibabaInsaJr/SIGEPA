package dev.example.SIGEPA.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
    private String email;

    @Size(max = 100, message = "O cargo deve ter no máximo 100 caracteres")
    private String cargo;

    @NotNull(message = "A role é obrigatória")
    private Long roleId;

    @NotNull(message = "O estado activo é obrigatório")
    private Boolean activo;
}