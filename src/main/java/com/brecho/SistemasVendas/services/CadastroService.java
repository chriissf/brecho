package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.CadastroDto;
import com.brecho.SistemasVendas.entities.Cadastro;
import com.brecho.SistemasVendas.entities.User;
import com.brecho.SistemasVendas.entities.UserRole;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.CadastroMapper;
import com.brecho.SistemasVendas.repositories.CadastroRepository;
import com.brecho.SistemasVendas.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final UserRepository userRepository;
    private final CadastroRepository cadastroRepository;
    private final CadastroMapper cadastroMapper;
    private final PasswordEncoder passwordEncoder;

    // ✅ Lista todos os clientes
    public List<Cadastro> listarClientes() {
        return cadastroRepository.findAll();
    }

    // ✅ Cria um novo usuário + cadastro
    @Transactional
    public Cadastro cadastrarNovoUsuario(String login, String senha, String role, CadastroDto dadosPessoais) {

        // Cria e salva o usuário (tabela users)
        User novoUser = new User();
        novoUser.setLogin(login);
        novoUser.setPassword(passwordEncoder.encode(senha));
        novoUser.setRole(UserRole.valueOf(role.toUpperCase()));
        userRepository.save(novoUser);

        // Converte o DTO para entidade Cadastro
        Cadastro cadastro = cadastroMapper.convertDtoToEntity(dadosPessoais);

        // Vincula o User no Cadastro
        cadastro.setUser(novoUser);

        // Salva o cadastro e retorna
        return cadastroRepository.save(cadastro);
    }

    // ✅ Verifica se o email já está cadastrado
    public boolean emailExiste(String email) {
        return cadastroRepository.existsByEmail(email);
    }

    // ✅ Verifica se o telefone já está cadastrado
    public boolean telefoneExiste(String telefone) {
        return cadastroRepository.existsByTelefone(telefone);
    }

    // ✅ Busca cliente por ID
    public Cadastro buscarPorId(String id) {
        return cadastroRepository.findById(id)
                .orElseThrow(() -> new AppException("Cliente com ID " + id + " não encontrado."));
    }

    // ✅ Busca cliente(s) por nome parcial
    public List<Cadastro> buscarPorNome(String nome) {
        List<Cadastro> cadastros = cadastroRepository.findByNomeContaining(nome);
        if (cadastros.isEmpty()) {
            throw new AppException("Nenhum cliente encontrado com o nome " + nome);
        }
        return cadastros;
    }

    // ✅ Busca cliente por e-mail
    public Cadastro buscarPorEmail(String email) {
        return cadastroRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Cliente com e-mail " + email + " não encontrado."));
    }

    // ✅ Deleta cliente por ID
    @Transactional
    public void deletarCliente(String id) {
        if (!cadastroRepository.existsById(id)) {
            throw new AppException("Cliente com ID " + id + " não encontrado para deletar.");
        }
        cadastroRepository.deleteById(id);
    }

    // ✅ Cria um cliente a partir de DTO (validações incluídas)
    @Transactional
    public CadastroDto criar(CadastroDto dto) {
        Cadastro cadastro = cadastroMapper.convertDtoToEntity(dto);

        if (emailExiste(cadastro.getEmail())) {
            throw new AppException("E-mail já cadastrado.", 400);
        }

        if (telefoneExiste(cadastro.getTelefone())) {
            throw new AppException("Telefone já cadastrado.", 400);
        }

        Cadastro salvo = cadastroRepository.save(cadastro);
        return cadastroMapper.convertEntityToDto(salvo);
    }

    // ✅ Salva um lote de clientes usando DTOs
    @Transactional
    public List<CadastroDto> salvarLote(List<CadastroDto> dtos) {
        return dtos.stream()
                .map(this::criar)
                .toList();
    }
}
