package com.vitu.web;

import com.vitu.domain.Papel;
import com.vitu.service.PapelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basic-security")
@RequestMapping("/api/v1/papel")
public class PapelController {

    private final PapelService papelService;
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USUARIO')")
    public List<Papel> obterPapeis() {
        return papelService.obterPapeis();
    }
}
