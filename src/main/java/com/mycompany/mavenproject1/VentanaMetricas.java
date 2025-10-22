package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VentanaMetricas extends JFrame {
    private JTextField tfAltura, tfPeso;
    private JLabel lblResultado;

    public VentanaMetricas() {
        setTitle("Métricas de Salud");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        tfAltura = new JTextField(10);
        tfPeso = new JTextField(10);
        lblResultado = new JLabel("Tu IMC aparecerá aquí");

        JButton btnCalcular = new JButton("Calcular IMC");
        btnCalcular.addActionListener(e -> calcularIMC());

        c.gridx = 0; c.gridy = 0;
        add(new JLabel("Altura (m):"), c);
        c.gridx = 1;
        add(tfAltura, c);

        c.gridx = 0; c.gridy = 1;
        add(new JLabel("Peso (kg):"), c);
        c.gridx = 1;
        add(tfPeso, c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        add(btnCalcular, c);

        c.gridy = 3;
        add(lblResultado, c);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            new VentanaInicio().setVisible(true);
            dispose();
        });
        c.gridy = 4;
        add(btnVolver, c);
    }

    private void calcularIMC() {
        try {
            double altura = Double.parseDouble(tfAltura.getText());
            double peso = Double.parseDouble(tfPeso.getText());
            double imc = peso / (altura * altura);

            lblResultado.setText(String.format("Tu IMC es: %.2f", imc));

            CrearExcelMetricas.exportarIMC(altura, peso, imc);
            JOptionPane.showMessageDialog(this, "Métrica exportada correctamente a Excel.");

        } catch (Exception e) {
            lblResultado.setText("Ingresa valores válidos");
        }
    }
}