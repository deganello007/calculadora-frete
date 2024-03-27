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
        frame.setLayout(null);

        JLabel distanciaLabel = new JLabel("Distância (KM):");
        distanciaLabel.setBounds(20, 20, 120, 25);
        frame.add(distanciaLabel);

        distanciaField = new JTextField();
        distanciaField.setBounds(150, 20, 200, 25);
        frame.add(distanciaField);

        JLabel precoDieselLabel = new JLabel("Preço do diesel por litro:");
        precoDieselLabel.setBounds(20, 50, 150, 25);
        frame.add(precoDieselLabel);

        precoDieselField = new JTextField();
        precoDieselField.setBounds(180, 50, 170, 25);
        frame.add(precoDieselField);

        JLabel pesoCargaLabel = new JLabel("Peso da carga (toneladas):");
        pesoCargaLabel.setBounds(20, 80, 180, 25);
        frame.add(pesoCargaLabel);

        pesoCargaField = new JTextField();
        pesoCargaField.setBounds(210, 80, 140, 25);
        frame.add(pesoCargaField);

        JLabel pedagioLabel = new JLabel("Valor do pedágio:");
        pedagioLabel.setBounds(20, 110, 120, 25);
        frame.add(pedagioLabel);

        pedagioField = new JTextField();
        pedagioField.setBounds(150, 110, 200, 25);
        frame.add(pedagioField);

        JLabel comissaoLabel = new JLabel("Comissão:");
        comissaoLabel.setBounds(20, 140, 120, 25);
        frame.add(comissaoLabel);

        comissaoField = new JTextField();
        comissaoField.setBounds(150, 140, 200, 25);
        frame.add(comissaoField);

        JLabel freteKmLabel = new JLabel("Frete por TON:");
        freteKmLabel.setBounds(20, 170, 120, 25);
        frame.add(freteKmLabel);

        freteKmField = new JTextField();
        freteKmField.setBounds(150, 170, 200, 25);
        frame.add(freteKmField);

        JLabel kmPorLitroLabel = new JLabel("KM por litro de diesel:");
        kmPorLitroLabel.setBounds(20, 200, 150, 25);
        frame.add(kmPorLitroLabel);

        kmPorLitroField = new JTextField();
        kmPorLitroField.setBounds(180, 200, 170, 25);
        frame.add(kmPorLitroField);

        JButton calcularButton = new JButton("Calcular Frete");
        calcularButton.setBounds(150, 240, 150, 30);
        frame.add(calcularButton);

        JButton limparButton = new JButton("Limpar Dados");
        limparButton.setBounds(150, 280, 150, 30);
        frame.add(limparButton);

        JButton visualizarPesquisasButton = new JButton("Visualizar Pesquisas Antigas");
        visualizarPesquisasButton.setBounds(50, 320, 300, 30);
        frame.add(visualizarPesquisasButton);

        resultadoArea = new JTextArea();
        resultadoArea.setBounds(20, 370, 350, 160);
        resultadoArea.setEditable(false);
        frame.add(resultadoArea);

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularFrete();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        visualizarPesquisasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarPesquisas();
            }
        });

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
