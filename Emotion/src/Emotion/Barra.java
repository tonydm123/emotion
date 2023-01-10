package Emotion;

import java.awt.Color;
import java.awt.Graphics2D;

import minhasClasses.Componente;

public class Barra extends Componente {

	public int altura, largura;
	public Color cor;
	public int larguraAtual;
	public int vez;
	public int vida;
	public int vidaMax;
	public float tempo;

	public Barra(float x, float y, int largura, int altura, int vez, Color cor) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.vez = vez;
		this.largura = largura;
		this.altura = altura;
		this.cor = cor;
		larguraAtual = largura - 10;
		tempo = 0;

	}

	@Override
	public void update(float time, int atual, int max) {
		// TODO Auto-generated method stub
		vida = atual;
		vidaMax = max;
		tempo = time;
		if (vida <= 0) {
			vida = 0;
		}

	}

	public void updateTempo(float time, int atual, int max) {
		// TODO Auto-generated method stub
		larguraAtual = (largura - 10)*atual/max;

	}

	public void drawTempo(Graphics2D dbg, float xMundo, float yMundo) {
		dbg.setColor(Color.DARK_GRAY);
		dbg.fillRect((int)x,(int) y, largura, altura);
		dbg.setColor(cor);
		dbg.fillRect((int)x+5, (int)y+5, larguraAtual, altura-10);
		
	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(new Color(250,0,0,110));
		dbg.fillRect((int) x - 5, (int) y - 5, largura + 10, altura + 10);
		dbg.setColor(cor);

		if (vez == 2) {
			dbg.fillRect((int) x + (largura - vida), (int) y, this.vida, altura);
		} else {
			dbg.fillRect((int) x, (int) y, this.vida, altura);
		}

	}

}
