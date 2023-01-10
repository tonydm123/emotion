/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author EduTaToo
 */
public class Componente extends  Button{
    public String nome;
    public String msg;
    
    public Componente(float x, float y, int width, int height, int task,
            String text, Color color, boolean passed,String nome, String msg) {
        super(x, y, width, height, task, text, color, passed, null);
        this.nome = nome;
        this.msg = msg;
    }

    @Override
    public void draw(Graphics2D dbg, float X, float Y) {
        x+=X;
	y+=Y;
        dbg.setColor(color);
	dbg.drawString(nome+": ", x+5, 5+y+height/2);
	dbg.setColor(Color.WHITE);
	dbg.drawString(text, x+75, 5+y+height/2);
        dbg.setColor(Color.YELLOW);
        dbg.drawRect((int)x, (int)y, width, height);
    }
    
}
