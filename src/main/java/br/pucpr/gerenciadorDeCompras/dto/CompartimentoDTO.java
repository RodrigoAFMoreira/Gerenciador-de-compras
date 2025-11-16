package br.pucpr.gerenciadorDeCompras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompartimentoDTO {
    private Long id;
    private String nome;
    private Long itemId;
}
