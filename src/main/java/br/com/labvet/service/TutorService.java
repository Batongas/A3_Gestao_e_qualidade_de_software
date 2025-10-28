package br.com.labvet.service;


import br.com.labvet.model.Tutor;

import java.sql.Connection;
import java.sql.DriverManager; // <-- Importamos o DriverManager
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TutorService {

    
    private String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";

    // -------------------
    // CREATE (Cadastrar)
    // -------------------
    public int cadastrarTutor(Tutor tutor) {
        String sql = "INSERT INTO TBL_TUTOR (NOME, SOBRENOME, DATA_NASCIMENTO, CPF, TELEFONE, EMAIL, [ENDEREÇO], CEP, GENERO_TUTOR) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Use DriverManager.getConnection(url)
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // O resto deste método está 100% correto...
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getSobrenome());
            stmt.setDate(3, tutor.getDataNascimento());
            stmt.setString(4, tutor.getCpf());
            stmt.setString(5, tutor.getTelefone());
            stmt.setString(6, tutor.getEmail());
            stmt.setString(7, tutor.getEndereco());
            stmt.setString(8, tutor.getCep());
            stmt.setString(9, String.valueOf(tutor.getGeneroTutor()));
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna o ID do novo tutor
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar tutor (CPF pode já existir): " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Falha
    }

    // -------------------
    // READ (Buscar por CPF)
    // -------------------
    public Tutor buscarTutorPorCpf(String cpf) {
        String sql = "SELECT * FROM TBL_TUTOR WHERE CPF = ?";
        
        // Use DriverManager.getConnection(url)
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // O resto deste método está 100% correto...
                    return new Tutor(
                        rs.getInt("ID_TUTOR"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getDate("DATA_NASCIMENTO"),
                        rs.getString("CPF"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ENDEREÇO"),
                        rs.getString("CEP"),
                        rs.getString("GENERO_TUTOR").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Não encontrou
    }
    
    // -------------------
    // UPDATE (Atualizar)
    // -------------------
    public boolean atualizarTutor(Tutor tutor) {
        String sql = "UPDATE TBL_TUTOR SET NOME = ?, SOBRENOME = ?, TELEFONE = ?, EMAIL = ?, [ENDEREÇO] = ?, CEP = ?, GENERO_TUTOR = ? " +
                     "WHERE ID_TUTOR = ?";
        
        // Use DriverManager.getConnection(url)
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            // O resto deste método está 100% correto...
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getSobrenome());
            stmt.setString(3, tutor.getTelefone());
            stmt.setString(4, tutor.getEmail());
            stmt.setString(5, tutor.getEndereco());
            stmt.setString(6, tutor.getCep());
            stmt.setString(7, String.valueOf(tutor.getGeneroTutor()));
            stmt.setInt(8, tutor.getId()); 
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // -------------------
    // DELETE (Deletar)
    // -------------------
    public boolean deletarTutor(int idTutor) {
        String sql = "DELETE FROM TBL_TUTOR WHERE ID_TUTOR = ?";
        
        // Use DriverManager.getConnection(url)
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, idTutor);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tutor. Ele ainda possui pets? " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}