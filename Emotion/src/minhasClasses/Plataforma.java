/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhasClasses;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Eduardo
 */
public class Plataforma extends Componente{
    
    public int largura;
    public int altura;
    public boolean visivel;

    public Plataforma(int largura, int altura, float x, float y) {
        super(x, y);
        this.largura = largura;
        this.altura = altura;
        visivel=true;
    }
    
    
    @Override
    public void update(float time, int cameraX, int cameraY) {
        
    }
    
    public boolean colideBola(Bola player){
        /*
        if ( (x+tamanho) >= obj_x && x <= obj_x+obj_tam ){
         if ( (y+tamanho) >= obj_y && y <= obj_y+obj_tam ){
        */
        if(x+largura>=player.x && x<=player.x+player.raio){
            if(y+altura >= player.y && y<=player.y+player.raio){
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
        if(x+largura-cameraX>=player.x && x-cameraX<=player.x+player.raio){
            if(y+altura >= player.y && y<=player.y+player.raio){
                return true;
            }
        }
        return false;
    }
    
    public boolean colidePlayer(Personagem player){
        /*
        if ( (x+tamanho) >= obj_x && x <= obj_x+obj_tam ){
         if ( (y+tamanho) >= obj_y && y <= obj_y+obj_tam ){
        */
        if(x+largura>=player.x && x<=player.x+player.largura){
            if(y+altura >= player.y && y<=player.y+player.altura){
                return true;
            }
        }
        
        return false;
    }
    
    

    @Override
    public void draw(Graphics2D dbg,float xMundo, float yMundo) {
        dbg.setColor(Color.CYAN);
        dbg.fillRect((int)(x-xMundo), (int)(y), 
                largura, altura);
    }
    
}
