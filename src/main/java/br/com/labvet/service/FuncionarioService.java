package br.com.labvet.service;

import br.com.labvet.util.HashSenha; // Usando seu 'HashSenha'

// Importações do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service // 1. Diz ao Spring que esta é uma classe de serviço
public class FuncionarioService {

    // 2. Pede ao Spring pelo JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FuncionarioService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Cadastra um novo funcionário no banco de dados.
     * Retorna true se o cadastro foi bem-sucedido, false se falhou.
     */
    // Dentro da classe FuncionarioService

        public String cadastrarFuncionario(String nome, String crmv, String cargo, String login, String senhaPura) {
        
        String senhaHash = HashSenha.hashPassword(senhaPura);
        
        String sql = "INSERT INTO TBL_FUNCIONARIOS (NOME_COMPLETO, CRMV, Cargo, LOGIN_USUARIO, SENHA_HASH) VALUES (?, ?, ?, ?, ?)";
        
        try {
            int linhasAfetadas = jdbcTemplate.update(
                sql, 
                nome, 
                crmv, 
                cargo, 
                login, 
                senhaHash
            );
            
            // Se 0 linhas foram afetadas (estranho), retorne um erro.
            if (linhasAfetadas == 0) {
                return "Nenhuma linha foi afetada. O cadastro falhou.";
            }
            
            // Se chegou aqui, foi um SUCESSO.
            return null; 

        } catch (Exception e) {
            // --- A GRANDE MUDANÇA ESTÁ AQUI ---
            // Agora, em vez de retornar 'false', nós retornamos a MENSAGEM REAL do erro.
            System.err.println("Erro de SQL ao cadastrar: " + e.getMessage());
            
            // Vamos checar se é um erro de chave duplicada (o que suspeitamos)
            if (e.getMessage().contains("UNIQUE KEY")) {
                return "Erro: O Login, CPF ou CRMV informado já existe no banco de dados.";
            }
            
            // Para qualquer outro erro (ex: "coluna não pode ser nula")
            return "Erro inesperado do banco de dados: " + e.getMessage();
        }
    }
}