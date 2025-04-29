package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.ProdutoDto;
import com.brecho.SistemasVendas.entities.Produto;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.ProdutoMapper;
import com.brecho.SistemasVendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper mapper;

    public Produto findById(Long id) {
        if (id == null) {
            throw new AppException("O ID informado é nulo.");
        }

        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new AppException("O ID %d não existe na tabela de produtos.".formatted(id)));
    }

    public ProdutoDto findOne(Long id) {
        return mapper.convertEntityToDto(findById(id));
    }

    public List<ProdutoDto> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(mapper::convertEntityToDto)
                .collect(toList());
    }
    public List<ProdutoDto> salvarLote(List<ProdutoDto> produtos) {
        return produtos.stream()
                .map(this::create) // Reutiliza o método que salva um ProdutoDto
                .toList();
    }


    public ProdutoDto create(ProdutoDto dto) {
        if (dto == null) {
            throw new AppException("O produto informado está nulo.");
        }

        log.info("Criando produto com dados: {}", dto);
        var entity = mapper.convertDtoToEntity(dto);
        var savedEntity = produtoRepository.save(entity);
        return mapper.convertEntityToDto(savedEntity);
    }

    public ProdutoDto update(Long id, ProdutoDto dto) {
        if (id == null || dto == null) {
            throw new AppException("ID ou dados do produto são nulos.");
        }

        log.info("Atualizando produto ID {} com dados: {}", id, dto);
        var existing = findById(id);
        var entity = mapper.convertDtoToEntity(dto);
        entity.setId(existing.getId());
        var updated = produtoRepository.save(entity);
        return mapper.convertEntityToDto(updated);
    }

    public void delete(Long id) {
        log.info("Removendo produto com ID: {}", id);
        findById(id);
        produtoRepository.deleteById(id);
    }

    public ProdutoMapper mapper() {
        return this.mapper;
    }
}
