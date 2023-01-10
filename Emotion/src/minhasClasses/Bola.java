/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhasClasses;

import gameMain.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Eduardo
 */
public class Bola extends Componente{
    public Color cor;
    public int raio;
    public float veloX;
    public float veloY;
    public float oldX;
    public float oldY;
    Random rnd = new Random();
    float gravidade=9.8f;
    
    public Bola(float x, float y, int raio, float veloX, float veloY, Color cor) {
        super(x+raio, y+raio);
        this.cor=cor;
        this.raio=raio;
        this.veloX=veloX;
        this.veloY=veloY;
    }

    @Override
    public void update(float time,int cameraX, int cameraY) {
        //olds
        oldX=x;
        oldY=y;
        x+=veloX*time;
        y+=veloY*time;
       //y+=(veloY*time+(gravidade*gravidade*time)/2)*0.01f;
        
        x-=cameraX;
        //colisões
        //parade de baixo
        if(y+raio>=GamePanel.PHEIGHT){
        	veloY*=-1;
        	y=GamePanel.PHEIGHT-raio;
        }
        //parede da direita
        if(x+raio>=GamePanel.PWIDTH){
        	veloX*=-1;
        	x=GamePanel.PWIDTH-raio;
        }
        //parede de cima
        if(y-raio<=0){
        	veloY*=-1;
        	y=raio;
        }
        //parede da esquerda
        if(x-raio<=0){
        	veloX*=-1;
        	x=raio;
        }
        
            
    }

    @Override
    public void draw(Graphics2D dbg,float xMundo, float yMundo) {
        dbg.setColor(cor);
        dbg.fillOval((int)x-raio-(int)xMundo, (int)y-raio, raio*2, raio*2);
    }
    
    public boolean colideBola(Bola player){
        /*
        if ( (x+tamanho) >= obj_x && x <= obj_x+obj_tam ){
         if ( (y+tamanho) >= obj_y && y <= obj_y+obj_tam ){
        */
        if(x+raio>=player.x && x<=player.x+player.raio){
            if(y+raio >= player.y && y<=player.y+player.raio){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean colideBolaCorreto(Bola player, int cameraX){
        /*
        if ( (x+tamanho) >= obj_x && x <= obj_x+obj_tam ){
         if ( (y+tamanho) >= obj_y && y <= obj_y+obj_tam ){
        */
        if(x+raio-cameraX>=player.x && x-cameraX<=player.x+player.raio){
            if(y+raio >= player.y && y<=player.y+player.raio){
                return true;
            }
        }
        
        return false;
    }
    
}
