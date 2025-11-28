package br.com.labvet.run;

import br.com.labvet.model.Tutor;
import br.com.labvet.service.TutorService;
import java.sql.Date; 

public class TestaTutorCRUD {

    public static void main(String[] args) {
        
        TutorService servico = new TutorService();
        String cpfTeste = "98765432100"; // Um CPF para o teste
        
        // --- 1. Testando CREATE ---
        System.out.println("--- Testando CREATE (Cadastro) ---");
        
        // criando um novo Tutor com todos os campos obrigatórios
        Date dataNasc = Date.valueOf("1990-01-15");
        Tutor novoTutor = new Tutor(
            "Tutor", // NOME
            "Teste", // SOBRENOME
            dataNasc, // DATA_NASCIMENTO
            cpfTeste, // CPF
            "11912345678", // TELEFONE
            "tutor.teste@email.com", // EMAIL
            "Rua Teste, 123", // ENDEREÇO
            "07123456", // CEP
            'M' // GENERO_TUTOR
        );
        
        int novoId = servico.cadastrarTutor(novoTutor);
        if (novoId != -1) {
            System.out.println("SUCESSO: Novo tutor cadastrado com ID: " + novoId);
        } else {
            System.out.println("FALHA: Tutor não cadastrado.");
            return;
        }

        // --- 2. Testando READ (por CPF) ---
        System.out.println("\n--- Testando READ (Busca por CPF) ---");
        Tutor tutorEncontrado = servico.buscarTutorPorCpf(cpfTeste);
        
        if (tutorEncontrado != null) {
            System.out.println("SUCESSO: Tutor encontrado: " + tutorEncontrado.toString());
        } else {
            System.out.println("FALHA: Tutor com CPF " + cpfTeste + " não encontrado.");
        }
        
        // --- 3. Testando UPDATE ---
        System.out.println("\n--- Testando UPDATE (Atualização) ---");
        tutorEncontrado.setNome("Tutor Teste (Atualizado)");
        tutorEncontrado.setTelefone("11988887777");
        
        boolean atualizou = servico.atualizarTutor(tutorEncontrado);
        if (atualizou) {
            System.out.println("SUCESSO: Tutor atualizado!");
        } else {
            System.out.println("FALHA: Tutor não atualizado.");
        }
        
        // --- 4. Testando DELETE ---
        System.out.println("\n--- Testando DELETE (Exclusão) ---");
        boolean deletou = servico.deletarTutor(novoId);
        
        if (deletou) {
            System.out.println("SUCESSO: Tutor deletado!");
        } else {
            System.out.println("FALHA: Tutor não deletado (Provavelmente porque ainda tem pets).");
        }
    }
}