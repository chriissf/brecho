package com.brecho.SistemasVendas.dtos;

public record CadastroRequestDTO(  String nome,
                                   String email,
                                   String telefone,
                                   String endereco) {
}
