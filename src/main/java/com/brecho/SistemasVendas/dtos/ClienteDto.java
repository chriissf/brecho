package com.brecho.SistemasVendas.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    @Size(min = 2,max = 100, message = "O nome deve ter entre 2 e 100 cararcteres")
    private String nome;

    @NotBlank(message = "O telefone é obrigatorio")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos  numéricos")
    private String telefone;

    @NotNull(message = "A data de aniversário é obrigatória")
    @Past(message = "A datta de aniversário deve ser no passado")
    private Date dataDeAniversario;


    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(min = 5,max = 200,message = "O endereço deve ter entre 5 e 200 caracteres")
    private String endereco;

}
