package br.com.labvet.model; 

import java.sql.Date;

public class Paciente {

    // Atributos que correspondem à tabela TBL_PACIENTE
    private int id; // ID_PACIENTE
    private String nomeAnimal; // NOME_ANIMAL
    private String especie; // ESPECIE
    private String raca; // RACA
    private String pelagem; // PELAGEM
    private Date dataNascimento; // DATA_NASCIMENTO
    private String peso; // PESO
    private char genero; // GENERO_PACIENTE
    private int idTutor; // ID_TUTOR
    
    // Construtor principal (usado para inserir novos pacientes)
    public Paciente(String nomeAnimal, String especie, String raca, String pelagem, 
                    Date dataNascimento, String peso, char genero, int idTutor) {
        this.nomeAnimal = nomeAnimal;
        this.especie = especie;
        this.raca = raca;
        this.pelagem = pelagem;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.genero = genero;
        this.idTutor = idTutor;
    }
    
    // Construtor completo (usado ao ler dados do banco)
    public Paciente(int id, String nomeAnimal, String especie, String raca, String pelagem, 
                    Date dataNascimento, String peso, char genero, int idTutor) {
        this(nomeAnimal, especie, raca, pelagem, dataNascimento, peso, genero, idTutor);
        this.id = id;
    }

    // --- GETTERS (MÉTODOS DE LEITURA) ---
   
    
    public int getId() { return id; }
    public String getNomeAnimal() { return nomeAnimal; }
    public String getEspecie() { return especie; }
    public String getRaca() { return raca; }
    public Date getDataNascimento() { return dataNascimento; }
    public int getIdTutor() { return idTutor; }
    
    
    public String getPelagem() { return pelagem; } 
    public String getPeso() { return peso; }
    public char getGenero() { return genero; }

    // --- SETTERS (MÉTODOS DE ESCRITA) ---
    // (Adicionando todos para garantir)
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setRaca(String raca) { this.raca = raca; }
    public void setPeso(String peso) { this.peso = peso; }
    public void setPelagem(String pelagem) { this.pelagem = pelagem; }

    // Método toString() para facilitar a impressão
    @Override
    public String toString() {
        return "Paciente [id=" + id + ", nome=" + nomeAnimal + ", especie=" + especie + ", raca=" + raca + ", idTutor=" + idTutor + "]";
    }
}