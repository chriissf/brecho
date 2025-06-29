package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoVendaDto {

    private Long id;  // O ID é opcional em um DTO, mas pode ser útil para atualização

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private int quantidade;


    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @DecimalMin(value = "0.0", inclusive = true, message = "O desconto não pode ser negativo")
    private Double desconto;

    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;

    @NotNull(message = "Venda ID é obrigatório")
    private Long vendaId;
}
