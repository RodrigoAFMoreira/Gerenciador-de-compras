package br.pucpr.gerenciadorDeCompras.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

    @Service
    @AllArgsConstructor
    public class AuthService {

        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final UserDetailsService userDetailsService;

        public AuthResponse authenticate(AuthRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            String jwtToken = jwtService.generateToken(userDetails);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtToken);
            authResponse.setEmail(request.getEmail());
            authResponse.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 24));

            return authResponse;
        }
    }