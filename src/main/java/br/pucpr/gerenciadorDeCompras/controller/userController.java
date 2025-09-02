package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.dto.userDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class userController {
    private final List<userDTO> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<userDTO>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping
    public ResponseEntity<userDTO>
    save(@RequestBody userDTO user) {
        users.add(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user); }

    @PutMapping("/{id}")
    public ResponseEntity<userDTO> update(@PathVariable Long id, @RequestBody userDTO updatedUser) {
        Optional<userDTO> existingUserOpt = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (existingUserOpt.isPresent()) {
            userDTO existingUser = existingUserOpt.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(existingUser);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));

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