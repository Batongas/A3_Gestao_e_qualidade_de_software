import javax.swing.JOptionPane;

public class Corredor {
    private String nome; 
    private double nota;
    private int trofeus;
    private String tipo;
    
    public Corredor(String nome){
        this.nome = nome; 
        this.nota = 0;
        this.trofeus = 0;
        this.tipo = "Normal"; 
    }

    public String getNome(){
        return this.nome;
    }
    public double getNota(){
        return this.nota;
    }
    public int getTrofeus(){
        return this.trofeus;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNota(double nota){
        this.nota = nota;
    }
    public void setTrofeus(int trofeus){
        this.trofeus = trofeus;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void acrescentarTrofeus(int quantidade) {
       this.trofeus += quantidade;
       if (trofeus >= 5)
            this.tipo = "Campeão";
    }
    
    public void definirNotaDesempenho(double nota) {
        this.nota = nota;
    }

    public String toString() {
        return "Corredor: " + nome + " Nota: " + nota + " Trofeus: " + trofeus + " Tipo: " + tipo;
    }
      
    public static void main (String args []){
         String nomeCorredor = JOptionPane.showInputDialog("Digite o nome do corredor:");
         Corredor c1 = new Corredor(nomeCorredor);
 
         c1.acrescentarTrofeus(7);
         c1.acrescentarTrofeus(3);
 
         double totalNotas = 0;
         for (int i = 0; i < 100; i++) {
             String notacontador = JOptionPane.showInputDialog("Digite a nota de desempenho " + (i + 1) + ":");
             double nota = Double.parseDouble(notacontador);
             totalNotas += nota;
         }
         double mediaNotas = totalNotas / 100;
 
         c1.definirNotaDesempenho(mediaNotas);
         
         JOptionPane.showMessageDialog(null, c1.toString());
    }  
}

