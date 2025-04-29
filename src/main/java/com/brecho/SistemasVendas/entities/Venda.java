package com.brecho.SistemasVendas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vendas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Venda {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "pagamentos_id")
    private Pagamento pagamento;

}
