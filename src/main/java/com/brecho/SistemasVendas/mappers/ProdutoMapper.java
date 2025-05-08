package com.brecho.SistemasVendas.mappers;
import com.brecho.SistemasVendas.dtos.ProdutoDto;
import com.brecho.SistemasVendas.entities.Produto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto convertDtoToEntity(ProdutoDto source) {

        var produto = new Produto();
        BeanUtils.copyProperties(source, produto);
        return produto;


    }

    public ProdutoDto convertEntityToDto(Produto source) {

        var produto = new ProdutoDto();
        BeanUtils.copyProperties(source, produto);

        if(source.getCategoria()!=null){
            produto.setCategoriaId(source.getCategoria().getId());
            produto.setNomeCategoria(source.getCategoria().getNome());
        }
        return produto;


    }
}
