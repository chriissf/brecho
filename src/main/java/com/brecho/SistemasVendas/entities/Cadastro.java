package com.brecho.SistemasVendas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Entity
@Table(name = "cadastros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull
    private User user;

    private String telefone;
    private Date dataDeAniversario;

    @Email
    private String email;

    private String endereco;

    // Adicione este campo para o método findByNomeContaining funcionar
    @Column(nullable = false)
    private String nome;
}
