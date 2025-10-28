package br.com.labvet.run;

import br.com.labvet.service.FuncionarioService;

public class TestaCadastroFuncionario {

    public static void main(String[] args) {
        
        // 1. Cria uma instância do novo serviço
        FuncionarioService servico = new FuncionarioService();

        // 2. Define os dados do novo funcionário (ex: uma recepcionista)
        String nome = "Ana Silva";
        String crmv = null; // Recepcionista não tem CRMV (passamos null)
        String cargo = "Recepcionista";
        String login = "ana.s";
        String senhaPura = "senhaDaAna456";

        // 3. Chama o serviço para tentar cadastrar
        System.out.println("Tentando cadastrar a recepcionista Ana Silva...");
        boolean sucesso = servico.cadastrarFuncionario(nome, crmv, cargo, login, senhaPura);

        // 4. Verifica o resultado
        if (sucesso) {
            System.out.println("SUCESSO! Funcionário cadastrado no banco!");
        } else {
            System.out.println("FALHA ao cadastrar o funcionário.");
        }
    }
}