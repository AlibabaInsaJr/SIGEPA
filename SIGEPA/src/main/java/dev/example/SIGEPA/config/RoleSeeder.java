package dev.example.SIGEPA.config;

import dev.example.SIGEPA.usuario.entity.Role;
import dev.example.SIGEPA.usuario.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String @NonNull ... args) {
        criarRoleSeNaoExistir(
                "ADMIN",
                "Gere todos os módulos do sistema"
        );

        criarRoleSeNaoExistir(
                "GESTOR_PATRIMONIO",
                "Gere apenas o módulo de património"
        );

        criarRoleSeNaoExistir(
                "GESTOR_ECONOMATO",
                "Gere economato e requisições; também actua como aprovador"
        );

        criarRoleSeNaoExistir(
                "SOLICITANTE",
                "Funcionário normal que apenas faz requisições"
        );
    }

    private void criarRoleSeNaoExistir(String nome, String descricao) {
        if (!roleRepository.existsByNome(nome)) {
            Role role = Role.builder()
                    .nome(nome)
                    .descricao(descricao)
                    .activo(true)
                    .build();

            roleRepository.save(role);
        }
    }
}