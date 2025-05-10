package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.CategoriaDto;
import com.brecho.SistemasVendas.entities.Categoria;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.CategoriaMapper;
import com.brecho.SistemasVendas.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper mapper;

    public Categoria findById(Long id) {
        if (id == null) {
            throw new AppException("O ID da categoria é nulo");
        }
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new AppException("Categoria com ID %d não encontrada.".formatted(id)));
    }

    public CategoriaDto findOne(Long id) {
        return mapper.convertEntityToDto(findById(id));
    }

    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(mapper::convertEntityToDto)
                .collect(toList());
    }


    @Transactional
    public CategoriaDto create(CategoriaDto dto) {
        if (dto == null) {
            throw new AppException("Categoria informada está nula");
        }

        log.info("Criando categoria com dados: {}", dto);
        var entity = mapper.convertDtoToEntity(dto);
        var saved = categoriaRepository.save(entity);
        return mapper.convertEntityToDto(saved);
    }
    @Transactional
    public CategoriaDto update(Long id, CategoriaDto dto) {
        if (id == null || dto == null) {
            throw new AppException("ID ou dados da categoria são nulos.");
        }

        log.info("Atualizando categoria ID {} com dados: {}", id, dto);
        var existing = findById(id);
        var entity = mapper.convertDtoToEntity(dto);
        entity.setId(existing.getId());
        var updated = categoriaRepository.save(entity);
        return mapper.convertEntityToDto(updated);
    }
    @Transactional
    public void delete(Long id) {
        log.info("Removendo categoria com ID: {}", id);
        findById(id);
        categoriaRepository.deleteById(id);
    }
}
