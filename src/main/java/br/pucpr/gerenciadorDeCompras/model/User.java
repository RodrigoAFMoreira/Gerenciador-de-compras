package br.pucpr.gerenciadorDeCompras.model;

import br.pucpr.gerenciadorDeCompras.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password; // Deve ser encoded com BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}