import javax.swing.JOptionPane;

public class CorredorTeste{
    private String nome; 
    private int nota;
    private int trofeus;
    private String tipo;
    
    public CorredorTeste(String nome, int nota, int trofeus, String tipo){
        this.nome = nome; 
        this.nota = 0;
        this.trofeus = 0;
        this.tipo = tipo;
    }

    public String getNome(){
        return this.nome;
    }
    public int getNota(){
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
    public void setNota(int nota){
        this.nota = nota;
    }
    public void setTrofeus(int trofeus){
        this.trofeus = trofeus;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void aumentaTrofeus(int quantidade) {
       this.trofeus += quantidade;
    }
    public void definirNotaDesempenho(int nota) {
        this.nota += nota;
    }

    public void tipo (String tipo ) {
      if (trofeus >=5);
        tipo = "Campeão";
      if (trofeus <5);
        tipo = "Normal";
    }

    public String toString() {
        return "Corredor: " + nome + ", Nota: " + nota + ", Trofeus: " + trofeus + ", Tipo: " + tipo;
    }
      
    public void main (String args []){
         String nomeCorredor = JOptionPane.showInputDialog("Digite o nome do corredor:");

         
         CorredorTeste c1 = new CorredorTeste(nomeCorredor);
 
        
         c1.aumentarTrofeus(7);
         c1.aumentarTrofeus(3);
 
         
         double totalNotas = 0;
         for (int i = 0; i < 100; i++) {
             String notaStr = JOptionPane.showInputDialog("Digite a nota de desempenho " + (i + 1) + ":");
             double nota = Double.parseDouble(notaStr);
             totalNotas += nota;
         }
         double mediaNotas = totalNotas / 100;
 
         
         c1.definirNotaDesempenho(mediaNotas);
 
    
    } 
}
