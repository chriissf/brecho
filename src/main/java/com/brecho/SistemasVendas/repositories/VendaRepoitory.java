package com.brecho.SistemasVendas.repositories;

import com.brecho.SistemasVendas.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepoitory extends JpaRepository <Venda, Long> {

}
