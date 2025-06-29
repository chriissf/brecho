/*package com.brecho.SistemasVendas.config;

import com.brecho.SistemasVendas.entities.Cliente;
import com.brecho.SistemasVendas.entities.Pagamento;
import com.brecho.SistemasVendas.entities.Produto;
import com.brecho.SistemasVendas.repositories.ClienteRepository;
import com.brecho.SistemasVendas.repositories.PagamentoRepository;
import com.brecho.SistemasVendas.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            ClienteRepository clienteRepository,
            PagamentoRepository pagamentoRepository,
            ProdutoRepository produtoRepository
    ) {
        return args -> {
            // Cliente
            Cliente cliente = new Cliente();
            cliente.setNome("João da Silva");
            cliente.setTelefone("11999999999");
            cliente.setEmail("joao@email.com");
            cliente.setEndereco("Rua A, 123");
            cliente.setDataDeAniversario(new Date());
            clienteRepository.save(cliente);

            // Pagamento
            Pagamento pagamento = new Pagamento();
            pagamento.setDescricao("Cartão de Crédito");
            pagamentoRepository.save(pagamento);

            // Produto
            Produto produto = new Produto();
            produto.setNome("Camiseta Vintage");
            produto.setDescricaoDoProduto("Camiseta retrô dos anos 80");
            produto.setCategoria("Roupas");
            produto.setPreco(79.90);
            produto.setEstoque(50);
            produto.setImgUrl("http://imagem.com/camiseta.png");
            produtoRepository.save(produto);

            System.out.println("✅ Dados iniciais inseridos com sucesso.");
        };
    }
}*/
