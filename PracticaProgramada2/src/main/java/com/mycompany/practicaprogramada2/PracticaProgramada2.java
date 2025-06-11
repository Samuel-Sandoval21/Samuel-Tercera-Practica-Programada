/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicaprogramada2;

import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author HP
 */
public class PracticaProgramada2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora2 calculadora = new Calculadora2();
            calculadora.setVisible(true);
        });
    }
}
