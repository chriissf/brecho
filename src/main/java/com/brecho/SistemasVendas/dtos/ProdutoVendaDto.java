package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoVendaDto {

    private Long id;  // O ID é opcional em um DTO, mas pode ser útil para atualização

    @NotNull(message = "Quantidade é obrigatória")
    private int quantidade;  // Quantidade do produto

    @NotNull(message = "Cliente ID é obrigatório")
    private Long clienteId;  // Apenas o ID do cliente

    private String nomeCliente;  // Nome do cliente, pode ser opcional dependendo da lógica

    private String cpf;  // CPF do cliente

    private Double desconto;  // Desconto aplicado na venda

    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;  // Apenas o ID do produto

    @NotNull(message = "Venda ID é obrigatório")
    private Long vendaId;  // Apenas o ID da venda
}
