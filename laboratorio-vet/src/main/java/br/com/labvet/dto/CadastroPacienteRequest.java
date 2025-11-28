package br.com.labvet.dto;

import java.sql.Date;

public class CadastroPacienteRequest {
    private String nomeAnimal;
    private String especie;
    private String raca;
    private String pelagem;
    private Date dataNascimento;
    private String peso;
    private char genero;
    private int idTutor; // OBRIGATÓRIO: O ID do dono

    // Getters e Setters
    public String getNomeAnimal() { return nomeAnimal; }
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public String getPelagem() { return pelagem; }
    public void setPelagem(String pelagem) { this.pelagem = pelagem; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }
    public char getGenero() { return genero; }
    public void setGenero(char genero) { this.genero = genero; }
    public int getIdTutor() { return idTutor; }
    public void setIdTutor(int idTutor) { this.idTutor = idTutor; }
}