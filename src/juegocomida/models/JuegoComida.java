/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegocomida.models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nico
 */
public class JuegoComida {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        CampoDeComida campoComida = new CampoDeComida();
        ventana.setResizable(false);      
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ventana.add(campoComida);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setTitle("Campo de comida");

        ventana.setVisible(true); 
    }
    
}
