/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhasClasses;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Eduardo
 */
public class Personagem extends Componente{
    
    public BufferedImage img;
    public float veloX;
    public float veloY;
    public float oldX;
    public float oldY;
    public int altura;
    public int largura;
    public boolean isJumping;
    public float velocidade;
    //velocidade da animação, resumidamente é quanto tempo vai demorar para desenhar o proximo quadro da animação
    //valor em Milesegundos, 1000 = 1 segundo
    int timeAnimation=157;
    //index da animação, indica qual frame do sprite sheet estamos desenhando
    int indexAnimation=0;
    int aux=0;
    //index da linha que queremos animar
    public int indexLinha=0;
    boolean parado=true;
    
    public Personagem(float x, float y, int largura,int altura
            , float veloX, float veloY, BufferedImage img){
        super(x, y);
        this.img=img;
        this.veloX=veloX;
        this.veloY=veloY;
        this.altura=altura;
        this.largura=largura;
        isJumping=true;
        velocidade=0.5f;
        para(0);
    }
    
    @Override
    public void update(float time, int cameraX, int cameraY) {
        
        oldX=x;
        oldY=y;
        x+=veloX*time;
        y+=veloY*time;
        
        if(!parado){
            aux+=time;
            if(aux>=timeAnimation){
            aux=0;
            indexAnimation++;
            if(indexAnimation>5)
                indexAnimation=0;
            }
        }
        
        
        
    }
    
     public void andaDireita(){
        indexLinha=0;
        parado=false;
    } 
    
    public void andaEsquerda(){
        indexLinha=1;
        parado=false;
    }  
    
    public void para(int coluna){
        indexLinha=2;
        parado=true;
        indexAnimation=coluna;
    } 


    @Override
    public void draw(Graphics2D dbg,float xMundo, float yMundo) {
        //dbg.fillRect((int)x, (int)y, largura, altura);
            
        dbg.drawImage(img,(int) x,(int) y,//x e y na tela
                largura+(int)x, altura+(int)y,//largura e altura
               103*indexAnimation,150*indexLinha,//x e y do rect Source
                103*(indexAnimation+1),150*(indexLinha+1)//largura e altura do rect source
                , null);
        /*
        dbg.drawImage(sprite,(int)X-xmundo,(int)Y-ymundo,// left, top
					(int)X+width-xmundo,(int)Y+height-ymundo,// right, bottom
					sourceWidth*frame,sourceHeigt*animation,// lef, top source
					(sourceWidth*frame)+sourceWidth, (sourceHeigt*animation)+sourceHeigt,//right, bottom source
					null);//null
        */
    }
    
}
