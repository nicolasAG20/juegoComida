/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegocomida.models;

import baseJuego.models.Sprite;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/**
 *
 * @author Nico
 */
public class Veneno extends Sprite  implements Runnable{
  
    private boolean activo = true;
    private int limiteInferior;
    
    public Veneno(int x, int y, int height, int width, String imagePath,int limiteInferior) {
        super(x, y, height, width);
        this.image = new ImageIcon(getClass().getResource(imagePath));
        this.limiteInferior = limiteInferior;
    }
    

    @Override
    public void paint(Graphics g) {
        Image image = this.image.getImage();
        g.setColor(color);
        g.drawImage(image, x, y, width, height, color, null);
    }
    
    @Override
    public void run() {
        while (activo) {
            this.y += 1;
            if (this.y > limiteInferior) {
                detener();
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void detener() {
        this.activo = false;
    }

    public boolean isActivo() {
        return activo;
    }
    
}
