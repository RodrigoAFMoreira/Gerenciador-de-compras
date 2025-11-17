package br.pucpr.gerenciadorDeCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.pucpr.gerenciadorDeCompras.model")  // Escaneia as entities
@EnableJpaRepositories(basePackages = "br.pucpr.gerenciadorDeCompras.repository")  // Escaneia os repos
public class GerenciadorDeComprasApplication {
    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeComprasApplication.class, args);
    }
}