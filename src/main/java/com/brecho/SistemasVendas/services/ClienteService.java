package com.brecho.SistemasVendas.services;


import com.brecho.SistemasVendas.dtos.ClienteDto;
import com.brecho.SistemasVendas.entities.Cliente;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;




    // 🔍 Lista todos os clientes
    public List<Cliente> listarClientes() {


        return clienteRepository.findAll();
    }


    @Transactional
    // 🔍 Cria e retorna um ClienteDto
    public ClienteDto create(ClienteDto dto) {
        // Converte o DTO para a entidade Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataDeAniversario(dto.getDataDeAniversario());

        // Salva o cliente no banco de dados
        Cliente salvo = clienteRepository.save(cliente);

        // Cria e retorna o DTO com os dados do cliente salvo
        ClienteDto retorno = new ClienteDto();
        retorno.setId(salvo.getId());
        retorno.setNome(salvo.getNome());
        retorno.setTelefone(salvo.getTelefone());
        retorno.setEmail(salvo.getEmail());
        retorno.setEndereco(salvo.getEndereco());
        retorno.setDataDeAniversario(salvo.getDataDeAniversario());

        return retorno;
    }

   // Processa um lote de ClienteDto e os salva
    public List<ClienteDto> salvarLote(List<ClienteDto> dtos) {
        return dtos.stream()
                .map(this::create) // Reutiliza o método que cria um ClienteDto
                .toList();

    }


    @Transactional
    // 💾 Salva um novo cliente
    public void salvar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // 🔎 Verifica se e-mail já existe
    public boolean emailExiste(String email) {
        return clienteRepository.existsByEmail(email);
    }

    // 🔎 Verifica se telefone já existe
    public boolean telefoneExiste(String telefone) {
        return clienteRepository.existsByTelefone(telefone);
    }

    // 🔍 Busca cliente por ID (lança exceção se não encontrar)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new AppException("Cliente com ID " + id + " não encontrado."));
    }

    // 🔍 Busca lista de clientes por nome
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContaining(nome);
        if (clientes.isEmpty()) {
            throw new AppException("Nenhum cliente encontrado com o nome " + nome);
        }
        return clientes;
    }

    // 🔍 Busca um cliente por email
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Cliente com e-mail " + email + " não encontrado."));
    }
    @Transactional
    // ❌ Deleta cliente por ID
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new AppException("Cliente com ID " + id + " não encontrado para deletar.");
        }
        clienteRepository.deleteById(id);
    }

    @Transactional
    // 📝 Criar um novo cliente
    public Cliente criarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }
}
