package br.com.labvet.controller;


import br.com.labvet.dto.LoginRequest; 
import br.com.labvet.model.Funcionario; 
import br.com.labvet.service.Login; 

// Importações do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    
    private final Login loginService; 

    @Autowired
    public LoginController(Login loginService) {
        this.loginService = loginService;
    }

    /**
     
      @RequestBody 
    
     */
    @PostMapping("/login")
    public ResponseEntity<?> tentarLogin(@RequestBody LoginRequest request) {
        
        
        Funcionario funcionarioLogado = loginService.tentarLogin(
            request.getLogin(), 
            request.getSenha()
        );

        
        if (funcionarioLogado != null) {
           
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