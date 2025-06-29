package com.brecho.SistemasVendas.mappers;


import com.brecho.SistemasVendas.dtos.CategoriaDto;
import com.brecho.SistemasVendas.entities.Categoria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria convertDtoToEntity(CategoriaDto source){

        var categoria = new Categoria();
        BeanUtils.copyProperties( source, categoria);
        return categoria;
    }


    public CategoriaDto convertEntityToDto(Categoria dto){

        var categoriaDto = new CategoriaDto();
        BeanUtils.copyProperties(dto, categoriaDto);
        return categoriaDto;
    }

}
