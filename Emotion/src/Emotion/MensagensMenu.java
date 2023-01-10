package Emotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import minhasClasses.Componente;

public class MensagensMenu extends Componente {

	
	public float tempo;
	public String texto;
	public float aux;
	public boolean isVivo;
	public int alpha;
	public boolean volta;
    public Color cor;
    public int tamanho;
	
	public MensagensMenu(float x, float y, float tempo, String texto, Color cor, int tamanho) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.tempo = tempo;
		this.texto = texto;
		isVivo = true;
		alpha = 0;
		volta = false;
		this.cor = cor;
		this.tamanho = tamanho;

	}

	@Override
	public void update(float time, int cameraX, int cameraY) {
		// TODO Auto-generated method stub
		// temporizador

		//		aux += time;
		//		if (aux >= tempo) {
		//			isVivo = false;
		//		}
		
		if(volta){
			alpha -=10;
			if (alpha <= 0) {
				alpha = 0;
				isVivo = false;
			}
		}else{
			alpha +=10;

			if (alpha >= 255) {
				alpha = 255;
				volta = true;
			}
		}
		
	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		Random aleatorio = new Random();
		dbg.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), alpha));
		dbg.setFont(new Font("Letter Gothic Std", (aleatorio.nextInt(1))+tamanho, (aleatorio.nextInt(1))+tamanho));
		dbg.drawString(texto, x, y);

	}

}
