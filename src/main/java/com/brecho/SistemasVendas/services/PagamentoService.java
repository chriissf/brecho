package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.PagamentoDto;
import com.brecho.SistemasVendas.entities.Pagamento;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.PagamentoMapper;
import com.brecho.SistemasVendas.repositories.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper mapper;

    public Pagamento findPagamentoById(Long id) {
        if (id == null) {
            throw new AppException("O ID informado é nulo.");
        }

        return pagamentoRepository
                .findById(id)
                .orElseThrow(() -> new AppException("O ID %d não existe na tabela de pagamentos.".formatted(id)));
    }

    public List<PagamentoDto> findAll() {
        return pagamentoRepository.findAll()
                .stream()
                .map(mapper::convertEntityToDto)
                .collect(toList());
    }

    public PagamentoDto create(PagamentoDto dto){
        var entity = mapper.convertDtoToEntity(dto);
        return mapper.convertEntityToDto(pagamentoRepository.save(entity));
    }

    public void delete(Long id){
        findPagamentoById(id);
        pagamentoRepository.deleteById(id);
    }


}
