package br.com.labvet.controller;

// Importa as 3 classes que vamos usar:
import br.com.labvet.dto.LoginRequest; // A "comanda"
import br.com.labvet.model.Funcionario; // O "crachá"
import br.com.labvet.service.Login; // O "porteiro" (usando seu nome 'Login')

// Importações do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController: Anotação que diz ao Spring que esta classe é um "Garçom".
 * Ela vai "ouvir" URLs e retornar dados (JSON).
 *
 * @RequestMapping("/api/auth"): Define o "setor" deste garçom.
 * Todos os métodos aqui dentro começarão com /api/auth
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    // 1. O "Garçom" precisa conhecer o "Porteiro" (a Cozinha)
    // Usamos Injeção de Dependência para que o Spring nos entregue a instância do Login
    private final Login loginService; // (usei 'loginService' como nome de variável, mas é da sua classe 'Login')

    @Autowired
    public LoginController(Login loginService) {
        this.loginService = loginService;
    }

    /**
     * @PostMapping("/login"): Diz ao Spring para executar este método
     * quando um pedido "POST" chegar na URL completa: "/api/auth/login".
     *
     * @RequestBody LoginRequest request: Pega o JSON do pedido e o
     * transforma no nosso objeto "Comanda" (LoginRequest).
     */
    @PostMapping("/login")
    public ResponseEntity<?> tentarLogin(@RequestBody LoginRequest request) {
        
        // 2. O Garçom passa o pedido para o Porteiro (Serviço)
        Funcionario funcionarioLogado = loginService.tentarLogin(
            request.getLogin(), 
            request.getSenha()
        );

        // 3. O Garçom devolve a resposta para o Cliente (Front-end)
        if (funcionarioLogado != null) {
            // SUCESSO!
            // Retorna uma resposta "200 OK" com os dados do funcionário
            return ResponseEntity.ok(funcionarioLogado);
        } else {
            // FALHA!
            // Retorna uma resposta "401 Unauthorized" (Não Autorizado)
            // com uma mensagem de erro.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Login ou senha inválidos.");
        }
    }
}