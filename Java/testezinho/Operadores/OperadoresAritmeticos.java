package Operadores;
import java.util.Scanner;

public class OperadoresAritmeticos {
    public static void main (String [] args) {
      Scanner media = new Scanner (System.in);
      System.out.println ("Digite o nome do aluno: ");
      String nome = media.nextLine();
      System.out.println ("Digite a nota do aluno: ");
      float nota = media.nextFloat();
      System.out.printf("A nota de %s é %.2f \n", nome, nota);
    }
}
         
