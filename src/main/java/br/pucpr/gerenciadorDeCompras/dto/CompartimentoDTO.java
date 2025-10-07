package br.pucpr.gerenciadorDeCompras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompartimentoDTO {
    private Long id;        // ID do compartimento
    private String nome;    // Nome do compartimento (ex: Geladeira, Armário)
    private Long itemId;    // ID do Item que está dentro do compartimento (pode ser null)
}
