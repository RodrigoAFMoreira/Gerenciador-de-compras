package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/itens")
public class ItemController {
    private final List<ItemDTO> itens = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itens);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> save(@RequestBody ItemDTO item) {
        itens.add(item);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(item);
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

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(existingItem);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = itens.removeIf(i -> i.getId().equals(id));

        if (removed) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}