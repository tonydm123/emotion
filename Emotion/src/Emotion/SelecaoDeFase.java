package Emotion;

import gameMain.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import stuff.Button;
import stuff.ButtonManager;
import telas.Screen;

public class SelecaoDeFase implements Screen {

	public static int fase;
	public ButtonManager button;
	int bx;
	boolean ready;
	BufferedImage[] fundos;

	// musica
	Clip musica;
	

	int xM, yM;

	// controle de comunicação
	// mensagem do servidor
	String mensagem;
	boolean conectado;
	public int num;
	public int fundoIndex;

	String[] dividido;

	public SelecaoDeFase() {
		// TODO Auto-generated constructor stub
		musica = Menu.musica;
		musica.loop(1000);
		

		ready = false;
		bx = 58;
		fundoIndex = 0;
		fundos = new BufferedImage[4];
		fundos[0] = AbreImagem("/imagens/f.png");
		fundos[1] = AbreImagem("/imagens/n.png");
		fundos[2] = AbreImagem("/imagens/q.png");
		fundos[3] = AbreImagem("/imagens/espera.png");

		button = new ButtonManager();
		button.add(new Button(0, 0, GamePanel.PWIDTH / 3, GamePanel.PHEIGHT, 1, " ", Color.RED, true));

		button.add(new Button(GamePanel.PWIDTH / 3, 0, 360, GamePanel.PHEIGHT, 2, " ", Color.YELLOW, true));

		button.add(new Button(GamePanel.PWIDTH / 3 + GamePanel.PWIDTH / 3, 0, 360, GamePanel.PHEIGHT, 3, " ",
				Color.GREEN, true));

		this.fase = button.TASK;
		num = -1;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean colideMouseBotao1(int x, int y) {
		if (x > 60 && x < 310 && y > 150 && y < 400) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colideMouseBotao2(int x, int y) {
		if (x > 410 && x < 660 && y > 150 && y < 400) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colideMouseBotao3(int x, int y) {
		if (x > 760 && x < 1010 && y > 150 && y < 400) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		// System.out.println(xM +" "+ yM);

		if (!conectado) {
			GamePanel.cliente.sendMessage("estouAtivo");
		}
		// mandando mensagem constante de conecxão
		mensagem = GamePanel.cliente.getMessage();
		if (mensagem == null) {
			// se a mensagem for nula não conectou com mais ninguem
			// System.out.println("Esperando amiguinho conectar.");
		} else {
			// se entrar aqui ele recebeu alguma mensagem ou seja
			// conectado com sucesso. hora de fazer o jogo funcionar
			dividido = mensagem.split(";");
			if (!conectado & dividido[1].equals("estouAtivo")) {
				GamePanel.cliente.sendMessage("estouAtivo");
				conectado = true;
			}
			// recebe o numero do inimigo
			num = Integer.parseInt(dividido[0]);
		}

		if (conectado) {
			switch (dividido[1]) {
			case "fase":
				// se for dano verificar quem ta levando dano e aplicar o dano
				// apos receber uma mensagem de dano, eu sei que o turno
				// do meu oponente acabou. logo agora é o meu
				if (dividido[2].equals("1")) {
					fase = 1;
					musica.stop();
					musica.close();
					GamePanel.screen = new Battle();
				}
				if (dividido[2].equals("2")) {
					fase = 2;
					musica.stop();
					musica.close();
					GamePanel.screen = new Battle();
				}
				if (dividido[2].equals("3")) {
					fase = 3;
					musica.stop();
					musica.close();
					GamePanel.screen = new Battle();
				}
				break;

			default:
				break;
			}
		}

		if (ready) {
			if (num == 1 && conectado) {
				GamePanel.cliente.sendMessage("fase;" + fase);
				musica.stop();
				musica.close();
				GamePanel.screen = new Battle();
			} else {
				fase = 0;
				ready = false;

			}

		}
		if (bx == 58) {
			fundoIndex = 0;
		}
		if (bx == 408) {
			fundoIndex = 1;
		}
		if (bx == 758) {
			fundoIndex = 2;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int codigo = e.getKeyCode();

		if (bx == 58 && codigo == KeyEvent.VK_RIGHT) {
			bx = 408;

		} else if (bx == 408 && codigo == KeyEvent.VK_RIGHT) {
			bx = 758;

		} else if (bx == 758 && codigo == KeyEvent.VK_LEFT) {
			bx = 408;

		} else if (bx == 408 && codigo == KeyEvent.VK_LEFT) {
			bx = 58;

		}

		if (bx == 58 && codigo == KeyEvent.VK_ENTER) {
			ready = true;
			fase = 1;
		}
		if (bx == 408 && codigo == KeyEvent.VK_ENTER) {
			ready = true;
			fase = 2;
		}
		if (bx == 758 && codigo == KeyEvent.VK_ENTER) {
			ready = true;
			fase = 3;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		button.pressed(e.getX(), e.getY());
		fase = button.TASK;
		if (fase != 0)
			ready = true;
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub

		xM = x;
		yM = y;

		if (colideMouseBotao1(x, y)) {
			bx = 58;
		}
		if (colideMouseBotao2(x, y)) {
			bx = 408;
		}
		if (colideMouseBotao3(x, y)) {
			bx = 758;
		}

	}

	@Override
	public void draw(Graphics2D dbg) {
		// TODO Auto-generated method stub

		if (num == 0) {
			dbg.setColor(Color.WHITE);
			dbg.drawImage(fundos[3], 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);
			// dbg.setColor(Color.BLACK);
			dbg.setFont(new Font("Letter Gothic Std", 40, 40));
			dbg.drawString("ESPERE O JOGADOR ESCOLHER A FASE ...", 130, GamePanel.PHEIGHT / 2);
		} else {
			dbg.drawImage(fundos[fundoIndex], 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);

			// dbg.setColor(new Color(250, 250, 250));
			// dbg.drawRect(bx, 148, 252, 252);

			dbg.setFont(new Font("Arial", 30, 30));
			// button.draw(dbg, 0, 0);
			dbg.setColor(new Color(250, 250, 250));
			dbg.setFont(new Font("Letter Gothic Std", 60, 60));
			dbg.drawString("ESCOLHA  A  FASE", 265, 70);
		}
	}

	public Clip AbreAudio(String nome) {
		Clip clip = null;
		try {
			// System.out.println(this.getClass().getClassLoader().getResource("teste.wav"));
			URL url = new URL("file:/" + nome);
			AudioInputStream audioIn;
			audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (clip != null) {
			return clip;
		} else {
			return null;
		}
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
