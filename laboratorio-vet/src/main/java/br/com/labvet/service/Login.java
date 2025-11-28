package br.com.labvet.service;

import br.com.labvet.model.Funcionario;
import br.com.labvet.util.HashSenha; 

// NOVAS IMPORTAÇÕES DO SPRING E DO JDBC TEMPLATE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class Login {

   
    private final JdbcTemplate jdbcTemplate;

    
    @Autowired
    public Login(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    // Dentro da classe Login

    public Funcionario tentarLogin(String loginDigitado, String senhaDigitada) {
        
        
        
        String sql = "SELECT ID_FUNCIONARIO, NOME_COMPLETO, Cargo, SENHA_HASH FROM TBL_FUNCIONARIOS WHERE LOGIN_USUARIO = ?";

        try {
            Funcionario funcionarioDoBanco = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Funcionario(
                    
                    rs.getInt("ID_FUNCIONARIO"),
                    rs.getString("NOME_COMPLETO"),
                    rs.getString("Cargo")
                ),
                loginDigitado
            );
            
            
            String hashSalvoNoBanco = jdbcTemplate.queryForObject(
                "SELECT SENHA_HASH FROM TBL_FUNCIONARIOS WHERE LOGIN_USUARIO = ?", 
                String.class, 
                loginDigitado
            );

            String hashDaSenhaDigitada = HashSenha.hashPassword(senhaDigitada); 

            if (hashSalvoNoBanco != null && hashSalvoNoBanco.equals(hashDaSenhaDigitada)) {
                return funcionarioDoBanco;
            } else {
                return null;
            }

        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}