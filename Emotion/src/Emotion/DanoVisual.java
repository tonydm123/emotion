package Emotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import minhasClasses.Componente;

public class DanoVisual extends Componente {

	public float tempo;
	public String texto;
	public float aux;
	public boolean isVivo;
	public float yMenos = 0.0001f;
	public int alpha;
	int auxAlpha;

	public DanoVisual(float x, float y, float tempo, String texto) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.tempo = tempo;
		this.texto = texto;
		isVivo = true;
		alpha = 250;

	}

	@Override
	public void update(float time, int cameraX, int cameraY) {
		// TODO Auto-generated method stub
		// temporizador
		if (y > 40) {
			y -= yMenos * time;
		} else {
			y = 40;
		}

		aux += time;
		if (aux >= tempo) {
			isVivo = false;
		}
		auxAlpha += time;
		if (auxAlpha >= 10) {
			alpha --;
			auxAlpha = 0;
			if (alpha < 0) {
				alpha = 0;
			}
		}

	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(new Color(255, 0, 0, alpha));
		dbg.setFont(new Font("Letter Gothic Std", 40, 40));
		dbg.drawString(texto, x, y);

	}

}
