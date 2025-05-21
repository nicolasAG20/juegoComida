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
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Nico
 */
public class CampoDeComida extends JPanel implements GraphicContainer, MouseListener,Runnable{
    private ArrayList<Comida> comidas = new ArrayList<>(); 
    private ArrayList<Veneno> venenos= new ArrayList<>();
    private int puntos=0; 
    private Random rand = new Random();
    private boolean enJuego = true;
    private Thread gameThread;
    
    public CampoDeComida() {
        
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        addMouseListener(this);
        gameThread = new Thread(this);
        gameThread.start();        
    }
    
    @Override
    public void run() {
        while (enJuego) {
            if (getWidth() > 40) {
                
                for (int i = comidas.size() - 1; i >= 0; i--) {
                    if (!comidas.get(i).isActivo()) {
                        comidas.remove(i);
                    }
                }

                for (int i = venenos.size() - 1; i >= 0; i--) {
                    if (!venenos.get(i).isActivo()) {
                        venenos.remove(i);
                    }
                }
                
                if (comidas.size() < 4) {
                    agregarComida();
                }
                if (venenos.size() < 4) {
                    agregarVeneno();
                }
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void agregarComida() {
        Rectangle newBounds;
        int x, y = 0;
        do {
            x = rand.nextInt(getWidth() - 40);
            newBounds = new Rectangle(x, y, 40, 40);
        } while (checkPosition(newBounds));

        Comida c = new Comida(x, y, 40, 40, "/imagesComida/comida.png",getHeight());
        comidas.add(c);
        new Thread(c).start();
    }

    public void agregarVeneno() {
        Rectangle newBounds;
        int x, y = 0; 
        do {
            x = rand.nextInt(getWidth() - 40);
            newBounds = new Rectangle(x, y, 40, 40);
        } while (checkPosition(newBounds));

        Veneno v = new Veneno(x, y, 40, 40, "/imagesComida/veneno.png",getHeight());
        venenos.add(v);
        new Thread(v).start();
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
                break;
            }
        }
         for (int i=0; i<venenos.size();i++){
            Veneno veneno = venenos.get(i); 
            Rectangle bounds = new Rectangle(veneno.getX(), veneno.getY(), veneno.getWidth(), veneno.getHeight());
            if (bounds.contains(x,y)){
                venenos.remove(i);
                puntos-=2;
                break;
            }
        }
    }
           
    public boolean seSuperponen(Rectangle r1, Rectangle r2) {
        if (r1.x + r1.width <= r2.x || r2.x + r2.width <= r1.x) {
            return false;
        }
        if (r1.y + r1.height <= r2.y || r2.y + r2.height <= r1.y) {
            return false;
        }       
        return true;
    }
    
    public boolean checkPosition(Rectangle newBounds) {
        for (Comida c : comidas) {
            Rectangle comBounds = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());
            if (seSuperponen(newBounds, comBounds)) {
                return true; 
            }
        }
        for (Veneno v : venenos) {
            Rectangle venBounds = new Rectangle(v.getX(), v.getY(), v.getWidth(), v.getHeight());
            if (seSuperponen(newBounds, venBounds)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        for (Comida comida : comidas) {
            comida.paint(g);
        }
        for (Veneno veneno : venenos) {
            veneno.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Puntos: " + puntos, 10, 20);
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
    public void refresh() {}

    @Override
    public Rectangle getBoundaries() {
        return null;
    }
    
    
}
