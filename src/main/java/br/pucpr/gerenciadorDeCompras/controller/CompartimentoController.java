package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.CompartimentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/compartimentos")
public class CompartimentoController {

    private final List<CompartimentoDTO> compartimentos = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<CompartimentoDTO>> findAll() {
        return ResponseEntity.ok(compartimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompartimentoDTO> findById(@PathVariable Long id) {
        return compartimentos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompartimentoDTO> save(@RequestBody CompartimentoDTO compartimento) {
        compartimento.setId(idGenerator.getAndIncrement());
        compartimentos.add(compartimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(compartimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompartimentoDTO> update(@PathVariable Long id, @RequestBody CompartimentoDTO updated) {
        Optional<CompartimentoDTO> existingOpt = compartimentos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (existingOpt.isPresent()) {
            CompartimentoDTO existing = existingOpt.get();
            existing.setNome(updated.getNome());
            existing.setItemId(updated.getItemId());
            return ResponseEntity.ok(existing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = compartimentos.removeIf(c -> c.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
