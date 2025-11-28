package br.com.labvet.dto;

public class CadastroExameRequest {
    private String tipoExame;
    private String resultado;
    private int idPaciente; // Quem fez o exame
    private int idFuncionario; // Médico responsável

    // Getters e Setters
    public String getTipoExame() { return tipoExame; }
    public void setTipoExame(String tipoExame) { this.tipoExame = tipoExame; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
}