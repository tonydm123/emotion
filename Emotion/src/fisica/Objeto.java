package fisica;

import gameMain.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import minhasClasses.Componente;

public class Objeto extends Componente{
	boolean isEffect;//nao ira colidir com nada
	boolean isAlive;//se estiver vivo sendo simulado
	boolean useGravity;//se usa gravidade
	boolean isGrounded;//se esta imovel no chao ou emcima de alguma coisa
	
	public float oldX, oldY;
	public float vX, vY;
	public float massa;
	
	public int largura,altura;
	public Color cor;
	
	public int lifeTime;
	public int gPid;
	
	public Objeto(int x, int y, float vX, float vY,
			int largura, int altura, Color cor,
			boolean isEffect, boolean useGravity
			, int gpId) {
		// TODO Auto-generated constructor stub
		super(x,y);
		isAlive=true;
		isGrounded=false;
		this.vX=vX;
		this.vY=vY;
		this.largura=largura;
		this.altura=altura;
		this.cor=cor;
		this.isEffect=isEffect;
		this.useGravity=useGravity;
		oldX=-1;
		oldY=-1;
		massa=10;
		lifeTime=0;
		this.gPid=gpId;
	}
	

	public Objeto copyEffect(boolean left, boolean down){
//		Objeto retorno = new Objeto((int)x,(int) y,
//				(GamePanel.rnd.nextInt((int)(vX*10))+1)/10,
//				(GamePanel.rnd.nextInt((int)(vY*10))+1)/10*-1, 
//				largura, altura, cor, isEffect, useGravity
//				,gPid);
		float vvX;
		float vvY;
		if(left){
			vvX = GamePanel.rnd.nextInt((int)(vX*2*100));
			vvX/=100;
			vvX-=vX;
			
		}else{
			vvX = GamePanel.rnd.nextInt((int)(vX*100));
			vvX/=100;
		}
		if(down){
			vvY = GamePanel.rnd.nextInt((int)(vY*2*100));
			vvY/=100;
			vvY-=vY;
			
		}else{
			vvY = GamePanel.rnd.nextInt((int)(vY*100));
			vvY/=100;
		}
		
		if(vvY==0){
			if(GamePanel.rnd.nextBoolean())
			vvY=vY;
			else
			vvY=0.05f;
		}
		if(vvX==0){
			if(GamePanel.rnd.nextBoolean())
			vvX=vX;
			else
			vvX=0.05f;
		}
		//System.out.println(vvX);
		Objeto retorno = new Objeto((int)x,(int) y,
				vvX,
				vvY*-1, 
				largura, altura, cor, isEffect, useGravity
				,gPid);
		
		return retorno;
	}
	
	@Override
	public void update(float time, int cameraX, int cameraY) {
		// TODO Auto-generated method stub
		lifeTime+=time;
		if(!isGrounded){
			if(oldX==x && oldY==y){
				isGrounded=true;
			}
			oldX=(int)x; oldY=(int)y;
			
			
			//vX-=(vX*ControleFisica.resistenciaAr);//atrito
			x+=time*vX;
			
			y+=time*vY;
			//vY-=(vY*ControleFisica.atrito);//atrito
			if(useGravity){
				vY+=time*ControleFisica.gravidade;//gravidade
			}
		}else{
			vX=0;
			vY=0;
		}
		
		
	}
	
	public void trataColisao(Objeto colisor){
		//vai pros olds
		x=oldX;
		y=oldY;
		colisor.x=colisor.oldX;
		colisor.y=colisor.oldY;
		//trata a colisao
		//isGrounded=false;
		//vX=vX/2;
		if(colisor.useGravity){
			//colisor.isGrounded=false;
			//colisor.vX=colisor.vX/2;
		}
		//vX*=-1; vX-=vX*0.1f;
		//colisor.vX*=-1; colisor.vX-=colisor.vX*0.1f;
		
	}
	
	public boolean colide(Objeto objeto){
		if(x+largura>=objeto.x && x<=objeto.x+objeto.largura){
            if(y+altura >= objeto.y && y<=objeto.y+objeto.altura){
                return true;
            }
        }
        
        return false;
	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(cor);
		if(useGravity){
			dbg.drawOval((int)x, (int)y, largura,altura);
//			if(!isEffect){
//				dbg.drawString(vX+":"+vY, (int)x, (int)y);
//			}
		}else{
			if(isEffect)
				dbg.drawOval((int)x, (int)y, largura,altura);
			else
				dbg.fillRect((int)x, (int)y, largura,altura);
		}
		//dbg.drawString(vX+":"+vY, (int)x, (int)y);
	}

}
