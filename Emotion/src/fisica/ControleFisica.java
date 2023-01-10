package fisica;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ControleFisica {
	public static float gravidade = 0.00098f;
	public static float resistenciaAr = 1.3f;
	public static float elastico = 0.01f;
	
	public ArrayList<Objeto> objetos;
	
	public ControleFisica() {
		// TODO Auto-generated constructor stub
		objetos = new ArrayList<Objeto>();
	}
	
	public void AddObjeto(Objeto objeto){
		objetos.add(objeto);
	}
	
	public void checkLife(GeradorParticula gP){
		for (Objeto objeto : objetos) {
			if(objeto.isEffect && gP.id==objeto.gPid){
				if(!objeto.isAlive){
					objetos.remove(objeto);
					gP.atual--;
					break;
				}
			}
		}
	}
	
	public boolean checkSingleEnd(GeradorParticula gP){
		if(gP.atual!=0){
			return false;
		}
		System.out.println("removed");
		return true;
	}
	
	
	public void updateEffect(float time, GeradorParticula gP){
		for (Objeto objeto : objetos) {
			//se for effect e se for o meu pai
			if(objeto.isEffect && gP.id==objeto.gPid){
				objeto.update(time, 0, 0);//faz o update
				if(objeto.lifeTime>=gP.vida){
					objeto.isAlive=false;
				}
			}
		}
	}
	
	public void update(float time){
		for (Objeto objeto : objetos) {
			if(!objeto.isEffect){
				objeto.update(time, 0, 0);//faz o update e faz eles colidirem
				for (Objeto colisor : objetos) {
					if(objeto!=colisor & objeto.useGravity & !colisor.isEffect){
						//colidiu tratar colisao
						if(objeto.colide(colisor)){
							objeto.trataColisao(colisor);
						}
					}
				}
			}
			
		}
	}
	
	public void draw(Graphics2D dbg){
		for (int i=0;i<objetos.size();i++) {
			Objeto objeto = objetos.get(i);
			objeto.draw(dbg, 0, 0);
			
		}
	}
	
	
}
