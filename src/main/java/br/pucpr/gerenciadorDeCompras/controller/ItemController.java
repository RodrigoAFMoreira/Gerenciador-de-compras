package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.ItemDTO;
import br.pucpr.gerenciadorDeCompras.model.Item;
import br.pucpr.gerenciadorDeCompras.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    @GetMapping
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(item -> ResponseEntity.ok(toDTO(item)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ItemDTO> save(@RequestBody ItemDTO dto) {
        Item item = fromDTO(dto);
        item = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(item));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemDTO dto) {
        Optional<Item> opt = itemRepository.findById(id);

        if (opt.isPresent()) {
            Item existing = opt.get();
            existing.setNome(dto.getNome());
            existing.setDataDeValidade(dto.getDataDeValidade());
            existing.setCategoria(dto.getCategoria());

            Item saved = itemRepository.save(existing);
            return ResponseEntity.ok(toDTO(saved));
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Item> opt = itemRepository.findById(id);

        if (opt.isPresent()) {
            itemRepository.delete(opt.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    private Item fromDTO(ItemDTO dto) {
        return Item.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .dataDeValidade(dto.getDataDeValidade())
                .categoria(dto.getCategoria())
                .build();
    }

    private ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getId(), item.getNome(),
                item.getDataDeValidade(), item.getCategoria());
    }
}