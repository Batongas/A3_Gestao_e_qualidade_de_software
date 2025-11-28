package br.com.labvet.service;

import br.com.labvet.model.Funcionario;
import br.com.labvet.util.HashSenha; // Usando seu nome de classe 'HashSenha'

// NOVAS IMPORTAÇÕES DO SPRING E DO JDBC TEMPLATE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Esta anotação @Service transforma esta classe em um "componente" gerenciado pelo Spring.
 * Agora, o Spring sabe sobre ela e pode "injetar" coisas nela (como o JdbcTemplate).
 */
@Service
public class Login {

   
    private final JdbcTemplate jdbcTemplate;

    /**
     * 2. INJEÇÃO DE DEPENDÊNCIA (via Construtor)
     * Este construtor pede ao Spring: "Por favor, me dê o JdbcTemplate
     * que você já configurou com o 'application.properties'".
     * O Spring "injeta" ele aqui automaticamente.
     */
    @Autowired
    public Login(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 3. O MÉTODO 'tentarLogin' REFEITO (MUITO MAIS LIMPO!)
     * não há mais 'try-with-resources', 'Connection' ou 'PreparedStatement'.
     * O JdbcTemplate cuida de tudo isso para nós nos bastidores.
     */
    // Dentro da classe Login

    public Funcionario tentarLogin(String loginDigitado, String senhaDigitada) {
        
        // --- CORREÇÃO 1 AQUI ---
        // Trocamos FuncionarioID, NomeCompleto, e LoginUsuario pelos nomes corretos
        String sql = "SELECT ID_FUNCIONARIO, NOME_COMPLETO, Cargo, SENHA_HASH FROM TBL_FUNCIONARIOS WHERE LOGIN_USUARIO = ?";

        try {
            Funcionario funcionarioDoBanco = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Funcionario(
                    // --- CORREÇÃO 2 AQUI ---
                    rs.getInt("ID_FUNCIONARIO"),
                    rs.getString("NOME_COMPLETO"),
                    rs.getString("Cargo")
                ),
                loginDigitado
            );
            
            // --- CORREÇÃO 3 AQUI ---
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