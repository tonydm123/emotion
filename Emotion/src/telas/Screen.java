package telas;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyEvent;

public interface Screen {
        public Color corTexto = Color.WHITE;
	public String getName();
	public void update(long time);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e );
	public void onMousePressed(MouseEvent e);
	public void onMouseReleased(MouseEvent e);
	public void onMouseMove(int x, int y);
	public void draw(Graphics2D dbg);
}
