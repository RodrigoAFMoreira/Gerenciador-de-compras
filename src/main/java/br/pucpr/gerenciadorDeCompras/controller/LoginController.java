package br.pucpr.gerenciadorDeCompras.controller;

import br.pucpr.gerenciadorDeCompras.security.AuthRequest;
import br.pucpr.gerenciadorDeCompras.security.AuthResponse;
import br.pucpr.gerenciadorDeCompras.security.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//A classe LoginController é um controlador REST que lida com requisições de autenticação na aplicação.
//Ela recebe um pedido de login (credenciais do usuário) e retorna um token JWT encapsulado em um objeto AuthResponse.
//A lógica de autenticação em si é delegada ao serviço AuthService.

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor

public class LoginController {

    private final AuthService authService;

    @PostMapping

    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}