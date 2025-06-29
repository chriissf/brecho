package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.CadastroDto;
import com.brecho.SistemasVendas.services.CadastroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CadastroController {

    private final CadastroService cadastroService;

    @GetMapping("/cadastroUsuario")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("cadastroDto", new CadastroDto());
        return "cadastro";
    }

    @PostMapping("/cadastroUsuario")
    public String cadastrarUsuario(@Valid @ModelAttribute("cadastroDto") CadastroDto cadastroDto, Model model) {

        if (cadastroService.emailExiste(cadastroDto.getEmail())) {
            model.addAttribute("error", "E-mail já cadastrado.");
            return "cadastro";
        }

        if (cadastroService.telefoneExiste(cadastroDto.getTelefone())) {
            model.addAttribute("error", "Telefone já cadastrado.");
            return "cadastro";
        }

        try {
            cadastroService.cadastrarNovoUsuario(
                    cadastroDto.getLogin(),
                    cadastroDto.getSenha(),
                    cadastroDto.getRole(),
                    cadastroDto
            );

            model.addAttribute("success", "Cadastro realizado com sucesso!");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar: " + e.getMessage());
            return "cadastro";
        }
    }
}
