package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.VendaDto;
import com.brecho.SistemasVendas.entities.Produto;
import com.brecho.SistemasVendas.entities.ProdutoVenda;
import com.brecho.SistemasVendas.entities.Venda;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.VendaMapper;
import com.brecho.SistemasVendas.repositories.ProdutoRepository;
import com.brecho.SistemasVendas.repositories.ProdutoVendaRepositories;
import com.brecho.SistemasVendas.repositories.VendaRepoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VendasService {

    private final VendaRepoitory vendaRepoitory;
    private final ProdutoRepository produtoRepository;
    private final ProdutoVendaRepositories produtoVendaRepository;
    private final VendaMapper vendaMapper;

    public Venda findVendaById(Long id){
        if (Objects.isNull(id))
            throw new AppException("O Id %d não existe na tabela venda".formatted(id));
        return vendaRepoitory
                .findById(id)
                .orElseThrow(() -> new AppException("O id %d não existe na tabela venda.".formatted(id)));
    }

    public VendaDto findOne(Long id){
        return vendaMapper.convertEntityToDto(findVendaById(id));
    }

    public List<VendaDto> findAll() {
        return vendaRepoitory.findAll()
                .stream()
                .map(vendaMapper::convertEntityToDto)
                .collect(toList());
    }

    public VendaDto create(VendaDto dto){
        Venda venda = vendaMapper.convertDtoToEntity(dto);

        // Salva a venda para garantir que ela tenha um ID
        Venda vendaSalva = vendaRepoitory.save(venda);

        for (ProdutoVenda item : venda.getProdutosVendidos()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new AppException("Produto com ID " + item.getProduto().getId() + " não encontrado."));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new AppException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualiza o estoque
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            // Associa a venda salva ao item e persiste o ProdutoVenda
            item.setVenda(vendaSalva);
            produtoVendaRepository.save(item);
        }

        return vendaMapper.convertEntityToDto(vendaSalva);
    }

    public VendaDto update(Long id, VendaDto dto){
        Venda target = findVendaById(id);
        var source = vendaMapper.convertDtoToEntity(dto);

        target.setDate(source.getDate());
        target.setPagamento(source.getPagamento());

        return vendaMapper.convertEntityToDto(vendaRepoitory.save(target));
    }

    public void delete(Long id){
        findVendaById(id);  // Verifica se a venda existe
        vendaRepoitory.deleteById(id);  // Deleta a venda
    }
}
