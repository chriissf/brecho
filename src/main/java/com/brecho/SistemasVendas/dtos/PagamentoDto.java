package com.brecho.SistemasVendas.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoDto {


    private Long id;
    @NotNull(message = "Descrição é obrigatória")
    private String descricao;
}
