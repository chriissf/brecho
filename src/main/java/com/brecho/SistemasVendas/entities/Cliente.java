package com.brecho.SistemasVendas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String telefone;


    private Date dataDeAniversario;


    private String email;


    private String endereco;



}
