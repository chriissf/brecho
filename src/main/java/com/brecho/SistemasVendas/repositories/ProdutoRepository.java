package com.brecho.SistemasVendas.repositories;

import com.brecho.SistemasVendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto,Long> {


}
