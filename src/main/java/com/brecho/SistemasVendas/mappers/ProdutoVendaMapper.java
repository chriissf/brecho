package com.brecho.SistemasVendas.mappers;

import com.brecho.SistemasVendas.dtos.ProdutoVendaDto;
import com.brecho.SistemasVendas.entities.ProdutoVenda;
import com.brecho.SistemasVendas.services.ProdutoServices;
import com.brecho.SistemasVendas.services.VendasService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoVendaMapper {


    private final ProdutoServices produtoService;
    private final VendasService vendasService;


    public ProdutoVenda convertDtoToEntity(ProdutoVendaDto source) {
        var target = new ProdutoVenda();
        BeanUtils.copyProperties(source, target);



        // Buscando as entidades a partir dos IDs fornecidos no DTO
        target.setProduto(produtoService.findById(source.getProdutoId()));
        target.setVenda(vendasService.findVendaById(source.getVendaId()));


        // Atribuindo os campos extras (como nome e CPF)

        target.setCpf(source.getCpf());

        return target;
    }

    public ProdutoVendaDto convertEntityToDto(ProdutoVenda source) {
        var target = new ProdutoVendaDto();
        BeanUtils.copyProperties(source, target);

        // Atribuindo os IDs das entidades ao DTO
        target.setProdutoId(source.getProduto().getId());
        target.setVendaId(source.getVenda().getId());


        return target;
    }
}
