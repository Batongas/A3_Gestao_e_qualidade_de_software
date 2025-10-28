package br.com.labvet.service; // Seu pacote

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.labvet.util.HashSenha;

public class FuncionarioService {

    // A URL de conexão é privada
    private String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";

    /**
     * Cadastra um novo funcionário no banco de dados.
     * Retorna true se o cadastro foi bem-sucedido, false se falhou.
     */
    public boolean cadastrarFuncionario(String nome, String crmv, String cargo, String login, String senhaPura) {
        
        // 1. Gera o Hash da senha
        String senhaHash = HashSenha.hashPassword(senhaPura);
        
        // 2. Prepara o SQL
        String sql = "INSERT INTO Funcionarios (NomeCompleto, CRMV, Cargo, LoginUsuario, SENHA_HASH) VALUES (?, ?, ?, ?, ?)";
        
        // 3. Conecta e executa
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, crmv); // CRMV pode ser nulo se a coluna permitir
            stmt.setString(3, cargo);
            stmt.setString(4, login);
            stmt.setString(5, senhaHash);

            int linhasAfetadas = stmt.executeUpdate();
            
            // Retorna true se pelo menos 1 linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário. O Login ou CRMV já pode existir.");
            e.printStackTrace();
            return false; // Retorna false em caso de falha
        }
    }
    
    // (No futuro, você pode adicionar outros métodos aqui, como...)
    // public Funcionario buscarFuncionarioPorId(int id) { ... }
    // public boolean deletarFuncionario(int id) { ... }
}