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
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import stuff.Button;
import stuff.ButtonManager;
import telas.Screen;

public class Menu implements Screen {

	ButtonManager buttons;
	BufferedImage backGround;
	int by;
	
	ArrayList<MensagensMenu> mensagens;
	public int tempo , aux;
	Random aleatorio = new Random();
	
	int textoAleatorio;
	int tamanhoAleatorio;
	
	String texto;
	//Personagem per;
	
	public static Clip musica;
	
	

	public Menu() {
		 String caminhoAudio =
		 "C:/Users/Tony/Documents/novoWorkspace/Emotion com online/Emotion com online/Emotion/src/musicas";
		 musica = AbreAudio(caminhoAudio+"/heartbeat.wav");
		 
		 musica.loop(1000);
		
		
		buttons = new ButtonManager();
		mensagens = new ArrayList<MensagensMenu>();
		by = 298;
		
		aux = 0;
		tempo = 1500;
		buttons.add(new Button(450, 200, 200, 50, 1, "", Color.BLUE, true));
		buttons.add(new Button(450, 300, 200, 50, 4, "", Color.BLUE, true));
		buttons.add(new Button(450, 400, 200, 50, 2, "", Color.GREEN, true));
		buttons.add(new Button(450, 500, 200, 50, 3, "", Color.RED, true));
		backGround = AbreImagem("/imagens/principal.png");
		texto = "";
		//per = new Personagem(100, 210, 1,AbreImagem("/imagens/personagem_resp2d.png"));
	
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Menu";
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		aux += time;
		
		//per.update(time, 0, 0);
		
		
		if(aux >= tempo){
			aux=0;
			
			Color cor = new Color(173,173,173);
			textoAleatorio = aleatorio.nextInt(5);
			tamanhoAleatorio = aleatorio.nextInt(10)+29;
			if(textoAleatorio == 0){
				texto = "RAIVA";
				//cor = Color.RED;
			}else if(textoAleatorio == 1){
				texto = "FELICIDADE";
				//cor = Color.GREEN;
			}else if(textoAleatorio == 2){
				texto = "TRISTEZA";
				//cor = Color.BLUE;
			}else if(textoAleatorio == 3){
				texto = "AMOR";
				//cor = Color.MAGENTA;
			}else if(textoAleatorio == 4){
				texto = "MEDO";
				//cor = Color.YELLOW;
			}
			mensagens.add(new MensagensMenu(aleatorio.nextInt(150)+20, aleatorio.nextInt(300)+200, time, texto,cor,tamanhoAleatorio ));
			
			textoAleatorio = aleatorio.nextInt(5);
			tamanhoAleatorio = aleatorio.nextInt(10)+29;
			if(textoAleatorio == 0){
				texto = "RAIVA";
				//cor = Color.RED;
			}else if(textoAleatorio == 1){
				texto = "FELICIDADE";
				//cor = Color.GREEN;
			}else if(textoAleatorio == 2){
				texto = "TRISTEZA";
				//cor = Color.BLUE;
			}else if(textoAleatorio == 3){
				texto = "AMOR";
				//cor = Color.MAGENTA;
			}else if(textoAleatorio == 4){
				texto = "MEDO";
				//cor = Color.YELLOW;
			}
			mensagens.add(new MensagensMenu(aleatorio.nextInt(100)+700, aleatorio.nextInt(300)+200, time, texto,cor,tamanhoAleatorio));
			
		}
		
		for (int i = 0; i < mensagens.size(); i++) {
			mensagens.get(i).update(time, 0, 0);
		}
		
		
	

		switch (buttons.TASK) {

		case 1:
          
			GamePanel.screen = new SelecaoDeFase();
			buttons.TASK = 0;
			break;

		case 2:

			musica.stop();
			GamePanel.screen = new Tutorial();
			buttons.TASK = 0;

			break;

		case 3:

			System.exit(0);

			break;
			
		case 4:
		
			musica.stop();
			GamePanel.screen = new TutorialEmocoes();
			buttons.TASK = 0;

			break;

		default:

			break;

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int codigo = e.getKeyCode();
		if (by == 198 && codigo == KeyEvent.VK_DOWN) {
			by = 298;

		}else if (by == 298 && codigo == KeyEvent.VK_DOWN) {
			by = 398;

		} else if (by == 398 && codigo == KeyEvent.VK_DOWN) {
			by = 498;

		} else if (by == 498 && codigo == KeyEvent.VK_UP) {
			by = 398;

		} else if (by == 398 && codigo == KeyEvent.VK_UP) {
			by = 298;

		}else if (by == 298 && codigo == KeyEvent.VK_UP) {
			by = 198;

		}

		if (by == 198 && codigo == KeyEvent.VK_ENTER) {
			buttons.TASK = 1;
		}
		if (by == 398 && codigo == KeyEvent.VK_ENTER) {
			buttons.TASK = 2;
		}
		if (by == 498 && codigo == KeyEvent.VK_ENTER) {
			buttons.TASK = 3;
		}
		if (by == 298 && codigo == KeyEvent.VK_ENTER) {
			buttons.TASK = 4;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		buttons.pressed(e.getX(), e.getY());

	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean colideMouseBotao1(int x, int y) {
		if (x > 450 && x < 650 && y > 200 && y < 250) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colideMouseBotao2(int x, int y) {
		if (x > 450 && x < 650 && y > 400 && y < 450) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colideMouseBotao3(int x, int y) {
		if (x > 450 && x < 650 && y > 500 && y < 550) {
			return true;
		} else {
			return false;
		}
	}
	public boolean colideMouseBotao4(int x, int y) {
		if (x > 450 && x < 650 && y > 300 && y < 350) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		if (colideMouseBotao1(x, y)) {
			by = 198;
		}
		if (colideMouseBotao2(x, y)) {
			by = 398;
		}
		if (colideMouseBotao3(x, y)) {
			by = 498;
		}
		if (colideMouseBotao4(x, y)) {
			by = 298;
		}
		

	}

	@Override
	public void draw(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.drawImage(backGround, 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT+220, null);
		dbg.setColor(new Color(250, 250, 250));
		dbg.drawRect(448, by, 202, 52);

		/*
		 * buttons.draw(dbg, 0, 0); dbg.setColor(Color.BLUE); dbg.fillRect(450,
		 * 300, 200, 50); dbg.setColor(Color.GREEN); dbg.fillRect(450, 400, 200,
		 * 50); dbg.setColor(Color.RED); dbg.fillRect(450, 500, 200, 50);
		 */

		dbg.setFont(new Font("Letter Gothic Std", 30, 30));
		dbg.setColor(new Color(250, 250, 250));
		dbg.drawString("INICIAR", 490, 235);
		dbg.drawString("EMOÇÕES", 490, 335);
		// dbg.setColor(Color.GRAY);
		dbg.drawString("TUTORIAL", 485, 435);
		// dbg.setColor(Color.BLACK);
		dbg.drawString("SAIR", 515, 535);
		dbg.setFont(new Font("Letter Gothic Std", 100, 100));
		dbg.drawString("E M O T I O N", 140, 130);
		for (int i = 0; i < mensagens.size(); i++) {
			mensagens.get(i).draw(dbg, 0, 0);
		}
		//per.draw(dbg,0,null);

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
