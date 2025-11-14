package br.com.labvet.controller;

import br.com.labvet.dto.CadastroFuncionarioRequest;
import br.com.labvet.service.FuncionarioService; // Importa o "RH"

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionarios") // O "setor" deste garçom é /api/funcionarios
public class FuncionarioController {

    // O "garçom do RH" precisa conhecer o "RH" (o Serviço)
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    /**
     * @PostMapping("/cadastrar")
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
            // SUCESSO! (O serviço retornou null)
            return ResponseEntity.status(HttpStatus.CREATED)
                                .body("Funcionário cadastrado com sucesso!");
        } else {
            // FALHA! Retorna 400 Bad Request com a MENSAGEM DE ERRO REAL
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(erro); // <-- Agora ele envia o erro real!
        }
    }
}