package br.com.labvet.model;

public class Funcionario {
    private int ID_FUNCIONARIO;
    private String NOME_COMPLETO;
    private String Cargo;
    
    // Construtor
    public Funcionario(int ID_FUNCIONARIO, String NOME_COMPLETO, String Cargo) {
        this.ID_FUNCIONARIO = ID_FUNCIONARIO;
        this.NOME_COMPLETO = NOME_COMPLETO;
        this.Cargo = Cargo;
    }

    // Getters (para acessar os dados)
    public int getId() { return ID_FUNCIONARIO; }
    public String getNomeCompleto() { return NOME_COMPLETO; }
    public String getCargo() { return Cargo; }

    // (Opcional) Método toString para facilitar a impressão
    @Override
    public String toString() {
        return "Funcionario [id=" + ID_FUNCIONARIO + ", nome=" + NOME_COMPLETO + ", cargo=" + Cargo + "]";
    }
}