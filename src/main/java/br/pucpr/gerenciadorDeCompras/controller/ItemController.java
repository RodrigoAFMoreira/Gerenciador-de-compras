package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/itens")
public class ItemController {

    private final List<ItemDTO> itens = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1); // Gera IDs automáticos

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable Long id) {
        return itens.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> save(@RequestBody ItemDTO item) {
        item.setId(idGenerator.getAndIncrement()); // Gera ID automático
        itens.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemDTO updatedItem) {
        Optional<ItemDTO> existingItemOpt = itens.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            ItemDTO existingItem = existingItemOpt.get();
            existingItem.setNome(updatedItem.getNome());
            existingItem.setDataDeValidade(updatedItem.getDataDeValidade());
            existingItem.setCategoria(updatedItem.getCategoria());
            return ResponseEntity.ok(existingItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = itens.removeIf(i -> i.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
