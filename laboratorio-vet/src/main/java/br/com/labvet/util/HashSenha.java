package br.com.labvet.util; 

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSenha {

    public static String hashPassword(String password) {
        try {
            // algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calcula o hash da senha
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Converte o array de bytes para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Formata cada byte como dois dígitos hexadecimais
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erro: Algoritmo de Hashing SHA-256 não encontrado.");
            e.printStackTrace();
            return null;
        }
    }
}