package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class VentanaEntrenamientos extends JFrame {
    private JComboBox<String> cbGrupo, cbEjercicio;

    public VentanaEntrenamientos() {
        setTitle("Entrenamientos");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        cbGrupo = new JComboBox<>(new String[]{"Pecho", "Espalda", "Pierna"});
        cbEjercicio = new JComboBox<>();

        cbGrupo.addActionListener(e -> actualizarEjercicios());

        c.gridx = 0; c.gridy = 0;
        add(new JLabel("Grupo muscular:"), c);
        c.gridx = 1;
        add(cbGrupo, c);

        c.gridx = 0; c.gridy = 1;
        add(new JLabel("Ejercicio:"), c);
        c.gridx = 1;
        add(cbEjercicio, c);

        try {
            URL url = new URL("https://img.freepik.com/psd-gratis/luchador-posando-aislado_23-2151871993.jpg");
            Image img = javax.imageio.ImageIO.read(url);
            Image imgEscalada = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(imgEscalada));
            c.gridx = 0; 
            c.gridy = 2; 
            c.gridwidth = 2;
            add(lblImg, c);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de internet: " + e.getMessage());
        }

        JButton btnExportar = new JButton("Exportar a Excel");
        btnExportar.addActionListener(e -> {
            String grupo = (String) cbGrupo.getSelectedItem();
            String ejercicio = (String) cbEjercicio.getSelectedItem();

            if (ejercicio == null || grupo == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un grupo y un ejercicio antes de exportar.");
                return;
            }

            CrearExcel.exportarEntrenamiento(grupo, ejercicio);
            JOptionPane.showMessageDialog(this, "Entrenamiento exportado correctamente a Excel.");
        });

        c.gridy = 3; c.gridwidth = 2;
        add(btnExportar, c);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            new VentanaInicio().setVisible(true);
            dispose();
        });

        c.gridy = 4;
        add(btnVolver, c);

        actualizarEjercicios();
    }

    private void actualizarEjercicios() {
        String grupo = (String) cbGrupo.getSelectedItem();
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        if (grupo.equals("Pecho")) {
            modelo.addElement("Press banca");
            modelo.addElement("Aperturas");
        } else if (grupo.equals("Espalda")) {
            modelo.addElement("Dominadas");
            modelo.addElement("Remo");
        } else if (grupo.equals("Pierna")) {
            modelo.addElement("Sentadillas");
            modelo.addElement("Prensa");
        }

        cbEjercicio.setModel(modelo);
    }
}