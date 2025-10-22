package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        setTitle("App Deporte - Inicio");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        try {
            Image img = ImageIO.read(getClass().getResource("/imagenes/mancuernas.jpg"));
            Image imgEscalada = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel lbl = new JLabel(new ImageIcon(imgEscalada));
            c.gridy = 0;
            c.gridwidth = 2;
            add(lbl, c);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        JButton btnEntrenamientos = new JButton("Entrenamientos");
        JButton btnMetricas = new JButton("Métricas de salud");

        btnEntrenamientos.addActionListener(e -> {
            new VentanaEntrenamientos().setVisible(true);
            dispose();
        });

        btnMetricas.addActionListener(e -> {
            new VentanaMetricas().setVisible(true);
            dispose();
        });

        c.gridwidth = 1;
        c.gridy = 1;
        add(btnEntrenamientos, c);

        c.gridy = 2;
        add(btnMetricas, c);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaInicio().setVisible(true);
        });
    }
}