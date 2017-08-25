package br.com.ronaldo.robot.model;

public class Position {

	// Limites com relação a borda N,S,L,W
	private int limites[];

	private char direcao;

	public Position() {
		this.limites = new int[] { 4, 0, 4, 0 };
		this.direcao = 'N';
	}

	public int[] getLimites() {
		return limites;
	}

	public void setLimites(int limites[]) {
		this.limites = limites;
	}

	public char getDirecao() {
		return direcao;
	}

	public void setDirecao(char direcao) {
		this.direcao = direcao;
	}

}
