package com.brecho.SistemasVendas.services;


import com.brecho.SistemasVendas.dtos.ProdutoVendaDto;
import com.brecho.SistemasVendas.entities.ProdutoVenda;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.ProdutoVendaMapper;
import com.brecho.SistemasVendas.repositories.ProdutoVendaRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProdutoVendaService {

    private final ProdutoVendaRepositories produtoVendaRepositories;
    private final ProdutoVendaMapper mapper;
    private final ProdutoServices produtoService;
    private final VendasService vendasService;

    public ProdutoVendaDto findById(Long id) {
        if (Objects.isNull(id)) {
            throw new AppException("ID do produto de venda não pode ser nulo.");
        }

        var entity = produtoVendaRepositories.findById(id)
                .orElseThrow(() -> new AppException("Produto venda com id %d não existe na base de dados.".formatted(id)));

        return mapper.convertEntityToDto(entity);
    }

    public List<ProdutoVendaDto> findAll() {
        return produtoVendaRepositories.findAll()
                .stream()
                .map(p -> mapper.convertEntityToDto(p))
                .toList();
    }

    @Transactional
    public ProdutoVendaDto create(ProdutoVendaDto dto) {
        // Buscando as entidades a partir dos IDs fornecidos no DTO
        var produto = produtoService.findById(dto.getProdutoId());
        var venda = vendasService.findVendaById(dto.getVendaId());

        // Verificando se o estoque é suficiente
        if (produto.getEstoque() < dto.getQuantidade()) {
            throw new AppException("Estoque insuficiente para o produto " + produto.getNome());
        }

        // Atualizando o estoque do produto após a venda
        produto.setEstoque(produto.getEstoque() - dto.getQuantidade());

        if (dto.getDesconto() > 0)
        produto.setPreco(produto.getPreco() - dto.getDesconto());

        // Salvando o produto com o estoque atualizado
        produtoService.update(produto.getId(), produtoService.mapper().convertEntityToDto(produto));

        // Criando a entidade ProdutoVenda
        var produtoVenda = new ProdutoVenda();
        produtoVenda.setQuantidade(dto.getQuantidade());
        produtoVenda.setProduto(produto);
        produtoVenda.setVenda(venda);
        produtoVenda.setDesconto(dto.getDesconto());
        produtoVenda.setCpf(dto.getCpf());


        // Salvando e retornando a resposta mapeada para DTO
        return mapper.convertEntityToDto(produtoVendaRepositories.save(produtoVenda));
    }


    public ProdutoVendaDto update(Long id, ProdutoVendaDto dto) {
        // Validando se o ProdutoVenda existe
        findById(id);

        // Buscando as entidades a partir dos IDs
        var produto = produtoService.findById(dto.getProdutoId());
        var venda = vendasService.findVendaById(dto.getVendaId());

        // Atualizando os campos da entidade ProdutoVenda
        var produtoVenda = produtoVendaRepositories.findById(id).get();
        produtoVenda.setQuantidade(dto.getQuantidade());

        produtoVenda.setProduto(produto);
        produtoVenda.setVenda(venda);
        produtoVenda.setDesconto(dto.getDesconto());
        produtoVenda.setCpf(dto.getCpf());

        // Salvando e retornando a resposta mapeada para DTO
        return mapper.convertEntityToDto(produtoVendaRepositories.save(produtoVenda));
    }

    public void delete(Long id) {
        findById(id);  // Valida se o ProdutoVenda existe
        produtoVendaRepositories.deleteById(id);  // Deleta o ProdutoVenda
    }
}
