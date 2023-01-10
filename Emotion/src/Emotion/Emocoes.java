package Emotion;

public class Emocoes {
	int emocoes[];
	int fase;

	public Emocoes() {
		// TODO Auto-generated constructor stub
		emocoes = new int[15];

		emocoes[0] = 2; // 1 -euforia
		emocoes[1] = 3; // 2 - extase
		emocoes[2] = 4; // 3 - otimismo
		emocoes[3] = -2; // 4 - melancolia
		emocoes[4] = -3; // 5 - desespero
		emocoes[5] = -4; // 6 - inseguranca
		emocoes[6] = -5; // 7 - panico
		emocoes[7] = -4; // 8 - choque
		emocoes[8] = -3; // 9 - tensao
		emocoes[9] = 3; // 10 - irritacao
		emocoes[10] = 5; // 11 - furia
		emocoes[11] = 2; // 12 - ressentimento
		emocoes[12] = 4; // 13 - desejo
		emocoes[13] = 2; // 14 - adoracao
		emocoes[14] = 3; // 15 - obsessao

	}

	public void Fase(int fase) {
		this.fase = fase;

		if (fase == 1) {
			
			emocoes[0] = 2; // 1 -euforia
			emocoes[1] = 3; // 2 - extase
			emocoes[2] = 4; // 3 - otimismo
			emocoes[3] = -4; // 4 - melancolia
			emocoes[4] = -5; // 5 - desespero
			emocoes[5] = -6; // 6 - inseguranca
			emocoes[6] = -7; // 7 - panico
			emocoes[7] = -6; // 8 - choque
			emocoes[8] = -5; // 9 - tensao
			emocoes[9] = 3; // 10 - irritacao
			emocoes[10] = 5; // 11 - furia
			emocoes[11] = 2; // 12 - ressentimento
			emocoes[12] = 4; // 13 - desejo
			emocoes[13] = 2; // 14 - adoracao
			emocoes[14] = 3; // 15 - obsessao
		}

		if (fase == 2) {
			
			emocoes[0] = 2; // 1 -euforia
			emocoes[1] = 3; // 2 - extase
			emocoes[2] = 4; // 3 - otimismo
			emocoes[3] = -2; // 4 - melancolia
			emocoes[4] = -3; // 5 - desespero
			emocoes[5] = -4; // 6 - inseguranca
			emocoes[6] = -5; // 7 - panico
			emocoes[7] = -4; // 8 - choque
			emocoes[8] = -3; // 9 - tensao
			emocoes[9] = 3; // 10 - irritacao
			emocoes[10] = 5; // 11 - furia
			emocoes[11] = 2; // 12 - ressentimento
			emocoes[12] = 4; // 13 - desejo
			emocoes[13] = 2; // 14 - adoracao
			emocoes[14] = 3; // 15 - obsessao
		}

		if (fase == 3) {
			
			emocoes[0] = 4; // 1 -euforia
			emocoes[1] = 5; // 2 - extase
			emocoes[2] = 6; // 3 - otimismo
			emocoes[3] = -2; // 4 - melancolia
			emocoes[4] = -3; // 5 - desespero
			emocoes[5] = -4; // 6 - inseguranca
			emocoes[6] = -5; // 7 - panico
			emocoes[7] = -4; // 8 - choque
			emocoes[8] = -3; // 9 - tensao
			emocoes[9] = 5; // 10 - irritacao
			emocoes[10] = 7; // 11 - furia
			emocoes[11] = 4; // 12 - ressentimento
			emocoes[12] = 6; // 13 - desejo
			emocoes[13] = 4; // 14 - adoracao
			emocoes[14] = 5; // 15 - obsessao
		}

	}

}
