package com.brecho.SistemasVendas.mappers;

import com.brecho.SistemasVendas.dtos.VendaDto;
import com.brecho.SistemasVendas.entities.Venda;
import com.brecho.SistemasVendas.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendaMapper {

    private final PagamentoService pagamentoService;

    public Venda convertDtoToEntity(VendaDto source) {
        var target = new Venda();
        BeanUtils.copyProperties(source, target);

        target.setPagamento(pagamentoService.findPagamentoById(source.getPagamentoId()));
        return target;
    }

    public VendaDto convertEntityToDto(Venda source) {
        var target = new VendaDto();
        BeanUtils.copyProperties(source, target);

        target.setPagamentoId(source.getPagamento().getId());
        return target;
    }
}
