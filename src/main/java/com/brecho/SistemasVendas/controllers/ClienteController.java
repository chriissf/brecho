package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.ClienteDto;
import com.brecho.SistemasVendas.entities.Cliente;
import com.brecho.SistemasVendas.services.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor // injeta dependência pelo construtor (boa prática)
public class ClienteController {

    private final ClienteService service;

    // ✅ Lista todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.listarClientes());
    }

    // ✅ Cadastra um cliente (verifica apenas e-mail e telefone)
    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Cliente cliente) {
        boolean emailJaCadastrado = service.emailExiste(cliente.getEmail());
        boolean telefoneJaCadastrado = service.telefoneExiste(cliente.getTelefone());

        if (!emailJaCadastrado && !telefoneJaCadastrado) {
            service.salvar(cliente);
            return ResponseEntity.ok("Cliente cadastrado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("E-mail ou telefone já estão cadastrados.");
        }
    }

    // ✅ Cria um cliente (adicionando diretamente um cliente)
    @PostMapping("/novo")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = service.criarCliente(cliente);
        return ResponseEntity.status(201).body(clienteCriado);
    }

    // ✅ Busca um cliente por ID ou nome
    @GetMapping("/{idOuNome}")
    public ResponseEntity<List<Cliente>> buscarPorIdOuNome(@PathVariable String idOuNome) {
        try {
            // Tenta converter para Long → busca por ID
            Long id = Long.parseLong(idOuNome);
            Cliente cliente = service.buscarPorId(id);
            return ResponseEntity.ok(List.of(cliente));
        } catch (NumberFormatException e) {
            // Se não for número → busca por nome
            List<Cliente> clientes = service.buscarPorNome(idOuNome);
            if (clientes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(clientes);
        }
    }

    // ✅ Busca um cliente por e-mail
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarPorEmail(@PathVariable String email) {
        try {
            Cliente cliente = service.buscarPorEmail(email);
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Deleta cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletarCliente(id);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Salva um lote de clientes
    @PostMapping("/clientes/lote")
    public ResponseEntity<List<ClienteDto>> salvarLote(@RequestBody List<ClienteDto> dtos) {
        List<ClienteDto> clientesSalvos = service.salvarLote(dtos);
        return ResponseEntity.status(201).body(clientesSalvos);
    }
}
