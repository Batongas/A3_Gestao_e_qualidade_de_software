package br.com.labvet.run;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost;databaseName=A3CC3S;user=java_user;password=123456;trustServerCertificate=true;";
        try (Connection conexao = DriverManager.getConnection(url)) {
            System.out.println("Conexão com o Maven funcionou! Banco de dados conectado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}