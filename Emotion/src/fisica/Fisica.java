package fisica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import telas.Screen;

public class Fisica implements Screen{
	ControleFisica cF;
	GeradorParticula gP;
	
	ArrayList<GeradorParticula> listaGP;
	public Fisica() {
		// TODO Auto-generated constructor stub
		cF = new ControleFisica();
		
		listaGP = new ArrayList<GeradorParticula>();
		//cria o chao
		cF.AddObjeto(new Objeto(0,500, 
				0, 0, 1300,50, 
				Color.WHITE, false, false,-1));
		
		
		gP = new GeradorParticula(100, 200, //local donde as particulas vao sair
				cF, //controle de fisica
				500, 100, //tempo de spawn, total de particulas
				GeradorParticula.LOOP //tipo de gerador
				, 4000,true,false,//tempo de vida das particulas e direções left e down 
				new Objeto(0,0, //x,y ignorar 
						0.4f, 0.8f,//força maxima em X e em Y
						10, 10, Color.WHITE,//tamanho e cor, se a cor for nulla será aleatoria
						false, true,//effect, se colide ou nao, se usa gravidade ou nao 
						-1));//gpId
		listaGP.add(gP);
		
		gP = new GeradorParticula(800, 200, //local donde as particulas vao sair
				cF, //controle de fisica
				2000, 100, //tempo de spawn, total de particulas
				GeradorParticula.BURST, //tipo de gerador
				1500,true,true,//tempo de vida das particulas e direções left e down 
				new Objeto(0,0, //x,y ignorar 
						0.3f, 0.3f,//força maxima em X e em Y
						10, 10, Color.BLUE,//tamanho e cor, se a cor for nulla será aleatoria
						true, true,//effect, se colide ou nao, se usa gravidade ou nao 
						-1));//gpId
		listaGP.add(gP);
		
		
		gP = new GeradorParticula(500, 200, //local donde as particulas vao sair
				cF, //controle de fisica
				100, 450, //tempo de spawn, total de particulas
				GeradorParticula.SINGLE //tipo de gerador
				, 500,true,true,//tempo de vida das particulas e direções left e down 
				new Objeto(0,0, //x,y ignorar 
						0.2f, 0.4f,//força maxima em X e em Y
						2, 2, Color.YELLOW,//tamanho e cor, se a cor for nulla será aleatoria
						true, true,//effect, se colide ou nao, se usa gravidade ou nao 
						-1));//gpId
		listaGP.add(gP);
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Fisica: gPtotal"+gP.atual+" id:"+gP.id;
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		cF.update(time);
		
		//geradores de particulas
		for (GeradorParticula geradorParticula : listaGP) {
			geradorParticula.update(time, 0, 0);
			if(geradorParticula.tipo.equals("nada")){
				if(cF.checkSingleEnd(geradorParticula)){
					listaGP.remove(geradorParticula);
					break;
				}
			}
		}
		//gP.update(time, 0, 0);
	}
	
	@Override
	public void draw(Graphics2D dbg) {
		// TODO Auto-generated method stub
		cF.draw(dbg);
		//gP.draw(dbg, 0, 0);
		for (GeradorParticula geradorParticula : listaGP) {
			geradorParticula.draw(dbg, 0, 0);
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
		if(e.getButton()==1){
			cF.AddObjeto(new Objeto(e.getX(), e.getY(), 
					0.40f, -0.6f, 20,20, //velocidades e tamanho
					Color.WHITE, false, true,-1));//effect e gravidade
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

	

}
