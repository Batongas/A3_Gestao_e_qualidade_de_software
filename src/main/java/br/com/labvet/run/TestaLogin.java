package br.com.labvet.run; 

import br.com.labvet.model.Funcionario;
import br.com.labvet.service.Login;

public class TestaLogin {

    public static void main(String[] args) {
        // Cria uma instância do nosso serviço de login
        Login loginService = new Login();

        // --- SIMULAÇÃO 1: Login Correto ---
        System.out.println("Tentativa 1: Login correto...");
        //teste
        Funcionario usuarioLogado = loginService.tentarLogin("carlos.a", "senhaForte123"); 

        if (usuarioLogado != null) {
            System.out.println("Login BEM-SUCEDIDO!");
            System.out.println("Bem-vindo(a), " + usuarioLogado.getNomeCompleto());
            System.out.println("Seu cargo é: " + usuarioLogado.getCargo());
        } else {
            System.out.println("FALHA no login. Usuário ou senha incorretos.");
        }

        System.out.println("\n-----------------------------------\n");

        // --- SIMULAÇÃO 2: Senha Errada ---
        System.out.println("Tentativa 2: Senha errada...");
        Funcionario usuarioInvalido = loginService.tentarLogin("carlos.a", "senhaerrada");

        if (usuarioInvalido != null) {
            System.out.println("Login BEM-SUCEDIDO! (Isso não deveria acontecer)");
        } else {
            System.out.println("FALHA no login. Usuário ou senha incorretos.");
        }
    }
}