package dev.example.SIGEPA.usuario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Usuario> usuarios = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataActualizacao;

    @PrePersist
    protected void prePersist() {
        dataCriacao = LocalDateTime.now();

        if (activo == null) {
            activo = true;
        }
    }

    @PreUpdate
    protected void preUpdate() {
        dataActualizacao = LocalDateTime.now();
    }
}