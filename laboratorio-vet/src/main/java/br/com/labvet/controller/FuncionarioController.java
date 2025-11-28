package br.com.labvet.controller;

import br.com.labvet.dto.CadastroFuncionarioRequest;
import br.com.labvet.service.FuncionarioService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionarios") 
public class FuncionarioController {

    
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    /**
     
     * Este método vai ouvir na URL completa: "/api/funcionarios/cadastrar"
     */
    // Dentro do FuncionarioController.java

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarNovoFuncionario(@RequestBody CadastroFuncionarioRequest request) {
        
        // 1. Chama o serviço, que agora retorna uma String de erro (ou null)
        String erro = funcionarioService.cadastrarFuncionario(
            request.getNomeCompleto(),
            request.getCrmv(),
            request.getCargo(),
            request.getLogin(),
            request.getSenhaPura()
        );

        // 2. Devolve a resposta
        if (erro == null) {
            // SUCESSO (O serviço retornou null)
            return ResponseEntity.status(HttpStatus.CREATED)
                                .body("Funcionário cadastrado com sucesso!");
        } else {
            // FALHA Retorna 400 Bad Request com a MENSAGEM DE ERRO 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(erro); // <- envia o erro real
        }
    }
}