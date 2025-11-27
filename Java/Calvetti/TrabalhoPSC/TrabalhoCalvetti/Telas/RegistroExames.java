
import javax.swing.*;
import java.awt.*;

public class RegistroExames {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Resultados de Exames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

       
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);

        JButton botaoVoltar = new JButton("Voltar à página principal");
        topPanel.add(botaoVoltar, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Resultados de Exames", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titlePanel.add(titleLabel);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Sair");
        topPanel.add(exitButton, BorderLayout.EAST);

        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(inputPanel, BorderLayout.CENTER);

        inputPanel.add(new JLabel("CPF"));
        JTextField cpfField = new JTextField(10);
        inputPanel.add(cpfField);

        inputPanel.add(new JLabel("Período"));
        JComboBox<String> periodoCombo = new JComboBox<>(
                new String[] { "Todos os períodos", "Últimos 7 dias", "Último mês", "Último ano" });
        inputPanel.add(periodoCombo);

        JButton botaoPesquisa = new JButton("Pesquisar");
        inputPanel.add(botaoPesquisa);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(createFichaPanel("19403629"));
        bottomPanel.add(createFichaPanel("31329481"));
        bottomPanel.add(createFichaPanel("13194810"));
        bottomPanel.add(createFichaPanel("24615875"));

       
        frame.setVisible(true);
    }

    private static JPanel createFichaPanel(String fichaNumero) {
        JPanel fichaPanel = new JPanel();
        fichaPanel.setLayout(new BorderLayout());
        fichaPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        fichaPanel.setPreferredSize(new Dimension(600, 90));

        JButton expandButton = new JButton("▼");
        fichaPanel.add(expandButton, BorderLayout.WEST);

        JLabel fichaLabel = new JLabel("Ficha " + fichaNumero);
        fichaPanel.add(fichaLabel, BorderLayout.CENTER);

        JButton examsButton = new JButton("Todos os exames");
        fichaPanel.add(examsButton, BorderLayout.EAST);

        return fichaPanel;
    }
}