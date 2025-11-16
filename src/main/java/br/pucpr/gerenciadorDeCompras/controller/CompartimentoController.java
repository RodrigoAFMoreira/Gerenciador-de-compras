package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.CompartimentoDTO;
import br.pucpr.gerenciadorDeCompras.model.Compartimento;
import br.pucpr.gerenciadorDeCompras.model.Item;
import br.pucpr.gerenciadorDeCompras.repository.CompartimentoRepository;
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
@RequestMapping("/api/v1/compartimentos")
@RequiredArgsConstructor
public class CompartimentoController {

    private final CompartimentoRepository compartimentoRepository;
    private final ItemRepository itemRepository;

    @GetMapping
    public List<CompartimentoDTO> findAll() {
        return compartimentoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompartimentoDTO> findById(@PathVariable Long id) {
        return compartimentoRepository.findById(id)
                .map(c -> ResponseEntity.ok(toDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CompartimentoDTO> save(@RequestBody CompartimentoDTO dto) {
        Compartimento c = fromDTO(dto);
        c = compartimentoRepository.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(c));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompartimentoDTO> update(@PathVariable Long id, @RequestBody CompartimentoDTO dto) {
        return compartimentoRepository.findById(id)
                .map(existing -> {
                    existing.setNome(dto.getNome());
                    existing.setItem(dto.getItemId() != null ?
                            itemRepository.findById(dto.getItemId()).orElse(null) : null);
                    return ResponseEntity.ok(toDTO(compartimentoRepository.save(existing)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Compartimento> opt = compartimentoRepository.findById(id);
        if (opt.isPresent()) {
            compartimentoRepository.delete(opt.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Compartimento fromDTO(CompartimentoDTO dto) {
        Item item = dto.getItemId() != null ? itemRepository.findById(dto.getItemId()).orElse(null) : null;
        return Compartimento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .item(item)
                .build();
    }

    private CompartimentoDTO toDTO(Compartimento c) {
        Long itemId = c.getItem() != null ? c.getItem().getId() : null;
        return new CompartimentoDTO(c.getId(), c.getNome(), itemId);
    }
}