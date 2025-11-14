package br.com.labvet.model;

import java.sql.Timestamp; // Importante: para data E hora

public class Exame {

    private int id; // ID_EXAME
    private String tipoExame; // TIPO_EXAME
    private String resultado; // RESULTADO
    private Timestamp dataHora; // DATA_HORA
    private int idPaciente; // ID_PACIENTE (Chave Estrangeira)
    private int idFuncionario; // ID_FUNCIONARIO (Chave Estrangeira)

    // Construtor para criar um novo exame (quando ainda não tem id)
    public Exame(String tipoExame, String resultado, Timestamp dataHora, int idPaciente, int idFuncionario) {
        this.tipoExame = tipoExame;
        this.resultado = resultado;
        this.dataHora = dataHora;
        this.idPaciente = idPaciente;
        this.idFuncionario = idFuncionario;
    }

    // Construtor para ler um exame do banco (quando já tem id)
    public Exame(int id, String tipoExame, String resultado, Timestamp dataHora, int idPaciente, int idFuncionario) {
        this(tipoExame, resultado, dataHora, idPaciente, idFuncionario);
        this.id = id;
    }

    // Getters (métodos de leitura que serão usados pelo Service)
    public int getId() { return id; }
    public String getTipoExame() { return tipoExame; }
    public String getResultado() { return resultado; }
    public Timestamp getDataHora() { return dataHora; }
    public int getIdPaciente() { return idPaciente; }
    public int getIdFuncionario() { return idFuncionario; }
    
    @Override
    public String toString() {
        return "Exame [id=" + id + ", tipo=" + tipoExame + ", data=" + dataHora + "]";
    }
}