package br.pucpr.gerenciadorDeCompras.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "itens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeValidade;

    @Column(nullable = false)
    private String categoria;
}