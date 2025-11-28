package br.com.labvet; // O pacote raiz do seu projeto

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esta é a classe principal que inicia TODA a aplicação Spring Boot.
 * É o novo ponto de partida (o novo "main").
 */
@SpringBootApplication
public class LabVetApplication {

    public static void main(String[] args) {
        // Esta linha "liga" o servidor web embutido (Tomcat)
        // e configura todo o Spring Boot.
        SpringApplication.run(LabVetApplication.class, args);

        System.out.println("\n=======================================================");
        System.out.println(">>> SERVIDOR WEB LABVET INICIADO COM SUCESSO! <<<");
        System.out.println(">>> Pronto para receber pedidos em http://localhost:8080 <<<");
        System.out.println("=======================================================\n");
    }

}