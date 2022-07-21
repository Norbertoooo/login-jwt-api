package com.vitu.service;

import com.vitu.domain.Papel;
import com.vitu.domain.Usuario;
import com.vitu.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PapelService papelService;

    private final PasswordEncoder passwordEncoder;

    public List<Usuario> obterUsuarios() {
        return usuarioRepository.findAll();
    }

    public Boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow();
    }

    public Usuario obterPorEmailEhSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha).orElseThrow();
    }

    public Usuario criar(String email, String senha, String papel) {

        Papel papelEncontrado = papelService.obterPapelPeloNome(papel);

        Usuario usuario = new Usuario().create(email, passwordEncoder.encode(senha), papelEncontrado);

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        Usuario usuarioEncontrado = obterPorEmail(usuario.getEmail());
        usuario.setId(usuarioEncontrado.getId());
        return usuarioRepository.save(usuario);
    }

    public Long excluir(String email) {
        if (Boolean.TRUE.equals(existePorEmail(email))) {
           return usuarioRepository.deleteByEmail(email);
        } else {
            throw new RuntimeException("email não encontrado");
        }
    }
}
