package fisica;

import java.awt.Graphics2D;

import minhasClasses.Componente;

public class GeradorParticula extends Componente{
	public static int totalId=0;
	public int id;
	public static String LOOP = "loop";
	public static String BURST = "burst";
	public static String SINGLE = "single";
	
	public String tipo;//o tipo de gerador
	ControleFisica cF;//o controle de fisica
	public boolean left;//se vai para todas as direções
	public boolean down;//se vai para todas as direções
	public int vida;//tempo de vida das particulas
	public int timer;//o para criar cada particula
	int aux;//auxiliar de tempo
	public int total;//total de particulas permitidas
	int atual;//numero atual de particulas
	Objeto referencial;//referencia para objeto a ser copiado
	
	public GeradorParticula(int x, int y,ControleFisica cF
			,int timer, int total, String tipo
			,int vida,boolean left, boolean down,Objeto referencial) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.cF=cF;
		this.tipo=tipo;
		this.timer=timer;
		this.total=total;
		this.left=left;
		this.down=down;
		this.vida=vida;
		this.referencial=referencial;
		id = totalId;
		totalId++;
		referencial.gPid=id;
		referencial.x=x;
		referencial.y=y;
		
	}
	
	@Override
	public void update(float time, int cameraX, int cameraY) {
		// TODO Auto-generated method stub
		cF.updateEffect(time, this);
		cF.checkLife(this);
		
		switch (tipo) {
		case "loop":
			//se for loop somar o tempo e começar a criar as particulas
			aux+=time;
			if(aux>=timer){
				aux=0;
				if(atual<total){
					atual++;
					cF.AddObjeto(referencial.copyEffect(left,down));
				}
				
			}
			break;
		case "burst":
			//se for burst, fica dando explosões
			aux+=time;
			if(aux>=timer){
				aux=0;
				for (int i = 0; i <total; i++) {
					cF.AddObjeto(referencial.copyEffect(left,down));
				}
				atual+=total;
			}
			break;
		case "single":
			//executa uma única vez, uma única explosão
			aux+=time;
			if(aux>=timer){
				aux=0;
				for (int i = 0; i <total; i++) {
					cF.AddObjeto(referencial.copyEffect(left,down));
				}
				atual+=total;
				tipo="nada";
			}
			
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D dbg, float xMundo, float yMundo) {
		// TODO Auto-generated method stub
		cF.draw(dbg);
		//dbg.setColor(referencial.cor);
		//dbg.drawOval((int)x,(int) y, 20, 20);
		
	}

}
