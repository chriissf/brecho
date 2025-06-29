package com.brecho.SistemasVendas.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotNull(message = "A data da venda é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @NotNull(message = "O ID do pagamento é obrigatório")
    private Long pagamentoId;

}
