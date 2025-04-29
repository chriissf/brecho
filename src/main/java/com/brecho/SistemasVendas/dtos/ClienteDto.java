package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String telefone;

    @NotNull
    private Date dataDeAniversario;

    @NotNull
    private String email;

    @NotNull
    private String endereco;

}
