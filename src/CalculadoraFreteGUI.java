/*import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculadoraFreteGUI {
    private JFrame frame;
    private JTextField distanciaField, precoDieselField, pesoCargaField, pedagioField, comissaoField, freteKmField, kmPorLitroField;
    private JTextArea resultadoArea;

    public CalculadoraFreteGUI() {
        frame = new JFrame("Calculadora de Frete");
        frame.setSize(400, 500);
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

        resultadoArea = new JTextArea();
        resultadoArea.setBounds(20, 300, 350, 160);
        resultadoArea.setEditable(false);
        frame.add(resultadoArea);

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularFrete();
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
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraFreteGUI();
            }
        });
    }
}
*/


import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculadoraFreteGUI {
    private JFrame frame;
    private JTextField distanciaField, precoDieselField, pesoCargaField, pedagioField, comissaoField, freteKmField, kmPorLitroField;
    private JTextArea resultadoArea;

    public CalculadoraFreteGUI() {
        frame = new JFrame("Calculadora de Frete");
        frame.setSize(400, 500);
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

        JLabel comissaoLabel = new JLabel("Comissão :");
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

        resultadoArea = new JTextArea();
        resultadoArea.setBounds(20, 350, 350, 160);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraFreteGUI();
            }
        });
    }
}
