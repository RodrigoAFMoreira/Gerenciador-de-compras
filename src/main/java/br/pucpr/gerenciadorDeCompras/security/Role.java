package br.pucpr.gerenciadorDeCompras.security;

//A classe Role no pacote br.pucpr.gerenciadorDeCompras.security é um enum Java que define os papéis (roles)
// disponíveis para usuários na aplicação, usados no contexto de autenticação e autorização com JWT (JSON Web Token).
// Ela é referenciada em outras classes, como JwtAuthFilter, para atribuir permissões aos usuários autenticados.

public enum Role {
    ADMIN,
    USER
}
