package br.pucpr.gerenciadorDeCompras.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compartimentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compartimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}