	package Emotion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameMain.GamePanel;
import stuff.Button;
import stuff.ButtonManager;
import telas.Screen;

public class Win implements Screen {

	public boolean vencedor1;
	ButtonManager button;
	String cliente;
	BufferedImage[] image;

	

	public Win(int vencedor, String cliente) {
		this.cliente = cliente;
		
		image = new BufferedImage[3];
		image[0] = AbreImagem("/imagens/gameover_frio.png");
		image[1] = AbreImagem("/imagens/gameover_neutro.png");
		image[2] = AbreImagem("/imagens/gameover_quente.png");

		if (vencedor == 1) {
			vencedor1 = true;
		} else {
			vencedor1 = false;
		}
		button = new ButtonManager();

		button.add(new Button(460, 500, 200, 50, 1, " ", Color.WHITE, true, null));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;//

	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		if (button.TASK == 1) {
			GamePanel.screen = new SelecaoDeFase();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		button.pressed(e.getX(), e.getY());
	}

	@Override
	public void onMouseReleased(MouseEvent e) {

	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D dbg) {
		dbg.setColor(new Color(40,0,90));
		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		if(SelecaoDeFase.fase == 1){
			dbg.drawImage(image[0],0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT,null);
		}else if (SelecaoDeFase.fase == 2){
			dbg.drawImage(image[1],0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT,null);
		}else{
			dbg.drawImage(image[2],0,0,GamePanel.PWIDTH, GamePanel.PHEIGHT,null);
		}
		
		
		if (vencedor1 && cliente.equals("0")) {
			
			dbg.setColor(Color.WHITE);
			dbg.setFont(new Font("Letter Gothic Std", 110, 110));
			dbg.drawString("VOCÊ VENCEU!", 165, 330);
		} else if (vencedor1 && cliente.equals("1")) {
			
			dbg.setColor(Color.WHITE);
			dbg.setFont(new Font("Letter Gothic Std", 110, 110));
			dbg.drawString("VOCÊ PERDEU!", 165, 330);
			
		} else if (!vencedor1 && cliente.equals("0")) {
			
			dbg.setColor(Color.WHITE);
			dbg.setFont(new Font("Letter Gothic Std", 110, 110));
			dbg.drawString("VOCÊ PERDEU!", 165, 330);
		} else {
			
			dbg.setColor(Color.WHITE);
			dbg.setFont(new Font("Letter Gothic Std", 110, 110));
			dbg.drawString("VOCÊ VENCEU!", 165, 330);
		}
		dbg.setFont(new Font("Letter Gothic Std", 20, 20));
		//button.draw(dbg, 0, 0);
		
		dbg.setColor(Color.WHITE);
		dbg.drawRect(460, 500, 200, 50);
		dbg.drawString("RECOMEÇAR", 510, 530);

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
