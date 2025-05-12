package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoDto {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser null ou vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private  String nome;

    @NotBlank(message = "A descrição do produto não pode ser nula ou vazia")
    @Size(min = 2, max = 255, message = "A descrição deve ter entre 2 e 255 caracteres")
    private  String descricaoDoProduto;

    @NotNull(message = "A categoria do produto é obrigatória")
    private Long categoriaId;
    private String nomeCategoria;

    @NotNull(message = "O preço do produto é obrigatório")
    @Positive(message = "O preço deve ser um número positivo")
    private  Double preco;
    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private  Integer estoque;

    private  String imgUrl;
}
