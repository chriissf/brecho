package com.brecho.SistemasVendas.dtos;

public record CadastroResponseDTO(
        String nomeCompleto,
        String Email,
        String telefone,
        String endereco
) {}
