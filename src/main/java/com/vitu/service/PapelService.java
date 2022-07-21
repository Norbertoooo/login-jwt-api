package com.vitu.service;

import com.vitu.domain.Papel;
import com.vitu.repository.PapelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PapelService {

    private final PapelRepository papelRepository;

    public Papel obterPapelPeloNome(String nome) {
        return papelRepository.findByNome(nome).orElseThrow();
    }

    public List<Papel> obterPapeis() {
        return papelRepository.findAll();
    }
}
