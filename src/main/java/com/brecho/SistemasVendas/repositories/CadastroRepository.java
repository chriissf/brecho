package com.brecho.SistemasVendas.repositories;

import com.brecho.SistemasVendas.entities.Cadastro;
import com.brecho.SistemasVendas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface CadastroRepository extends JpaRepository<Cadastro, String> {
    Optional<Cadastro> findByUser(User user);
    Optional<Cadastro> findByEmail(String email);
    List<Cadastro> findByNomeContaining(String nome);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
}
