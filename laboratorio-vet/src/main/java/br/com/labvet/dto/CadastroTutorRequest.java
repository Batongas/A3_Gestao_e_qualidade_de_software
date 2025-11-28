package br.com.labvet.dto;

import java.sql.Date;

public class CadastroTutorRequest {
    // Dados que vêm do Front-end
    private String nome;
    private String sobrenome;
    private Date dataNascimento; // Formato YYYY-MM-DD no JSON
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cep;
    private char generoTutor;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public char getGeneroTutor() { return generoTutor; }
    public void setGeneroTutor(char generoTutor) { this.generoTutor = generoTutor; }
}