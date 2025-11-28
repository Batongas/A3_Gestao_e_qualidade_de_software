package br.com.labvet.service;

import br.com.labvet.model.Paciente;

// Importações do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Diz ao Spring que isso é um Serviço
public class PacienteService {

    // Trocamos a 'String url' pelo JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    //  O Construtor recebe a conexão pronta do Spring
    @Autowired
    public PacienteService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    // CREATE (Cadastrar)
    
    public boolean cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO TBL_PACIENTE (NOME_ANIMAL, ESPECIE, RACA, PELAGEM, DATA_NASCIMENTO, PESO, GENERO_PACIENTE, ID_TUTOR) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // (jdbcTemplate.update)
            int linhasAfetadas = jdbcTemplate.update(
                sql,
                paciente.getNomeAnimal(),
                paciente.getEspecie(),
                paciente.getRaca(),
                paciente.getPelagem(),
                paciente.getDataNascimento(),
                paciente.getPeso(),
                String.valueOf(paciente.getGenero()), // Converte char para String
                paciente.getIdTutor()
            );
            
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    // READ (Buscar por Tutor)
    
    public List<Paciente> buscarPacientesPorTutor(int idTutor) {
        String sql = "SELECT * FROM TBL_PACIENTE WHERE ID_TUTOR = ?";

        try {
            // JdbcTemplate com um RowMapper (Mapeador)
            return jdbcTemplate.query(sql, (rs, rowNum) -> 
                new Paciente(
                    rs.getInt("ID_PACIENTE"),
                    rs.getString("NOME_ANIMAL"),
                    rs.getString("ESPECIE"),
                    rs.getString("RACA"),
                    rs.getString("PELAGEM"),
                    rs.getDate("DATA_NASCIMENTO"),
                    rs.getString("PESO"),
                    rs.getString("GENERO_PACIENTE").charAt(0),
                    rs.getInt("ID_TUTOR")
                ),
                idTutor // O valor do '?'
            );

        } catch (Exception e) {
            System.err.println("Erro ao buscar pacientes: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    
    // UPDATE (Atualizar)
    
    public boolean atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE TBL_PACIENTE SET NOME_ANIMAL = ?, ESPECIE = ?, RACA = ?, " +
                     "PELAGEM = ?, DATA_NASCIMENTO = ?, PESO = ?, GENERO_PACIENTE = ? " +
                     "WHERE ID_PACIENTE = ?";
        
        try {
            int linhasAfetadas = jdbcTemplate.update(
                sql,
                paciente.getNomeAnimal(),
                paciente.getEspecie(),
                paciente.getRaca(),
                paciente.getPelagem(),
                paciente.getDataNascimento(),
                paciente.getPeso(),
                String.valueOf(paciente.getGenero()),
                paciente.getId() // O ID vai no WHERE
            );
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    // DELETE (Deletar)
    
    public boolean deletarPaciente(int idPaciente) {
        String sql = "DELETE FROM TBL_PACIENTE WHERE ID_PACIENTE = ?";

        try {
            int linhasAfetadas = jdbcTemplate.update(sql, idPaciente);
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}