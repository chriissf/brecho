package com.brecho.SistemasVendas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagamento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String descricao;
}
