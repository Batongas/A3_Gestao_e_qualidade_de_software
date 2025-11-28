package br.com.labvet.controller;

import br.com.labvet.dto.CadastroExameRequest;
import br.com.labvet.model.Exame;
import br.com.labvet.service.ExameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/exames")
public class ExameController {

    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    // 1 Cadastrar Exame
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody CadastroExameRequest req) {
        // Converte DTO para Modelo
        Exame novoExame = new Exame(
            req.getTipoExame(),
            req.getResultado(),
            Timestamp.from(Instant.now()), 
            req.getIdPaciente(),
            req.getIdFuncionario()
        );

        boolean sucesso = exameService.cadastrarExame(novoExame);

        if (sucesso) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Exame registrado com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao registrar exame.");
        }
    }

    // 2 Buscar Histórico de um Paciente
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Exame>> listarPorPaciente(@PathVariable int idPaciente) {
        List<Exame> historico = exameService.buscarExamesPorPaciente(idPaciente);
        return ResponseEntity.ok(historico);
    }
}