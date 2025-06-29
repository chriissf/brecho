package com.brecho.SistemasVendas.controllers;

import com.brecho.SistemasVendas.dtos.ProdutoDto;
import com.brecho.SistemasVendas.services.ProdutoServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoServices service;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }




   /* @PostMapping("/produtos/lote")
    public ResponseEntity<?> salvarLote(@RequestBody List<@Valid ProdutoDto> produtos) {
        List<ProdutoDto> salvos = service.salvarLote(produtos);
        return ResponseEntity.status(201).body(salvos);
    }*/



    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@RequestBody @Valid ProdutoDto dto) {
        var created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody @Valid ProdutoDto dto) {
        var updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
