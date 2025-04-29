package com.brecho.SistemasVendas.mappers;

import com.brecho.SistemasVendas.dtos.PagamentoDto;
import com.brecho.SistemasVendas.entities.Pagamento;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoMapper {
    public Pagamento convertDtoToEntity(PagamentoDto source) {

        var pagamento = new Pagamento();
        BeanUtils.copyProperties(source, pagamento);
        return pagamento;


    }

    public PagamentoDto convertEntityToDto(Pagamento source) {

        var pagamento = new PagamentoDto();
        BeanUtils.copyProperties(source, pagamento);
        return pagamento;


    }


}
