package dev.example.SIGEPA.usuario.repository;

import dev.example.SIGEPA.usuario.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNome(String nome);

    boolean existsByNome(String nome);
}