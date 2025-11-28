package br.com.labvet.run;

// Importar tudo o que precisamos
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestaHash {

    public static void main(String[] args) {
        String senhaParaTestar = "senhaforte123"; // f minúsculo, sem espaço

        System.out.println("--- TESTE DE HASH ISOLADO ---");
        System.out.println("Senha pura: '" + senhaParaTestar + "'");
        System.out.println("Comprimento da senha: " + senhaParaTestar.length());

        // --- Vamos colocar a lógica do HashSenha.java AQUI DENTRO ---
        String hashGerado = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senhaParaTestar.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hashGerado = hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            hashGerado = "ERRO NO ALGORITMO";
        }
        // --- Fim da lógica do hash ---

        System.out.println("Hash gerado (teste isolado): " + hashGerado);
    }
}