package Emotion;

import gameMain.GamePanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import telas.Screen;

public class TutorialEmocoes implements Screen {

	BufferedImage tutorial;
	String voltar;
	
	
	public TutorialEmocoes(){
	
		tutorial = AbreImagem("/imagens/emoções.png");
	voltar = "CLIQUE PARA VOLTAR";
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int codigo = e.getKeyCode();
		
		if(codigo == KeyEvent.VK_ENTER){
			GamePanel.screen = new Menu();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		if(e.getX()>0 &&e.getX()< GamePanel.PWIDTH && e.getY()>0 && e.getY()<GamePanel.PHEIGHT){
			GamePanel.screen = new Menu();
		}
		
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D dbg) {
		dbg.drawImage(tutorial, 0 , 0 ,GamePanel.PWIDTH, GamePanel.PHEIGHT, null);
		dbg.setFont(new Font("Letter Gothic Std", 25, 25));
		dbg.drawString(voltar, GamePanel.PWIDTH/2-140, GamePanel.PHEIGHT-25);
	}
	
	 public BufferedImage AbreImagem(String path){
			BufferedImage image = null;
			try {
				BufferedImage imgtmp = ImageIO.read( getClass().getResource(path) );
				image = new BufferedImage(imgtmp.getWidth(), imgtmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
				image.getGraphics().drawImage(imgtmp, 0,0,null);
				imgtmp = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return image;
		}

	
	
	
}
