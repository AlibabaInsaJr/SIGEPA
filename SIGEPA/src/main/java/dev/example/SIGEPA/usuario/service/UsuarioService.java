package dev.example.SIGEPA.usuario.service;

import dev.example.SIGEPA.usuario.dto.UsuarioCreateDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioDTO;
import dev.example.SIGEPA.usuario.dto.UsuarioUpdateDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criar(UsuarioCreateDTO dto);

    UsuarioDTO buscarPorId(Long id);

    List<UsuarioDTO> listarTodos();

    UsuarioDTO actualizar(Long id, UsuarioUpdateDTO dto);

    void desactivar(Long id);
}