package br.pucpr.gerenciadorDeCompras.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private Integer id;
    private String name;
    private String email;
}