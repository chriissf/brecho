package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.PagamentoDto;
import com.brecho.SistemasVendas.dtos.VendaDto;
import com.brecho.SistemasVendas.services.PagamentoService;
import com.brecho.SistemasVendas.services.VendasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendasService service;

    @GetMapping
    public ResponseEntity<List<VendaDto>> findAll() {

        return ResponseEntity.ok(service.findAll());

    }

    @PostMapping
    public ResponseEntity<VendaDto> create(@RequestBody @Valid VendaDto dto) {

        return ResponseEntity.ok(service.create(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
