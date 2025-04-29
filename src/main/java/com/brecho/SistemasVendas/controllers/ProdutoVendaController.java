package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.ProdutoVendaDto;
import com.brecho.SistemasVendas.services.ProdutoVendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtoVenda")
@RequiredArgsConstructor
public class ProdutoVendaController {

    private final ProdutoVendaService service;

    @GetMapping
    public ResponseEntity<List<ProdutoVendaDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoVendaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoVendaDto> create(@RequestBody @Valid ProdutoVendaDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(201).body(created); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoVendaDto> update(@PathVariable Long id, @RequestBody @Valid ProdutoVendaDto dto) {
        var updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
