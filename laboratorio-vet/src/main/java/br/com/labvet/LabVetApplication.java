package br.com.labvet; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * classe principal que inicia TODA a aplicação Spring Boot.
 */
@SpringBootApplication
public class LabVetApplication {

    public static void main(String[] args) {
        // "liga" o servidor web embutido (Tomcat)
        // configura todo o Spring Boot.
        SpringApplication.run(LabVetApplication.class, args);

        System.out.println("\n=======================================================");
        System.out.println(">>> SERVIDOR WEB LABVET INICIADO COM SUCESSO! <<<");
        System.out.println(">>> Pronto para receber pedidos em http://localhost:8080 <<<");
        System.out.println("=======================================================\n");
    }

}