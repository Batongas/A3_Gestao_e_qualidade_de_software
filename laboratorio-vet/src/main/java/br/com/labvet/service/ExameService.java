package br.com.labvet.service;

import br.com.labvet.model.Exame;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExameService {

    private final JdbcTemplate jdbcTemplate;

    
    @Autowired
    public ExameService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    public boolean cadastrarExame(Exame exame) {
        String sql = "INSERT INTO TBL_EXAMES (TIPO_EXAME, RESULTADO, DATA_HORA, ID_PACIENTE, ID_FUNCIONARIO) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try {
            int linhasAfetadas = jdbcTemplate.update(
                sql,
                exame.getTipoExame(),
                exame.getResultado(),
                exame.getDataHora(),
                exame.getIdPaciente(),
                exame.getIdFuncionario()
            );
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar exame: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Exame> buscarExamesPorPaciente(int idPaciente) {
        String sql = "SELECT * FROM TBL_EXAMES WHERE ID_PACIENTE = ? ORDER BY DATA_HORA DESC";

        try {
            // Retorna direto, sem criar variável 'historico' antes
            return jdbcTemplate.query(sql, (rs, rowNum) -> 
                new Exame(
                    rs.getInt("ID_EXAME"),
                    rs.getString("TIPO_EXAME"),
                    rs.getString("RESULTADO"),
                    rs.getTimestamp("DATA_HORA"),
                    rs.getInt("ID_PACIENTE"),
                    rs.getInt("ID_FUNCIONARIO")
                ),
                idPaciente
            );
        } catch (Exception e) {
            e.printStackTrace();
            // ArrayList para retornar uma lista vazia em caso de erro
            return new ArrayList<>();
        }
    }
}