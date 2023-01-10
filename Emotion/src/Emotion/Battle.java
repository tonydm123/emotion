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

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fisica.ControleFisica;
import fisica.GeradorParticula;
import fisica.Objeto;
import stuff.Button;
import stuff.ButtonManager;
import telas.Screen;

public class Battle implements Screen {

	Personagem personagem1;
	Personagem personagem2;
	Personagem daVez;
	public int vez;
	Color corP1;
	Color corP2;
	public int atk1, atk2, atk3;
	Barra tempo;
	int xDasBolas;
	ButtonManager buttons;
	Emocoes emocao;
	Color cor, outra;
	BufferedImage image;
	int tempoDano;

	String fase;
	ArrayList<DanoVisual> danosVisuais;
	Color corAtk;

	public static Clip musica1;
	public static Clip musica2;
	public static Clip musica3;

	int tempocro;

	// input do mouse
	// numero maluko
	int n = 0;
	// mouse
	int mouseX, mouseY;
	int bx = xDasBolas - 80, by = 500;
	// tempo
	float tempoqPassou;

	boolean meuturno;
	String mensagem;
	public int num;
	String[] dividido;
	boolean foi;

	// Fisica

	ControleFisica cF;
	GeradorParticula gP;
	ArrayList<GeradorParticula> listaGP;

	public Battle() {
		// TODO Auto-generated constructor stub

		// inicia fisica
		corAtk = Color.WHITE;
		tempoDano = 3000;

		String caminhoAudio = "C:/Users/Tony/Documents/novoWorkspace/Emotion com online/Emotion com online/Emotion/src/musicas";
		musica1 = AbreAudio(caminhoAudio + "/frio.wav");
		musica2 = AbreAudio(caminhoAudio + "/neutro.wav");
		musica3 = AbreAudio(caminhoAudio + "/quente.wav");

		cF = new ControleFisica();
		listaGP = new ArrayList<GeradorParticula>();

		num = -1;
		if (SelecaoDeFase.fase == 1) {
			fase = "/imagens/frio_v8.png";
			musica1.loop(3000000);
		} else if (SelecaoDeFase.fase == 2) {
			fase = "/imagens/neutro_v3.png";
			musica2.loop(3000000);
		} else if (SelecaoDeFase.fase == 3) {
			fase = "/imagens/quente_v3.png";
			musica3.loop(3000000);
		}

		image = AbreImagem(fase);

		danosVisuais = new ArrayList<DanoVisual>();

		n = 0;
		emocao = new Emocoes();

		tempo = new Barra(380, 540, 310, 25, 0, Color.BLACK);
		personagem1 = new Personagem(100, 210, 1, AbreImagem("/imagens/rd.png"), AbreImagem("/imagens/AtkD.png"),
				AbreImagem("/imagens/DefD.png"));
		personagem2 = new Personagem(900, 210, 2, AbreImagem("/imagens/re.png"), AbreImagem("/imagens/AtkE.png"),
				AbreImagem("/imagens/DefE.png"));

		daVez = personagem1;
		xDasBolas = (int) daVez.xI + 15;
		bx = xDasBolas - 80;

		vez = 1;
		cor = new Color(0, 0, 0, 0);
		outra = Color.WHITE;
		buttons = new ButtonManager();

		for (int i = 0; i < 15; i++) {
			buttons.add(new Button(bx, by, 20, 20, i + 1, "", cor, true));
			by -= 40;
			if ((i + 1) % 3 == 0) {
				bx += 40;
				by = 500;
			}
		}

		GamePanel.cliente.sendMessage("player");

		foi = false;
		meuturno = false;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		emocao.Fase(SelecaoDeFase.fase);

		// System.out.println(vez);]
		// mandando mensagem constante de conecxão
		mensagem = GamePanel.cliente.getMessage();
		if (mensagem == null) {
			// se a mensagem for nula não conectou com mais ninguem
			// System.out.println("Esperando amiguinho conectar.");
		} else {
			// se entrar aqui ele recebeu alguma mensagem ou seja
			// conectado com sucesso. hora de fazer o jogo funcionar
			dividido = mensagem.split(";");
			// recebe o numero do inimigo
			num = Integer.parseInt(dividido[0]);
		}

		if (num == 1 && !foi) {
			foi = true;
			meuturno = true;
		}

		// System.out.println(num);

		for (int i = 0; i < danosVisuais.size(); i++) {
			danosVisuais.get(i).update(time, 0, 0);
			if (!danosVisuais.get(i).isVivo) {
				danosVisuais.remove(i);
				break;
			}
		}

		switch (buttons.TASK) {
		case 1:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[0];
					corAtk = Color.YELLOW;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[0];
					corAtk = Color.YELLOW;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[0];
					corAtk = Color.YELLOW;
				}

			}
			buttons.TASK = 0;

			break;

		case 2:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[1];
					corAtk = Color.YELLOW;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[1];
					corAtk = Color.YELLOW;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[1];
					corAtk = Color.YELLOW;
				}
			}
			buttons.TASK = 0;

			break;

		case 3:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[2];
					corAtk = Color.YELLOW;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[2];
					corAtk = Color.YELLOW;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[2];
					corAtk = Color.YELLOW;
				}
			}
			buttons.TASK = 0;

			break;

		case 4:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[3];
					corAtk = Color.BLUE;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[3];
					corAtk = Color.BLUE;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[3];
					corAtk = Color.BLUE;
				}
			}
			buttons.TASK = 0;

			break;

		case 5:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[4];
					corAtk = Color.BLUE;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[4];
					corAtk = Color.BLUE;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[4];
					corAtk = Color.BLUE;
				}
			}
			buttons.TASK = 0;

			break;

		case 6:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[5];
					corAtk = Color.BLUE;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[5];
					corAtk = Color.BLUE;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[5];
					corAtk = Color.BLUE;
				}
			}
			buttons.TASK = 0;

			break;

		case 7:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[6];
					corAtk = Color.BLACK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[6];
					corAtk = Color.BLACK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[6];
					corAtk = Color.BLACK;
				}
			}
			buttons.TASK = 0;

			break;

		case 8:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[7];
					corAtk = Color.BLACK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[7];
					corAtk = Color.BLACK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[7];
					corAtk = Color.BLACK;
				}
			}
			buttons.TASK = 0;

			break;
		case 9:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[8];
					corAtk = Color.BLACK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[8];
					corAtk = Color.BLACK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[8];
					corAtk = Color.BLACK;
				}
			}
			buttons.TASK = 0;

			break;
		case 10:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[9];
					corAtk = Color.RED;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[9];
					corAtk = Color.RED;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[9];
					corAtk = Color.RED;
				}
			}
			buttons.TASK = 0;

			break;
		case 11:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[10];
					corAtk = Color.RED;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[10];
					corAtk = Color.RED;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[10];
					corAtk = Color.RED;
				}
			}
			buttons.TASK = 0;

			break;
		case 12:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[11];
					corAtk = Color.RED;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[11];
					corAtk = Color.RED;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[11];
					corAtk = Color.RED;
				}
			}
			buttons.TASK = 0;

			break;
		case 13:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[12];
					corAtk = Color.PINK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[12];
					corAtk = Color.PINK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[12];
					corAtk = Color.PINK;
				}
			}
			buttons.TASK = 0;

			break;
		case 14:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[13];
					corAtk = Color.PINK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[13];
					corAtk = Color.PINK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[13];
					corAtk = Color.PINK;
				}
			}
			buttons.TASK = 0;

			break;
		case 15:
			if (buttons.get(buttons.TASK - 1).color == cor) {
				buttons.get(buttons.TASK - 1).color = outra;
				if (atk1 == 0) {
					atk1 = emocao.emocoes[14];
					corAtk = Color.PINK;
				} else if (atk2 == 0) {
					atk2 = emocao.emocoes[14];
					corAtk = Color.PINK;
				} else if (atk3 == 0 && atk2 != 0) {
					atk3 = emocao.emocoes[14];
					corAtk = Color.PINK;
				}
			}
			buttons.TASK = 0;

			break;

		default:

			break;

		}

		if (dividido != null && dividido.length > 1 && dividido[1] != null) {
			switch (dividido[1]) {
			case "trocaTurno":
				// se for dano verificar quem ta levando dano e aplicar o dano
				// apos receber uma mensagem de dano, eu sei que o turno
				// do meu oponente acabou. logo agora é o meu
				meuturno = true;
				if (dividido[2].equals("1")) {
					personagem2.levadano(Integer.parseInt(dividido[3]));
					personagem1.aux = 0;
					personagem1.indexAnimation = 0;
					personagem2.aux = 0;
					personagem2.indexAnimation = 0;
					personagem1.animacao = "Atk";
					personagem2.animacao = "LAtk";
					danosVisuais.add(new DanoVisual(personagem2.x + 20, personagem2.y, tempoDano,
							"" + Integer.parseInt(dividido[3])));

					gP = new GeradorParticula((int) personagem2.x + 20, (int) personagem2.y + 100, // local

					cF, 100, 450, GeradorParticula.SINGLE, 500, true, true,
							new Objeto(0, 0, 0.2f, 0.4f, 2, 2, new Color(Integer.parseInt(dividido[4]),
									Integer.parseInt(dividido[5]), Integer.parseInt(dividido[6])), true, true,

					-1));

					listaGP.add(gP);

				} else if (dividido[2].equals("2")) {
					personagem1.levadano(Integer.parseInt(dividido[3]));
					personagem1.aux = 0;
					personagem1.indexAnimation = 0;
					personagem2.aux = 0;
					personagem2.indexAnimation = 0;
					personagem2.animacao = "Atk";
					personagem1.animacao = "LAtk";
					danosVisuais.add(new DanoVisual(personagem1.x + 20, personagem1.y, tempoDano,
							"" + Integer.parseInt(dividido[3])));

					gP = new GeradorParticula((int) personagem1.x + 20, (int) personagem1.y + 100, // local

					cF, 100, 450, GeradorParticula.SINGLE, 500, true, true,
							new Objeto(0, 0,
									0.2f, 0.4f, 2, 2, new Color(Integer.parseInt(dividido[4]),
											Integer.parseInt(dividido[5]), Integer.parseInt(dividido[6])),
									true, true, -1));
					listaGP.add(gP);

				}
				vez = Integer.parseInt(dividido[2]);
				fimDeAtk();
				dividido[1] = "nada";

				break;

			case "fimtempo":
				meuturno = true;
				fimDeAtk();
				dividido[1] = "nada";
				break;

			default:
				break;
			}
		}

		tempoqPassou += time;

		personagem1.update(time, 0, 0);
		personagem2.update(time, 0, 0);
		tempo.updateTempo(time, 15000 - (int) tempoqPassou, 15000);
		if (tempoqPassou >= 15000) {
			// acabou o tempo
			// trocar de turno
			GamePanel.cliente.sendMessage("fimtempo");
			meuturno = false;
			fimDeAtk();
		}

		if (vez == 2) {

			daVez = personagem2;
			xDasBolas = (int) daVez.xI + 15;
			bx = xDasBolas - 80;
			by = 500;

			for (int i = 0; i < 15; i++) {
				buttons.get(i).x = bx;
				buttons.get(i).y = by;
				by -= 40;
				if ((i + 1) % 3 == 0) {
					bx += 40;
					by = 500;
				}
			}
		} else {

			daVez = personagem1;
			xDasBolas = (int) daVez.xI + 15;
			bx = xDasBolas - 80;
			by = 500;

			for (int i = 0; i < 15; i++) {
				buttons.get(i).x = bx;
				buttons.get(i).y = by;
				by -= 40;
				if ((i + 1) % 3 == 0) {
					bx += 40;
					by = 500;
				}
			}
		}

		if (buttons.verificaTres(outra) && vez == 1) {
			personagem2.levadano(personagem1.atk(atk1, atk2, atk3));

			personagem1.aux = 0;
			personagem1.indexAnimation = 0;
			personagem2.aux = 0;
			personagem2.indexAnimation = 0;
			personagem1.animacao = "Atk";
			personagem2.animacao = "LAtk";

			danosVisuais.add(new DanoVisual(personagem2.x + 20, personagem2.y, tempoDano, "" + personagem1.dano));

			gP = new GeradorParticula((int) personagem2.x + 20, (int) personagem2.y + 100, cF, 100, 450,
					GeradorParticula.SINGLE, 500, true, true,

			new Objeto(0, 0, 0.2f, 0.4f, 2, 2, corAtk, true, true, -1));
			listaGP.add(gP);

			personagem1.fimdeatk();
			meuturno = false;
			GamePanel.cliente.sendMessage("trocaTurno;" + vez + ";" + personagem1.atk(atk1, atk2, atk3) + ";"
					+ corAtk.getRed() + ";" + corAtk.getGreen() + ";" + corAtk.getBlue());
			fimDeAtk();

		} else if (buttons.verificaTres(outra) && vez == 2) {
			personagem1.levadano(personagem2.atk(atk1, atk2, atk3));

			personagem1.aux = 0;
			personagem1.indexAnimation = 0;
			personagem2.aux = 0;
			personagem2.indexAnimation = 0;
			personagem2.animacao = "Atk";
			personagem1.animacao = "LAtk";

			danosVisuais.add(new DanoVisual(personagem1.x + 20, personagem1.y, tempoDano, "" + personagem2.dano));

			gP = new GeradorParticula((int) personagem1.x + 20, (int) personagem1.y + 100, cF, 100, 650,
					GeradorParticula.SINGLE, 500, true, true,

			new Objeto(0, 0, 0.2f, 0.4f, 2, 2, corAtk, true, true, -1));
			listaGP.add(gP);

			personagem2.fimdeatk();
			GamePanel.cliente.sendMessage("trocaTurno;" + vez + ";" + personagem2.atk(atk1, atk2, atk3) + ";"
					+ corAtk.getRed() + ";" + corAtk.getGreen() + ";" + corAtk.getBlue());
			meuturno = false;
			fimDeAtk();

		}

		if (personagem1.vida <= 0) {
			musica1.close();
			GamePanel.screen = new Win(1, dividido[0]);
		} else if (personagem2.vida <= 0) {
			musica1.close();
			GamePanel.screen = new Win(2, dividido[0]);
		}

		cF.update(time);

		// geradores de particulas
		for (GeradorParticula geradorParticula : listaGP) {
			geradorParticula.update(time, 0, 0);
			if (geradorParticula.tipo.equals("nada")) {
				if (cF.checkSingleEnd(geradorParticula)) {
					listaGP.remove(geradorParticula);
					break;
				}
			}
		}

	}

	public void fimDeAtk() {
	
		atk1 = 0;
		atk2 = 0;
		atk3 = 0;
		buttons.cleanColor(cor, outra);
		if (vez == 1) {
			vez = 2;
		} else {
			vez = 1;
		}
		tempoqPassou = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		buttons.pressed(e.getX(), e.getY());
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub

		if (x > daVez.x - 85 && x < daVez.x + 140 && y > 360 && y < 590) {

			if (daVez.colideMouse(x, y)) {
				if (meuturno) {
					if (x < xDasBolas - 50) {
						n = 1;
						buttons.setVisible(false);
						buttons.setVisible(true, 0);
						buttons.setVisible(true, 1);
						buttons.setVisible(true, 2);
					} else if (x < xDasBolas - 10) {
						buttons.setVisible(false);
						buttons.setVisible(true, 3);
						buttons.setVisible(true, 4);
						buttons.setVisible(true, 5);
						n = 2;
					} else if (x < xDasBolas + 30) {
						n = 3;
						buttons.setVisible(false);
						buttons.setVisible(true, 6);
						buttons.setVisible(true, 7);
						buttons.setVisible(true, 8);
					} else if (x < xDasBolas + 70) {
						n = 4;
						buttons.setVisible(false);
						buttons.setVisible(true, 9);
						buttons.setVisible(true, 10);
						buttons.setVisible(true, 11);
					} else if (x < xDasBolas + 120) {
						n = 5;
						buttons.setVisible(false);
						buttons.setVisible(true, 12);
						buttons.setVisible(true, 13);
						buttons.setVisible(true, 14);
					}
				}
			}

		} else {
			if (!meuturno) {
				n = 0;
				buttons.setVisible(false);
			}
		}

	}

	@Override
	public void draw(Graphics2D dbg) {
		

		dbg.drawImage(image, 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);

		personagem1.draw(dbg, n, Color.WHITE);
		personagem2.draw(dbg, n, Color.WHITE);

	
		for (GeradorParticula geradorParticula : listaGP) {
			geradorParticula.draw(dbg, 0, 0);

		}

		// dbg.drawRect((int)daVez.x-85, 360, (int)daVez.x+140, 900);
		dbg.setColor(Color.BLACK);
		dbg.drawOval(xDasBolas, 540, 30, 30);
		dbg.drawOval(xDasBolas + 40, 540, 30, 30);
		dbg.drawOval(xDasBolas - 40, 540, 30, 30);
		dbg.drawOval(xDasBolas + 80, 540, 30, 30);
		dbg.drawOval(xDasBolas - 80, 540, 30, 30);

		dbg.setFont(new Font("Letter Gothic Std", 10, 10));
		if (n == 1) {

			dbg.drawOval(xDasBolas - 80, 500, 20, 20);
			dbg.drawOval(xDasBolas - 80, 460, 20, 20);
			dbg.drawOval(xDasBolas - 80, 420, 20, 20);
			if (vez == 1) {

				dbg.drawString("OTIMISMO", xDasBolas - 50, 435);
				dbg.drawString("EXTASE", xDasBolas - 50, 475);
				dbg.drawString("EUFORIA", xDasBolas - 50, 515);

			} else if (vez == 2) {

				dbg.drawString("OTIMISMO", xDasBolas - 135, 435);
				dbg.drawString("EXTASE", xDasBolas - 130, 475);
				dbg.drawString("EUFORIA", xDasBolas - 130, 515);
			}

		}
		if (n == 2) {

			dbg.drawOval(xDasBolas - 40, 500, 20, 20);
			dbg.drawOval(xDasBolas - 40, 460, 20, 20);
			dbg.drawOval(xDasBolas - 40, 420, 20, 20);
			if (vez == 1) {

				dbg.drawString("INSEGURANÇA", xDasBolas - 10, 435);
				dbg.drawString("DESESPERO", xDasBolas - 10, 475);
				dbg.drawString("MELANCOLIA", xDasBolas - 10, 515);

			} else if (vez == 2) {

				dbg.drawString("INSEGURANÇA", xDasBolas - 115, 435);
				dbg.drawString("DESESPERO", xDasBolas - 110, 475);
				dbg.drawString("MELANCOLIA", xDasBolas - 110, 515);
			}

		}
		if (n == 3) {

			dbg.drawOval(xDasBolas, 500, 20, 20);
			dbg.drawOval(xDasBolas, 460, 20, 20);
			dbg.drawOval(xDasBolas, 420, 20, 20);
			if (vez == 1) {

				dbg.drawString("CHOQUE", xDasBolas + 30, 435);
				dbg.drawString("PÂNICO", xDasBolas + 30, 475);
				dbg.drawString("INSEGURANÇA", xDasBolas + 30, 515);

			} else if (vez == 2) {

				dbg.drawString("CHOQUE", xDasBolas - 50, 435);
				dbg.drawString("PÂNICO", xDasBolas - 50, 475);
				dbg.drawString("INSEGURANÇA", xDasBolas - 77, 515);
			}
		}
		if (n == 4) {

			dbg.drawOval(xDasBolas + 40, 500, 20, 20);
			dbg.drawOval(xDasBolas + 40, 460, 20, 20);
			dbg.drawOval(xDasBolas + 40, 420, 20, 20);
			if (vez == 1) {

				dbg.drawString("RESSENTIMENTO", xDasBolas + 70, 435);
				dbg.drawString("IRRITAÇÃO", xDasBolas + 70, 475);
				dbg.drawString("FURIA", xDasBolas + 70, 515);

			} else if (vez == 2) {

				dbg.drawString("RESSENTIMENTO", xDasBolas - 42, 435);
				dbg.drawString("IRRITAÇÃO", xDasBolas - 20, 475);
				dbg.drawString("FURIA", xDasBolas, 515);
			}
		}
		if (n == 5) {

			dbg.drawOval(xDasBolas + 80, 500, 20, 20);
			dbg.drawOval(xDasBolas + 80, 460, 20, 20);
			dbg.drawOval(xDasBolas + 80, 420, 20, 20);
			if (vez == 1) {

				dbg.drawString("DESEJO", xDasBolas + 110, 435);
				dbg.drawString("ADORAÇÃO", xDasBolas + 110, 475);
				dbg.drawString("OBSESSÃO", xDasBolas + 110, 515);

			} else if (vez == 2) {

				dbg.drawString("DESEJO", xDasBolas + 30, 435);
				dbg.drawString("ADORAÇÃO", xDasBolas + 20, 475);
				dbg.drawString("OBSESSÃO", xDasBolas + 20, 515);
			}
		}

		tempo.drawTempo(dbg, 0, 0);
		buttons.draw(dbg, 0, 0);

		for (int i = 0; i < danosVisuais.size(); i++) {
			danosVisuais.get(i).draw(dbg, 0, 0);
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
}
