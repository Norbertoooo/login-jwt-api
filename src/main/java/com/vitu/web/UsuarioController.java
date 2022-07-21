package com.vitu.web;

import com.vitu.domain.Usuario;
import com.vitu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obterUsuarios() {
        return usuarioService.obterUsuarios();
    }

    @PostMapping("/admin")
    public Usuario criarUsuarioAdministrador(@RequestHeader String email, @RequestHeader String senha) {
        return usuarioService.criar(email, senha, "ROLE_ADMINISTRADOR");
    }

    @PostMapping
    public Usuario criarUsuario(@RequestHeader String email, @RequestHeader String senha) {
        return usuarioService.criar(email, senha, "ROLE_USUARIO");
    }

}
