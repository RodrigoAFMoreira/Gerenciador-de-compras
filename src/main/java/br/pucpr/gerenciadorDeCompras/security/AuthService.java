package br.pucpr.gerenciadorDeCompras.security;

import br.pucpr.gerenciadorDeCompras.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Date;

//A classe AuthService no pacote br.pucpr.gerenciadorDeCompras.security é um serviço Spring responsável por lidar com a
//lógica de autenticação de usuários em uma aplicação que utiliza JWT (JSON Web Token). Ela processa as credenciais
//fornecidas em um objeto AuthRequest e retorna um objeto AuthResponse contendo o token JWT, o e-mail do usuário e a
//data de expiração do token.

@Service
@AllArgsConstructor
public class AuthService {

    //private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
        var user = new User();

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setEmail(request.getEmail());

        var jwtToken = jwtService.generateToken(userAuthentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        authResponse.setEmail(user.getEmail());
        authResponse.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 24));

        return authResponse;
    }


}
