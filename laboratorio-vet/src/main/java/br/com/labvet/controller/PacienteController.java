package br.com.labvet.controller;

import br.com.labvet.dto.CadastroPacienteRequest;
import br.com.labvet.model.Paciente;
import br.com.labvet.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // 1. Cadastrar Paciente
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody CadastroPacienteRequest req) {
        // Converte DTO para Modelo
        Paciente novoPet = new Paciente(
            req.getNomeAnimal(), req.getEspecie(), req.getRaca(),
            req.getPelagem(), req.getDataNascimento(), req.getPeso(),
            req.getGenero(), req.getIdTutor()
        );

        boolean sucesso = pacienteService.cadastrarPaciente(novoPet);

        if (sucesso) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao cadastrar. Verifique o ID do Tutor.");
        }
    }

    // 2. Buscar Pacientes de um Tutor
    @GetMapping("/tutor/{idTutor}")
    public ResponseEntity<List<Paciente>> listarPorTutor(@PathVariable int idTutor) {
        List<Paciente> lista = pacienteService.buscarPacientesPorTutor(idTutor);
        return ResponseEntity.ok(lista);
    }
}