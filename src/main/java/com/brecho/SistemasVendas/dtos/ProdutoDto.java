package com.brecho.SistemasVendas.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoDto {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser null ou vazio")
    private  String nome;

    private  String descricaoDoProduto;
    private  String categoria;
    private  Double preco;

    private  Integer estoque;

    private  String imgUrl;
}
