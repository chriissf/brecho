package com.brecho.SistemasVendas.controllers;


import com.brecho.SistemasVendas.dtos.CategoriaDto;
import com.brecho.SistemasVendas.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {


    private final CategoriaService service;

    @GetMapping
    public ResponseEntity <List<CategoriaDto>>findAll(){


            return ResponseEntity.ok(service.findAll());

    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> listarPorId(@PathVariable Long id){

            return   ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto>create(@RequestBody CategoriaDto dto){

            var created= service.create(dto);
            return ResponseEntity.status(HttpStatus.OK).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id,@RequestBody CategoriaDto dto){

        var updated = service.update(id, dto);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDto> delete(@PathVariable Long id){

       service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
