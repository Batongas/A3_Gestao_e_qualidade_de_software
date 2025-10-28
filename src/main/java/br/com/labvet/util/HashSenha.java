package br.com.labvet.util; 

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Esta classe é uma "ferramenta" que vamos usar
public class HashSenha {

    // Este método recebe uma senha (ex: "123456") e retorna o hash SHA-256
    public static String hashPassword(String password) {
        try {
            // Obtém uma instância do algoritmo de hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Calcula o hash
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Converte o array de bytes para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            // Em um mundo ideal, este erro nunca deve acontecer com SHA-256
            System.err.println("Erro: Algoritmo de Hashing não encontrado.");
            e.printStackTrace();
            return null;
        }
    }
}