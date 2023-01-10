package gameMain;

import telas.Screen;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.*;
import java.io.*;
import java.net.UnknownHostException;

import java.util.Random;

import javax.swing.*;



public class GamePanel extends Canvas implements Runnable {
	public static final int PWIDTH = 1080;
	public static final int PHEIGHT = 600;
	private Thread animator;
	public boolean running = false;
	private boolean gameOver = false;

	int FPS, SFPS;
	int fpscount;

	public static Random rnd = new Random();

	BufferedImage imagemcharsets;
	boolean STOP;
	public int mousex, mousey;

	public static Screen screen;
	
	public static Conexao.Cliente cliente;

	public GamePanel() {
		
		try {
			cliente = new Conexao.Cliente("localhost", 9090);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH - 9, PHEIGHT - 9));
		// create game components
		setFocusable(true);
		requestFocus(); // JPanel now receives key events
		setSize(new Dimension(PWIDTH, PHEIGHT));
		// Adiciona um Key Listner
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				screen.keyPressed(e);
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE) {
					stopGame();

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				screen.keyReleased(e);
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mousex = e.getX();
				mousey = e.getY();
				screen.onMouseMove(mousex, mousey);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				screen.onMousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				screen.onMouseReleased(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		mousex = mousey = 0;
		screen = new Emotion.Menu();

	} // end of GamePanel()

	public void startGame()
	// initialise and start the thread
	{
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} // end of startGame()

	public void stopGame() {
		running = false;
	}

	public void run()
	/* Repeatedly update, render, sleep */
	{
		running = true;

		long DifTime, TempoAnterior;
		int segundo = 0;
		DifTime = 0;
		TempoAnterior = System.currentTimeMillis();

		this.createBufferStrategy(2);
		BufferStrategy strategy = this.getBufferStrategy();
		while (running) {
			gameUpdate(DifTime); // game state is updated
			Graphics g = strategy.getDrawGraphics();
			gameRender((Graphics2D) g); // render to a buffer
			strategy.show();
			try {
				Thread.sleep(1); // sleep a bit
			} catch (InterruptedException ex) {
			}

			DifTime = System.currentTimeMillis() - TempoAnterior;
			TempoAnterior = System.currentTimeMillis();

			if (segundo != ((int) (TempoAnterior / 1000))) {
				FPS = SFPS;
				SFPS = 1;
				segundo = ((int) (TempoAnterior / 1000));
			} else {
				SFPS++;
			}

		}
		System.exit(0); // so enclosing JFrame/JApplet exits
	} // end of run()

	int timerfps = 0;

	private void gameUpdate(long DiffTime) {
		screen.update(DiffTime);
	}

	private void gameRender(Graphics2D dbg) {
		// draw the current frame to an image buffer
		// clear the background
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, PWIDTH + 100, PHEIGHT + 100);
		dbg.setColor(Color.WHITE);
		
		// dbg.drawRect(0, 0, PWIDTH,PHEIGHT);
		screen.draw(dbg);
		dbg.setFont(new Font("Arial", 10, 10));
		dbg.setColor(Color.WHITE);
		//dbg.drawString(screen.getName() + " - FPS: " + FPS, 10, 10);
	}

	public static void main(String args[]) {
		GamePanel ttPanel = new GamePanel();

		// create a JFrame to hold the timer test JPanel
		JFrame app = new JFrame("EMOTION");
		app.getContentPane().add(ttPanel, BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocation(110 , 55);
		app.setFocusable(true);
		app.pack();
		app.setResizable(false);
		app.setVisible(true);
		ttPanel.startGame();
	} // end of main()*/
}
