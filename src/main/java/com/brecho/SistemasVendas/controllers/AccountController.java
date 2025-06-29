package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.CadastroRequestDTO;
import com.brecho.SistemasVendas.dtos.CadastroResponseDTO;
import com.brecho.SistemasVendas.dtos.UserResponseDTO;
import com.brecho.SistemasVendas.entities.Cadastro;
import com.brecho.SistemasVendas.entities.User;
import com.brecho.SistemasVendas.repositories.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CadastroRepository cadastroRepository;

    /**
     * Retorna os dados básicos do usuário autenticado (id, login e role).
     * Endpoint: GET /account/me
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUsuarioAutenticado(@AuthenticationPrincipal User user) {
        var dto = new UserResponseDTO(
                user.getId(),
                user.getLogin(),
                user.getRole()
        );

        return ResponseEntity.ok(dto);
    }

    /**
     * Retorna os dados de cadastro vinculados ao usuário autenticado.
     * Endpoint: GET /account/me/cadastro
     */
    @GetMapping("/me/cadastro")
    public ResponseEntity<?> getCadastroDoUsuario(@AuthenticationPrincipal User user) {
        var cadastro = user.getCadastro();

        if (cadastro == null) {
            return ResponseEntity.noContent().build(); // Retorna 204 se cadastro não existir
        }

        var dto = new CadastroResponseDTO(
                cadastro.getNome(),
                cadastro.getEmail(),
                cadastro.getTelefone(),
                cadastro.getEndereco()
        );

        return ResponseEntity.ok(dto);
    }

    /**
     * Cria o cadastro vinculado ao usuário autenticado.
     * Endpoint: POST /account/cadastro
     */
    @PostMapping("/cadastro")
    public ResponseEntity<?> criarCadastro(
            @RequestBody CadastroRequestDTO data,
            @AuthenticationPrincipal User user
    ) {
        if (user.getCadastro() != null || cadastroRepository.findByUser(user).isPresent()) {
            return ResponseEntity.status(409).body("Usuário já possui cadastro.");
        }

        var cadastro = new Cadastro();
        cadastro.setNome(data.nome());
        cadastro.setEmail(data.email());
        cadastro.setTelefone(data.telefone());
        cadastro.setEndereco(data.endereco());
        cadastro.setUser(user);

        cadastroRepository.save(cadastro);

        return ResponseEntity.status(201).body("Cadastro criado com sucesso.");
    }
}
