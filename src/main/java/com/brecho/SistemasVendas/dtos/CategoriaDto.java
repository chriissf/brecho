package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaDto {

    private Long id;
    @NotNull(message = "O nome da Categoria é obrigatorio! ")
    private String nome;
}
