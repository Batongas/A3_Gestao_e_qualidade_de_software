// package br.com.labvet.run;

// import br.com.labvet.model.Paciente; 
// import br.com.labvet.service.PacienteService;
// import java.sql.Date;
// import java.util.List;

// public class TestaPacientesCRUD {

//     public static void main(String[] args) {
        
//         PacienteService servico = new PacienteService();
        
        
//         int idTutorExistente = 3; 
        
//         // ---  Testando CREATE ---
//         System.out.println("--- Testando CREATE (Cadastro) ---");
//         // java.sql.Date.valueOf() aceita o formato 'YYYY-MM-DD'
//         Date dataNasc = Date.valueOf("2020-05-10");
//         Paciente novoPet = new Paciente("Rex", "Cachorro", "Vira-lata", "Caramelo", 
//                                         dataNasc, "12.5", 'M', idTutorExistente);
        
//         boolean cadastrou = servico.cadastrarPaciente(novoPet);
//         if (cadastrou) {
//             System.out.println("SUCESSO: Novo paciente cadastrado!");
//         } else {
//             // System.out.println("FALHA: Paciente não cadastrado.");
//             return; // Encerra o teste se o cadastro falhar
//         }

//         // --- 2. Testando READ ---
//         System.out.println("\n--- Testando READ (Busca por Tutor) ---");
//         List<Paciente> petsDoTutor = servico.buscarPacientesPorTutor(idTutorExistente);
        
//         if (petsDoTutor.isEmpty()) {
//             System.out.println("FALHA: Nenhum pet encontrado para o tutor " + idTutorExistente);
//         } else {
//             System.out.println("SUCESSO: Pets encontrados:");
//             for (Paciente pet : petsDoTutor) {
//                 System.out.println(pet.toString());
//             }
//         }
        
//         // Pega o ID do pet cadastrado para usar nos próximos testes
//         int idDoPetCadastrado = petsDoTutor.get(0).getId();

//         // --- 3. Testando UPDATE ---
//         System.out.println("\n--- Testando UPDATE (Atualização) ---");
//         Paciente petParaAtualizar = petsDoTutor.get(0); // Pega o pet que buscamos
//         petParaAtualizar.setNomeAnimal("Rex (Atualizado)");
//         petParaAtualizar.setPeso("13.0");
        
//         boolean atualizou = servico.atualizarPaciente(petParaAtualizar);
//         if (atualizou) {
//             System.out.println("SUCESSO: Paciente atualizado!");
//         } else {
//             System.out.println("FALHA: Paciente não atualizado.");
//         }
        
//         // --- 4. Testando DELETE ---
//         System.out.println("\n--- Testando DELETE (Exclusão) ---");
//         boolean deletou = servico.deletarPaciente(idDoPetCadastrado);
        
//         if (deletou) {
//             System.out.println("SUCESSO: Paciente deletado!");
//         } else {
//             System.out.println("FALHA: Paciente não deletado.");
//         }
        
//         // --- 5. Verificação Final ---
//         System.out.println("\n--- Verificação Final (Busca) ---");
//         List<Paciente> petsAposDelete = servico.buscarPacientesPorTutor(idTutorExistente);
//         if (petsAposDelete.isEmpty()) {
//             System.out.println("SUCESSO: O pet foi removido, a lista está vazia.");
//         } else {
//             System.out.println("FALHA: O pet ainda está na lista.");
//         }
//     }
// }