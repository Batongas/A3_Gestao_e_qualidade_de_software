package br.com.labvet.dto;

/**
 * Esta classe é um DTO (Data Transfer Object).
 * Ela não faz nada, apenas serve de "molde" para carregar os dados
 * que vêm do front-end (o JSON).
 */
public class LoginRequest {

    private String login;
    private String senha;

    // Getters e Setters são ESSENCIAIS para o Spring funcionar
    // (O Spring usa eles para preencher os dados do JSON)
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}