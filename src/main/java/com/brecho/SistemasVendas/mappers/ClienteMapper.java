package com.brecho.SistemasVendas.mappers;

import com.brecho.SistemasVendas.dtos.ClienteDto;
import com.brecho.SistemasVendas.dtos.PagamentoDto;
import com.brecho.SistemasVendas.entities.Cliente;
import com.brecho.SistemasVendas.entities.Pagamento;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

        public Cliente convertDtoToEntity(ClienteDto source) {

            var cliente = new Cliente();
            BeanUtils.copyProperties(source, cliente);
            return cliente;
        }

        public ClienteDto convertEntityToDto(Cliente source) {

            var cliente = new ClienteDto();
            BeanUtils.copyProperties(source, cliente);
            return cliente;
        }
    }

