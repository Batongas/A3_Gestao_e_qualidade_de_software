package ExercicioTriangulos;
import java.util.Scanner;
public class ProgramaTriangulo {
public static void main (String[] args){

   Scanner scanner = new Scanner (System.in); 
     
   System.out.println("Digite o primeiro lado:");
   int L1 = scanner.nextInt();
   System.out.println("Digite o segundo lado:");
   int L2 = scanner.nextInt();
   System.out.println("Digite o terceiro lado:");
   int L3 = scanner.nextInt();
    
   boolean resultado = false, equilatero = false, escaleno = false;
   if (L1 < L2 + L3 && L2 < L1 + L3 && L3 < L1 + L2) {
       resultado = true;
   }
   if (L1 == L2 && L2 == L3){
       equilatero = true;
   }
   if (L1 != L2 && L2 != L3 && L1 != L3) {
       escaleno = true;
   }
  
   if (resultado) {
       System.out.println("É um Triangulo");
   }
   if (equilatero){
       System.out.println("O triangulo é equilatero");
   }
   if (escaleno) {
       System.out.println("O triangulo é escaleno");
   }

 }
}
