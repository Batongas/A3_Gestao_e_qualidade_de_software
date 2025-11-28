package br.com.labvet.service;

import br.com.labvet.model.Paciente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;      

public class PacienteService {

    private String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";

    // -------------------
    // CREATE (Cadastrar)
    // -------------------
    public boolean cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO TBL_PACIENTE (NOME_ANIMAL, ESPECIE, RACA, PELAGEM, DATA_NASCIMENTO, PESO, GENERO_PACIENTE, ID_TUTOR) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNomeAnimal());
            stmt.setString(2, paciente.getEspecie());
            stmt.setString(3, paciente.getRaca());
            stmt.setString(4, paciente.getPelagem()); 
            stmt.setDate(5, paciente.getDataNascimento());
            stmt.setString(6, paciente.getPeso()); 
            stmt.setString(7, String.valueOf(paciente.getGenero())); // Converte char para String
            stmt.setInt(8, paciente.getIdTutor());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // -------------------
    // READ (Buscar)
    // -------------------
    // Este método busca TODOS os pets de um determinado tutor
    public List<Paciente> buscarPacientesPorTutor(int idTutor) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM TBL_PACIENTE WHERE ID_TUTOR = ?";

        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idTutor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Recria o objeto Paciente com os dados do banco
                    Paciente p = new Paciente(
                        rs.getInt("ID_PACIENTE"),
                        rs.getString("NOME_ANIMAL"),
                        rs.getString("ESPECIE"),
                        rs.getString("RACA"),
                        rs.getString("PELAGEM"),
                        rs.getDate("DATA_NASCIMENTO"),
                        rs.getString("PESO"),
                        rs.getString("GENERO_PACIENTE").charAt(0),
                        rs.getInt("ID_TUTOR")
                    );
                    pacientes.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pacientes: " + e.getMessage());
            e.printStackTrace();
        }
        return pacientes;
    }

    // -------------------
    // UPDATE (Atualizar)
    // -------------------
    public boolean atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE TBL_PACIENTE SET NOME_ANIMAL = ?, ESPECIE = ?, RACA = ?, " +
                     "PELAGEM = ?, DATA_NASCIMENTO = ?, PESO = ?, GENERO_PACIENTE = ? " +
                     "WHERE ID_PACIENTE = ?";
        
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, paciente.getNomeAnimal());
            stmt.setString(2, paciente.getEspecie());
            stmt.setString(3, paciente.getRaca());
            stmt.setString(4, paciente.getPelagem());
            stmt.setDate(5, paciente.getDataNascimento());
            stmt.setString(6, paciente.getPeso());
            stmt.setString(7, String.valueOf(paciente.getGenero()));
            stmt.setInt(8, paciente.getId()); // O ID vai no WHERE
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // -------------------
    // DELETE (Deletar)
    // -------------------
    public boolean deletarPaciente(int idPaciente) {
        String sql = "DELETE FROM TBL_PACIENTE WHERE ID_PACIENTE = ?";

        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}