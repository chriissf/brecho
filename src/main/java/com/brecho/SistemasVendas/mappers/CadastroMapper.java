package com.brecho.SistemasVendas.mappers;

import com.brecho.SistemasVendas.dtos.CadastroDto;
import com.brecho.SistemasVendas.entities.Cadastro;
import com.brecho.SistemasVendas.entities.User;
import com.brecho.SistemasVendas.services.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CadastroMapper {

    private final AuthorizationService authorizationService;

    // Converte CadastroDto para Cadastro (Entidade)
    public Cadastro convertDtoToEntity(CadastroDto dto) {
        if (dto == null) {
            return null; // Evita NullPointerException
        }

        var cadastro = new Cadastro();
        BeanUtils.copyProperties(dto, cadastro);  // Copia as propriedades de dto para cadastro
        return cadastro;
    }

    // Converte Cadastro (Entidade) para CadastroDto
    public CadastroDto convertEntityToDto(Cadastro entity) {
        if (entity == null) {
            return null; // Evita NullPointerException
        }

        var cadastroDto = new CadastroDto();
        BeanUtils.copyProperties(entity, cadastroDto);  // Copia as propriedades de cadastro para cadastroDto
        return cadastroDto;
    }
}
