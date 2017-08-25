package br.com.ronaldo.robot.model;

public class Position {

	// Limites com relação a borda N,S,L,W
	private int limits[];

	private char direction;

	public Position() {
		this.limits = new int[] { 4, 0, 4, 0 };
		this.direction = 'N';
	}

	public int[] getLimites() {
		return limits;
	}

	public void setLimites(int limites[]) {
		this.limits = limites;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direcao) {
		this.direction = direcao;
	}

}
