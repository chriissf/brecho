package com.brecho.SistemasVendas.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendaDto {

    private Long id;
    private Date date;

    @NotNull
    private Long pagamentoId;

}
