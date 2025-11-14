package br.com.labvet.dto;

// Este é o "molde" para os dados de um NOVO funcionário
public class CadastroFuncionarioRequest {

    // Os campos devem corresponder ao que o seu FuncionarioService espera
    private String nomeCompleto;
    private String crmv; // Pode ser nulo para recepcionistas
    private String cargo;
    private String login;
    private String senhaPura; // Vamos receber a senha pura (ex: "senha123")

    // Getters e Setters são essenciais para o Spring
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String NOME_COMPLETO) {
        this.nomeCompleto = NOME_COMPLETO;
    }
    public String getCrmv() {
        return crmv;
    }
    public void setCrmv(String CRMV) {
        this.crmv = CRMV;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String Cargo) {
        this.cargo = Cargo;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String LOGIN_USUARIO) {
        this.login = LOGIN_USUARIO;
    }
    public String getSenhaPura() {
        return senhaPura;
    }
    public void setSenhaPura(String senhaPura) {
        this.senhaPura = senhaPura;
    }
}