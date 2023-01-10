package Emotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personagem {

	public int vida, dano;
	public float largura, altura;
	public int larguraA;
	public int escolha1;
	public int escolha2;
	public int escolha3;
	public Color cor;
	public float x, y;
	Barra vidaBar;
	BufferedImage image;
	BufferedImage imageRes;
	BufferedImage imageAtk;
	BufferedImage imageLAtk;
	BufferedImage icones;
	public int personagem;
	public int xI;
	boolean atkQuente;

	// animation
	int timeAnimation;
	// index da animação, indica qual frame do sprite sheet estamos desenhando
	int indexAnimation;
	int aux = 0;
	String animacao;
	int total;

	public Personagem(float x, float y, int vez, BufferedImage imageRes, BufferedImage imageAtk,
			BufferedImage imageLAtk) {
		this.x = x;
		this.y = y;
		xI = (int) this.x;
		vida = 100;
		largura = 79;// 136
		larguraA = 79;
		timeAnimation = 200;
		indexAnimation = 0;
		altura = 200;// 240
		personagem = vez;
		vidaBar = new Barra(x - 60, y - 190, 100 * 2, 20, vez, Color.GREEN);
		animacao = "idle";
		icones = AbreImagem("/imagens/icones.png");
		this.imageRes = imageRes;
		this.imageAtk = imageAtk;
		this.imageLAtk = imageLAtk;
		total = 5;
		image = this.imageRes;
		atkQuente = false;
		// TODO Auto-generated constructor stub
	}

	public int atk(int atk1, int atk2, int atk3) {
		escolha1 = atk1;
		escolha2 = atk2;
		escolha3 = atk3;
		dano = escolha1 + escolha2 + escolha3;
		if (dano < 0) {
			dano = dano * -1;
			atkQuente = false;

		} else {
			atkQuente = true;
		}

		return dano;
	}

	public void levadano(int danoLevado) {
		this.vida = this.vida - danoLevado;

	}

	public void fimdeatk() {
		escolha1 = 0;
		escolha2 = 0;
		escolha3 = 0;
		dano = 0;
	}

	public void update(float time, int cameraX, int cameraY) {

		vidaBar.update(time, vida * 2, 100 * 2);
		if (vida <= 0) {
			vida = 0;
		}
		if (animacao.equals("idle")) {
			if (personagem == 1) {
				x = 100;
			} else {
				x = 900;
			}
			timeAnimation = 200;
			total = 5;
			larguraA = 79;
			largura = 79;
			image = imageRes;

		} else if (animacao.equals("Atk")) {
			timeAnimation = 80;
			total = 16;
			if (personagem == 1) {
				x = 85;
				larguraA = 106;
				largura = 106;
			} else {
				larguraA = 105;
				largura = 105;
				x = 900;

			}
			if (atkQuente) {
				image = imageAtk;
			} else {
				image = imageAtk;
			}

		} else if (animacao.equals("LAtk")) {
			if (personagem == 2) {
				x = 870;

			}

			timeAnimation = 90;
			total = 14;
			larguraA = 113;
			largura = 113;

			image = imageLAtk;
		}

		aux += time;
		if (aux >= timeAnimation) {
			aux = 0;
			indexAnimation++;
			if (indexAnimation > total) {
				animacao = "idle";
				indexAnimation = 0;
			}

		}
	}

	public void draw(Graphics2D dbg, float num, Color cor) {
		dbg.setColor(cor);
		dbg.drawImage(icones, (int) xI - 64, 540, null);
		dbg.drawImage(image, (int) x, (int) y, (int) largura + (int) x, (int) altura + (int) y,
				(int) larguraA * indexAnimation, 0, (int) larguraA * (indexAnimation + 1), 200, null);

		vidaBar.draw(dbg, 0, 1);
		dbg.setColor(new Color(0, 0, 0, 200));
		dbg.setFont(new Font("Letter Gothic Std", 15, 15));
		dbg.drawString("VIDA:" + vida + "%", x + 2, y - 173);

	}

	public boolean colideMouse(int mousex, int mousey) {
		if (mousex < (x - 80) + 200 && mousex > x - 80) {
			if (mousey < (540) + 30 && mousey > 540) {
				return true;
			}
		}
		return false;
	}

	public BufferedImage AbreImagem(String path) {
		BufferedImage image = null;
		try {
			BufferedImage imgtmp = ImageIO.read(getClass().getResource(path));
			image = new BufferedImage(imgtmp.getWidth(), imgtmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(imgtmp, 0, 0, null);
			imgtmp = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return image;
	}

}
