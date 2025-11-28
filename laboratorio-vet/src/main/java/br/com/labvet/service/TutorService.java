package br.com.labvet.service;

import br.com.labvet.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Service // Diz ao Spring que é um serviço
public class TutorService {

    private final JdbcTemplate jdbcTemplate;

    
    @Autowired
    public TutorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // -------------------
    // CREATE (Cadastrar)
    // -------------------
    public int cadastrarTutor(Tutor tutor) {
        String sql = "INSERT INTO TBL_TUTOR (NOME, SOBRENOME, DATA_NASCIMENTO, CPF, TELEFONE, EMAIL, [ENDEREÇO], CEP, GENERO_TUTOR) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            // KeyHolder para recuperar o ID gerado automaticamente
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tutor.getNome());
                ps.setString(2, tutor.getSobrenome());
                ps.setDate(3, tutor.getDataNascimento());
                ps.setString(4, tutor.getCpf());
                ps.setString(5, tutor.getTelefone());
                ps.setString(6, tutor.getEmail());
                ps.setString(7, tutor.getEndereco());
                ps.setString(8, tutor.getCep());
                ps.setString(9, String.valueOf(tutor.getGeneroTutor()));
                return ps;
            }, keyHolder);

            // Retorna o ID gerado
            Number chaveGerada = keyHolder.getKey();

            // Verifica a variável 
            if (chaveGerada != null) {
                return chaveGerada.intValue();
            }
            return -1;

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar tutor: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    // -------------------
    // READ (Buscar por CPF)
    // -------------------
    public Tutor buscarTutorPorCpf(String cpf) {
        String sql = "SELECT * FROM TBL_TUTOR WHERE CPF = ?";
        
        try {
            //  queryForObject do Spring (se não achar, lança exceção que tratamos no catch)
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
                new Tutor(
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
                ),
                cpf
            );
        } catch (Exception e) {
            // Se der erro (ex: não encontrado), retorna null
            return null;
        }
    }
    
    // -------------------
    // UPDATE (Atualizar)
    // -------------------
    public boolean atualizarTutor(Tutor tutor) {
        String sql = "UPDATE TBL_TUTOR SET NOME = ?, SOBRENOME = ?, TELEFONE = ?, EMAIL = ?, [ENDEREÇO] = ?, CEP = ?, GENERO_TUTOR = ? " +
                     "WHERE ID_TUTOR = ?";
        
        try {
            int linhas = jdbcTemplate.update(sql,
                tutor.getNome(),
                tutor.getSobrenome(),
                tutor.getTelefone(),
                tutor.getEmail(),
                tutor.getEndereco(),
                tutor.getCep(),
                String.valueOf(tutor.getGeneroTutor()),
                tutor.getId()
            );
            return linhas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // -------------------
    // DELETE (Deletar)
    // -------------------
    public boolean deletarTutor(int idTutor) {
        String sql = "DELETE FROM TBL_TUTOR WHERE ID_TUTOR = ?";
        
        try {
            int linhas = jdbcTemplate.update(sql, idTutor);
            return linhas > 0;
        } catch (Exception e) {
            System.err.println("Erro ao deletar tutor: " + e.getMessage());
            return false;
        }
    }
}