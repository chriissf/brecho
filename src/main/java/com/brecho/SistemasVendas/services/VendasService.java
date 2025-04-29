package com.brecho.SistemasVendas.services;

import com.brecho.SistemasVendas.dtos.ProdutoDto;
import com.brecho.SistemasVendas.dtos.VendaDto;
import com.brecho.SistemasVendas.entities.Pagamento;
import com.brecho.SistemasVendas.entities.Produto;
import com.brecho.SistemasVendas.entities.Venda;
import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.mappers.VendaMapper;
import com.brecho.SistemasVendas.repositories.ProdutoRepository;
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
    private final VendaMapper vendaMapper;
    public Venda findVendaById(Long id){

        if(Objects.isNull(id))
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
        var entity = vendaMapper.convertDtoToEntity(dto);
        return vendaMapper.convertEntityToDto(vendaRepoitory.save(entity));
    }


    public VendaDto update(Long id, VendaDto dto){
        Venda target = findVendaById(id);
        var source = vendaMapper.convertDtoToEntity(dto);




        target.setDate(source.getDate());
        target.setPagamento(source.getPagamento());


        return vendaMapper.convertEntityToDto(vendaRepoitory.save(target));
    }


    public void delete(Long id){
        findVendaById(id);  // Verifica se o produto existe
        vendaRepoitory.deleteById(id);  // Deleta o produto
    }
}

