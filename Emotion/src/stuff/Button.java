 package stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class Button {
	public float x, y;
	int width, height;
	int task;
	public String text;
	public boolean pressed;
	public Color color;
	public boolean passed;
	public BufferedImage image;
	public Polygon poly;
	
	public Button(float x, float y, int width, int height,int task, String text,Color color, boolean passed ) {
		// TODO Auto-generated constructor stub
		this.passed = passed;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.task = task;
		this.text = text;
		pressed = false;
		this.color = color;
		poly=null;
	}
	
	public Button(float x, float y, int width, int height,int task, String text,Color color, boolean passed , BufferedImage image) {
		// TODO Auto-generated constructor stub
		this.passed = passed;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.task = task;
		this.text = text;
		pressed = false;
		this.color = color;
		this.image = image;
		poly=null;
	}
	
	
	
	public void draw(Graphics2D dbg,float X, float Y){
		x+=X;
		y+=Y;
		
		if(image!=null){
			dbg.drawImage(image,(int)this.x,(int)this.y,(int)this.width,(int)this.height,null);
		}else{
		
		dbg.setColor(color);
		dbg.fillRoundRect((int)x, (int)y, width, height,20,20);
		
		}
		dbg.setColor(Color.BLACK);
		dbg.drawString(text, x+5, 5+y+height/2);
		
	}
	
	public boolean press(float x2, float y2){
		if(x2>x && x2<(x+width)){
			if(y2>y && y2<(y+height)){
				return true;
			}
		}
		return false;
	}
}
