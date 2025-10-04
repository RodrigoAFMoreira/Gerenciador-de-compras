package br.pucpr.gerenciadorDeCompras.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String nome;
    private LocalDate dataDeValidade;
    private String categoria;
}