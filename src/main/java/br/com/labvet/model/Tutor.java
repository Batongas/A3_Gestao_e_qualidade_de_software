package br.com.labvet.model;

import java.sql.Date; 

public class Tutor {
    
    private int id; // ID_TUTOR
    private String nome; // NOME
    private String sobrenome; // SOBRENOME
    private Date dataNascimento; // DATA_NASCIMENTO
    private String cpf; // CPF
    private String telefone; // TELEFONE
    private String email; // EMAIL
    private String endereco; // ENDEREÇO
    private String cep; // CEP
    private char generoTutor; // GENERO_TUTOR

    // Construtor para criar novos tutores (sem id)
    public Tutor(String nome, String sobrenome, Date dataNascimento, String cpf, 
                 String telefone, String email, String endereco, String cep, char generoTutor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cep = cep;
        this.generoTutor = generoTutor;
    }

    // Construtor para ler tutores do banco (com id)
    public Tutor(int id, String nome, String sobrenome, Date dataNascimento, String cpf, 
                 String telefone, String email, String endereco, String cep, char generoTutor) {
        this(nome, sobrenome, dataNascimento, cpf, telefone, email, endereco, cep, generoTutor);
        this.id = id;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public Date getDataNascimento() { return dataNascimento; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public String getCep() { return cep; }
    public char getGeneroTutor() { return generoTutor; }

    public void setNome(String nome) { this.nome = nome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCep(String cep) { this.cep = cep; }
    public void setGeneroTutor(char generoTutor) { this.generoTutor = generoTutor; }


    @Override
    public String toString() {
        return "Tutor [id=" + id + ", nome=" + nome + " " + sobrenome + ", cpf=" + cpf + "]";
    }
}