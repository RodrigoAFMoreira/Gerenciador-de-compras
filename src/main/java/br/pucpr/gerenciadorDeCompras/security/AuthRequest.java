package br.pucpr.gerenciadorDeCompras.security;

//A classe AuthRequest no pacote br.pucpr.gerenciadorDeCompras.security é uma classe Java simples que serve como um DTO
//(Data Transfer Object) para encapsular as credenciais de autenticação enviadas pelo cliente em uma requisição de login.
// Ela é usada no contexto da autenticação JWT, conforme referenciado na classe LoginController mencionada anteriormente.

public class AuthRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}