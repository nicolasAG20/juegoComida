/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegocomida.models;

import baseJuego.models.GraphicContainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nico
 */
public class CampoDeComida extends JPanel implements GraphicContainer, MouseListener{
    private ArrayList<Comida> comidas = new ArrayList<>(); 
    private ArrayList<Veneno> venenos= new ArrayList<>();
    private int puntos=0; 

    public CampoDeComida() {
        
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        
        addMouseListener(this);
        
    }    
     
    @Override
    public void mouseClicked(MouseEvent e) {
        checkComidaVenenoClickeado(e.getX(), e.getY());
    }
    
    public void checkComidaVenenoClickeado(int x , int y){
        for (int i=0; i<comidas.size();i++){
            Comida comida = comidas.get(i); 
            Rectangle bounds = new Rectangle(comida.getX(), comida.getY(), comida.getWidth(), comida.getHeight());
            if (bounds.contains(x,y)){
                comidas.remove(i);
                puntos++;
            }
        }
         for (int i=0; i<venenos.size();i++){
            Veneno veneno = venenos.get(i); 
            Rectangle bounds = new Rectangle(veneno.getX(), veneno.getY(), veneno.getWidth(), veneno.getHeight());
            if (bounds.contains(x,y)){
                venenos.remove(i);
                puntos-=2;
            }
        }
    }
    
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rectangle getBoundaries() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
