package br.com.labvet.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.labvet.model.Funcionario;
import br.com.labvet.util.HashSenha;

public class Login {
    
    private String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";

    // Este é o método principal do back-end de login
    public Funcionario tentarLogin(String loginDigitado, String senhaDigitada) {
        
        // 1. Buscamos o usuário no banco APENAS pelo login
        String sql = "SELECT ID_FUNCIONARIO, NOME_COMPLETO, Cargo, SENHA_HASH FROM TBL_FUNCIONARIOS WHERE LOGIN_USUARIO = ?";

        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, loginDigitado); // Define o 1º '?' como o login
            
            try (ResultSet rs = stmt.executeQuery()) {

                // 2. Verificamos se o usuário foi encontrado
                if (rs.next()) {
                    // Usuário existe! Agora vamos checar a senha.
                    
                    // 3. Pegamos o hash da senha que está SALVO no banco
                    String hashSalvoNoBanco = rs.getString("SENHA_HASH");
                    
                    // 4. Geramos um hash da senha que o usuário DIGITOU
                    String hashDaSenhaDigitada = HashSenha.hashPassword(senhaDigitada);
                    
                    // 5. Comparamos os dois hashes
                    if (hashSalvoNoBanco != null && hashSalvoNoBanco.equals(hashDaSenhaDigitada)) {
                        // SENHA CORRETA! Login bem-sucedido.
                        
                        // Criamos o objeto Funcionario com os dados do banco
                        int id = rs.getInt("ID_FUNCIONARIO");
                        String nome = rs.getString("NOME_COMPLETO");
                        String cargo = rs.getString("Cargo");
                        
                        return new Funcionario(id, nome, cargo);
                    } else {
                        // Senha incorreta
                        return null; 
                    }
                } else {
                    // Usuário não encontrado (login incorreto)
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados durante o login:");
            e.printStackTrace();
            return null;
        }
    }
}