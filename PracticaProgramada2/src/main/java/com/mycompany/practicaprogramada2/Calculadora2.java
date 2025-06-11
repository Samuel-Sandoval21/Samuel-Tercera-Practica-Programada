/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaprogramada2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author HP
 */
public class Calculadora2 extends JFrame {

    private JTextField display;
    private String operador = "";
    private double valorAnterior = 0;
    private boolean nuevaOperacion = true;

    public Calculadora2() {
        setTitle("Calculadora Basica De Samuel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display (pantalla de la calculadora)
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Panel Botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] botones = {
            "AC", "←", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=",};

        // Añadir los botones al panel
        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.addActionListener(this::accionBoton);
            panelBotones.add(btn);
        }

        // Para que "0", ".", "=", llenen toda la fila final
        panelBotones.add(new JLabel()); // Celda vacia

        add(panelBotones, BorderLayout.CENTER);
    }

    public void accionBoton(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) {
            if (nuevaOperacion) {
                display.setText(comando);
                nuevaOperacion = false;

            } else {
                display.setText(display.getText().equals("0") ? comando : display.getText() + comando);

            }

        } else if (comando.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");

            }

        } else if (comando.equals("AC")) {
            display.setText("0");
            valorAnterior = 0;
            operador = "";
            nuevaOperacion = true;

        } else if (comando.equals("←")) {
            String texto = display.getText();
            if (texto.length() > 1) {
                display.setText(texto.substring(0, texto.length() - 1));
            } else {
                display.setText("0");
            }
        } else if (comando.equals("%")) {

            try {
                double valor = Double.parseDouble(display.getText());
                display.setText(String.valueOf(valor / 100));
            } catch (NumberFormatException ex) {
                mostrarError();
            }
        } else if (comando.matches(
                "[÷×\\-+]")) {
            try {
                valorAnterior = Double.parseDouble(display.getText());
                operador = comando;
                nuevaOperacion = true;
            } catch (NumberFormatException ex) {
                mostrarError();
            }
        } else if (comando.equals(
                "=")) {
            try {
                double valorActual = Double.parseDouble(display.getText());
                double resultado = switch (operador) {
                    case "+" ->
                        valorAnterior + valorActual;
                    case "-" ->
                        valorAnterior - valorActual;
                    case "×" ->
                        valorAnterior * valorActual;
                    case "÷" -> {
                        if (valorActual == 0) {
                            mostrarError("Division por cero");
                            yield valorAnterior;
                        }
                        yield valorAnterior / valorActual;
                    }
                    default ->
                        valorActual;
                };
                display.setText(String.valueOf(resultado));
                nuevaOperacion = true;
            } catch (NumberFormatException ex) {
                mostrarError();
            }
        }

    }

    public void mostrarError() {
        mostrarError("Error: Entrada invalida");
    }

    public void mostrarError(String mensaje) {
        display.setText("0");
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
