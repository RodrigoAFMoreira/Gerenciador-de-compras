package br.pucpr.gerenciadorDeCompras.security;

import java.util.Date;

//A classe AuthResponse no pacote br.pucpr.gerenciadorDeCompras.security é um DTO (Data Transfer Object) que encapsula
//a resposta de autenticação retornada ao cliente após um login bem-sucedido em uma aplicação que utiliza autenticação JWT
//(JSON Web Token). Ela é usada no contexto do LoginController e do AuthService, conforme mencionado anteriormente.

public class AuthResponse {

    private String email;
    private String token;
    private Date expires;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}

