package br.pucpr.gerenciadorDeCompras.repository;


import br.pucpr.gerenciadorDeCompras.model.Compartimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompartimentoRepository extends JpaRepository<Compartimento, Long> { }