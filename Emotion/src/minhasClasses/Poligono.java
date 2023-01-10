package minhasClasses;

import gameMain.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Poligono extends Componente{
	public Polygon poly;
	public Color cor;
    public float veloX;
    public float veloY;
    public int size;
    public float oldX[];
    public float oldY[];
    Rectangle coli;
	public Poligono(float x, float y,Polygon poly,int size, float veloX, float veloY, Color cor) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.poly=poly;
		this.size=size;
		this.veloX=veloX;
        this.veloY=veloY;
        this.cor=cor;
        oldX = new float[poly.npoints];
        oldY = new float[poly.npoints];
        for (int i = 0; i < oldX.length; i++) {
			oldX[i]=(float)poly.xpoints[i]/100;
			oldY[i]=(float)poly.ypoints[i]/100;
			//System.out.println(oldX[i]+" "+oldY[i]);
		}
        coli = poly.getBounds();
        coli.width=size;
        coli.height=size;
        //System.out.println(coli.getWidth()+" "+coli.getHeight());
	}

	@Override
	public void update(float time, int cameraX, int cameraY) {
		// TODO Auto-generated method stub
		x+=veloX*time;
		y+=veloY*time;
		//atualiza o poligono
      //  poly.translate((int)(oldX-x),(int) (oldY-y));
		 //colisões
        //parade de baixo
        if(y+coli.height>=GamePanel.PHEIGHT){
        	veloY*=-1;
        	y=GamePanel.PHEIGHT-coli.height;
        }
        //parede da direita
        if(x+coli.width>=GamePanel.PWIDTH){
        	veloX*=-1;
        	x=GamePanel.PWIDTH-coli.width;
        }
        //parede de cima
        if(y<=0){
        	veloY*=-1;
        	y=0;
        }
        //parede da esquerda
        if(x<=0){
        	veloX*=-1;
        	x=0;
        }
        
        //calcula os pontos em pixeis, plota eles num quadrado do tamanho size
        for (int i = 0; i < oldX.length; i++) {
			poly.xpoints[i]=(int)(x+oldX[i]*size);
			poly.ypoints[i]=(int)(y+oldY[i]*size);
		}
        
	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(cor);
		dbg.fillPolygon(poly.xpoints,poly.ypoints,poly.npoints);
		//dbg.drawRect((int)x, (int)y, coli.width, coli.height);
	}

}
