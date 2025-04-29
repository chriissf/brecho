package com.brecho.SistemasVendas.repositories;

import com.brecho.SistemasVendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByNomeContaining(String nome);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
}
