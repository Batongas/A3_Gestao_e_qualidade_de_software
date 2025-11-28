package br.com.labvet.service;

// Importa as classes que vamos precisar
import br.com.labvet.model.Exame; // Importa o modelo que você criou

import java.sql.Connection;
import java.sql.DriverManager; // Importação necessária
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameService {

    // ADICIONE ESTA LINHA DE VOLTA (a sua string de conexão)
    private String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";

    /**
     * Cadastra um novo exame no banco de dados.
     */
    public boolean cadastrarExame(Exame exame) {
        String sql = "INSERT INTO TBL_EXAMES (TIPO_EXAME, RESULTADO, DATA_HORA, ID_PACIENTE, ID_FUNCIONARIO) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        // MUDANÇA AQUI: Voltamos a usar o DriverManager
        try (Connection conexao = DriverManager.getConnection(url); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define os valores para cada '?' do SQL
            stmt.setString(1, exame.getTipoExame());
            stmt.setString(2, exame.getResultado());
            stmt.setTimestamp(3, exame.getDataHora());
            stmt.setInt(4, exame.getIdPaciente());
            stmt.setInt(5, exame.getIdFuncionario());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se o cadastro deu certo

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar exame: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca o histórico de exames de um paciente específico.
     */
    public List<Exame> buscarExamesPorPaciente(int idPaciente) {
        List<Exame> historico = new ArrayList<>();
        String sql = "SELECT * FROM TBL_EXAMES WHERE ID_PACIENTE = ? ORDER BY DATA_HORA DESC"; 

        // MUDANÇA AQUI: Voltamos a usar o DriverManager
        try (Connection conexao = DriverManager.getConnection(url);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Loop para cada exame encontrado
                while (rs.next()) {
                    Exame ex = new Exame(
                        rs.getInt("ID_EXAME"),
                        rs.getString("TIPO_EXAME"),
                        rs.getString("RESULTADO"),
                        rs.getTimestamp("DATA_HORA"),
                        rs.getInt("ID_PACIENTE"),
                        rs.getInt("ID_FUNCIONARIO")
                    );
                    historico.add(ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico; // Retorna a lista (pode estar vazia)
    }
}