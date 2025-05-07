package com.brecho.SistemasVendas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricaoDoProduto;


    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Double preco;
    private Integer estoque;

    private String imgUrl;
}
