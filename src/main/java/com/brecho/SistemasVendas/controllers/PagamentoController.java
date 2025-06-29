package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.PagamentoDto;
import com.brecho.SistemasVendas.services.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {


    private final PagamentoService service;

    @GetMapping
    public ResponseEntity<List<PagamentoDto>> findAll() {

        return ResponseEntity.ok(service.findAll());

    }

    @PostMapping
    public ResponseEntity<PagamentoDto> create(@RequestBody @Valid PagamentoDto dto) {

        return ResponseEntity.ok(service.create(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
