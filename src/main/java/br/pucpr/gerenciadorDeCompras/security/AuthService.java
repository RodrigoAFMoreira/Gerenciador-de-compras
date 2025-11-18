package br.pucpr.gerenciadorDeCompras.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Date;
import br.pucpr.gerenciadorDeCompras.model.User;
import br.pucpr.gerenciadorDeCompras.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        AuthResponse response = new AuthResponse();
        response.setEmail(request.getEmail());
        response.setToken(token);
        response.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)); // 24h
        return response;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        // Build the user
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        // Save to DB
        userRepository.save(user);
        // Generate token like in login
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        AuthResponse response = new AuthResponse();
        response.setEmail(request.getEmail());
        response.setToken(token);
        response.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)); // 24h
        return response;
    }
}