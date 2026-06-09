package dev.example.SIGEPA.usuario.serviceImpl;

import dev.example.SIGEPA.usuario.dto.UsuarioCreateDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioUpdateDTO;
import dev.example.SIGEPA.usuario.entity.Role;
import dev.example.SIGEPA.usuario.entity.Usuario;
import dev.example.SIGEPA.usuario.repository.RoleRepository;
import dev.example.SIGEPA.usuario.repository.UsuarioRepository;
import dev.example.SIGEPA.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioImpService implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO criar(UsuarioCreateDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um usuário com este email");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .cargo(dto.getCargo())
                .role(role)
                .activo(true)
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return toDTO(usuarioSalvo);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um usuário com este email");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        usuario.setRole(role);
        usuario.setActivo(dto.getActivo());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return toDTO(usuarioActualizado);
    }

    @Override
    public void desactivar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cargo(usuario.getCargo())
                .roleId(usuario.getRole().getId())
                .roleNome(usuario.getRole().getNome())
                .activo(usuario.getActivo())
                .dataCriacao(usuario.getDataCriacao())
                .dataActualizacao(usuario.getDataActualizacao())
                .build();
    }
}