import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SuperCalculadora {
    static JLabel l1, l2, l3, l4, l5;
    static JButton b1;
    static int i;
    
    public static void main(String args[]) {    
        i = Integer.parseInt(JOptionPane.showInputDialog("Informe um valor: "));

        double restoDivisaoPor2 = i % 2;
        l1 = new JLabel("Resto da divisão por 2: " + restoDivisaoPor2);

        double potenciaDe3 = Math.pow(i, 3);
        l2 = new JLabel("Potência de 3: " + potenciaDe3);

        double raizQuadrada = Math.sqrt(i);
        l3 = new JLabel("Raiz quadrada: " + raizQuadrada);

        double raizCubica = Math.cbrt(i);
        l4 = new JLabel("Raiz cúbica: " + raizCubica);
        
        int valorAbsoluto = Math.abs(i);
        l5 = new JLabel("Valor absoluto: " + valorAbsoluto);

        JFrame frame = new JFrame("Super Calculadora");
        frame.setLayout(null);
        frame.setSize(400, 300);

        l1.setBounds(10, 10, 300, 20);
        l2.setBounds(10, 40, 300, 20);
        l3.setBounds(10, 70, 300, 20);
        l4.setBounds(10, 100, 300, 20);
        l5.setBounds(10, 130, 300, 20);

        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);

        frame.setVisible(true);
    }
}