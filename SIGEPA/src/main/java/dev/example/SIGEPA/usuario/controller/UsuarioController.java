package dev.example.SIGEPA.usuario.controller;

import dev.example.SIGEPA.usuario.dto.UsuarioCreateDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioUpdateDTO;
import dev.example.SIGEPA.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioCreateDTO dto) {
        UsuarioDTO usuarioCriado = usuarioService.criar(dto);

        URI location = URI.create("/api/usuarios/" + usuarioCriado.getId());

        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();

        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO dto
    ) {
        UsuarioDTO usuarioActualizado = usuarioService.actualizar(id, dto);

        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        usuarioService.desactivar(id);

        return ResponseEntity.noContent().build();
    }
}