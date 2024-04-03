import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalculadoraFreteGUI {
    private JFrame frame;
    private JTextField distanciaField, precoDieselField, pesoCargaField, pedagioField, comissaoField, freteKmField, kmPorLitroField;
    private JTextArea resultadoArea;

    public CalculadoraFreteGUI() {
        frame = new JFrame("Calculadora de Frete");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel distanciaLabel = new JLabel("Distância (KM):");
        distanciaField = new JTextField();
        JLabel precoDieselLabel = new JLabel("Preço do diesel por litro:");
        precoDieselField = new JTextField();
        JLabel pesoCargaLabel = new JLabel("Peso da carga (toneladas):");
        pesoCargaField = new JTextField();
        JLabel pedagioLabel = new JLabel("Valor do pedágio:");
        pedagioField = new JTextField();
        JLabel comissaoLabel = new JLabel("Comissão:");
        comissaoField = new JTextField();
        JLabel freteKmLabel = new JLabel("Frete por TON:");
        freteKmField = new JTextField();
        JLabel kmPorLitroLabel = new JLabel("KM por litro de diesel:");
        kmPorLitroField = new JTextField();

        inputPanel.add(distanciaLabel);
        inputPanel.add(distanciaField);
        inputPanel.add(precoDieselLabel);
        inputPanel.add(precoDieselField);
        inputPanel.add(pesoCargaLabel);
        inputPanel.add(pesoCargaField);
        inputPanel.add(pedagioLabel);
        inputPanel.add(pedagioField);
        inputPanel.add(comissaoLabel);
        inputPanel.add(comissaoField);
        inputPanel.add(freteKmLabel);
        inputPanel.add(freteKmField);
        inputPanel.add(kmPorLitroLabel);
        inputPanel.add(kmPorLitroField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton calcularButton = new JButton("Calcular Frete");
        calcularButton.setIcon(new ImageIcon("calculate_icon.png"));
        JButton limparButton = new JButton("Limpar Dados");
        limparButton.setIcon(new ImageIcon("clear_icon.png"));
        JButton visualizarPesquisasButton = new JButton("Visualizar Pesquisas Antigas");
        visualizarPesquisasButton.setIcon(new ImageIcon("history_icon.png"));

        buttonPanel.add(calcularButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(visualizarPesquisasButton);

        resultadoArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void calcularFrete() {
        try {
            double distancia = Double.parseDouble(distanciaField.getText().replace(',', '.'));
            double precoDiesel = Double.parseDouble(precoDieselField.getText().replace(',', '.'));
            double pesoCarga = Double.parseDouble(pesoCargaField.getText().replace(',', '.'));
            double pedagio = Double.parseDouble(pedagioField.getText().replace(',', '.'));
            double comissao = Double.parseDouble(comissaoField.getText().replace(',', '.'));
            double freteKm = Double.parseDouble(freteKmField.getText().replace(',', '.'));
            double kmPorLitro = Double.parseDouble(kmPorLitroField.getText().replace(',', '.'));

            double freteTotal = freteKm * pesoCarga;
            double consumoDiesel = distancia / kmPorLitro;
            double custoDiesel = consumoDiesel * precoDiesel;
            double comissaoValor = freteTotal * (comissao / 100);
            double freteLiquido = freteTotal - comissaoValor - pedagio - custoDiesel;
            double custoKMrodado = freteTotal / distancia;

            DecimalFormat df = new DecimalFormat("#,##0.00");
            resultadoArea.setText("Frete total: R$ " + df.format(freteTotal) + "\n" +
                    "Consumo de diesel: R$ " + df.format(custoDiesel) + "\n" +
                    "Comissão: R$ " + df.format(comissaoValor) + "\n" +
                    "Frete líquido: R$ " + df.format(freteLiquido) + "\n" +
                    "Custo por KM rodado: R$ " + df.format(custoKMrodado));

            // Salvando a pesquisa
            salvarPesquisa(distancia, precoDiesel, pesoCarga, pedagio, comissao, freteKm, kmPorLitro);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        distanciaField.setText("");
        precoDieselField.setText("");
        pesoCargaField.setText("");
        pedagioField.setText("");
        comissaoField.setText("");
        freteKmField.setText("");
        kmPorLitroField.setText("");
        resultadoArea.setText("");
    }

    private void visualizarPesquisas() {
        ArrayList<String> pesquisas = lerPesquisas();
        if (pesquisas.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma pesquisa encontrada.", "Pesquisas Antigas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JFrame visualizarPesquisasFrame = new JFrame("Pesquisas Antigas");
            visualizarPesquisasFrame.setSize(400, 400);
            visualizarPesquisasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            visualizarPesquisasFrame.setLayout(new BorderLayout());
            JTextArea pesquisasTextArea = new JTextArea();
            pesquisasTextArea.setEditable(false);

            for (String pesquisa : pesquisas) {
                String[] dados = pesquisa.split("\n"); // Divide a pesquisa em cada linha
                for (String dado : dados) {
                    pesquisasTextArea.append(dado + "\n"); // Adiciona cada dado da pesquisa em uma linha separada
                }
                pesquisasTextArea.append("-----------------------------\n"); // Adiciona uma linha horizontal entre cada pesquisa
            }

            JScrollPane scrollPane = new JScrollPane(pesquisasTextArea);
            visualizarPesquisasFrame.add(scrollPane, BorderLayout.CENTER);

            visualizarPesquisasFrame.setVisible(true);
        }
    }

    private void salvarPesquisa(double distancia, double precoDiesel, double pesoCarga, double pedagio, double comissao, double freteKm, double kmPorLitro) {
        try (FileWriter writer = new FileWriter("pesquisas.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            DecimalFormat df = new DecimalFormat("#.##");
            String pesquisa = "Data: " + getDataAtual() + "\n" +
                    "Distância: " + df.format(distancia) + " KM\n" +
                    "Preço do Diesel: R$ " + df.format(precoDiesel) + " por litro\n" +
                    "Peso da Carga: " + df.format(pesoCarga) + " toneladas\n" +
                    "Pedágio: R$ " + df.format(pedagio) + "\n" +
                    "Comissão: " + df.format(comissao) + "%\n" +
                    "Frete por TON: R$ " + df.format(freteKm) + "\n" +
                    "KM por litro de Diesel: " + df.format(kmPorLitro) + "\n";
            bw.write(pesquisa);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> lerPesquisas() {
        ArrayList<String> pesquisas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pesquisas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                pesquisas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pesquisas;
    }

    private String getDataAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraFreteGUI();
            }
        });
    }
}
