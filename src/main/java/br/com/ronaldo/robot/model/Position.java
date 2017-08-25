package br.com.ronaldo.robot.model;

/**
 * Classe utilizada para as informações de posição do robô
 * 
 * @author ronaldocaldas
 *
 */
public class Position {

	// Limites com relação a borda N,S,L,W
	private int limits[];

	private char direction;
	
	private String actualPosition;

	public Position() {
		this.limits = new int[] { 4, 0, 4, 0 };
		this.direction = 'N';
	}
	
	/**
	 * Verifica se o robo ultrapassou os limites do terreno, isto é, caso o
	 * valor de alguns dos limites for menor que 0
	 * 
	 * @param limites
	 */
	public void verifyLimits() {
		for (int i = 0; i < limits.length; i++) {
			if (limits[i] < 0) {
				throw new IllegalArgumentException("The robot fell out dude! Build another one...");
			}
		}
	}


	/**
	 * Retorna a posição final do Robô de acordo com o format pedido (2, 0, S)
	 * 
	 * @return
	 */
	public String updateActualPosition() {
		if (this.getDirection() == 'S' || this.getDirection() == 'N') {
			return "(" + limits[2] + ", " + limits[1] + ", " + this.getDirection() + ")";
		} else {
			return "(" + limits[3] + ", " + limits[1] + ", " + this.getDirection() + ")";
		}

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

	public String getActualPosition() {
		return actualPosition;
	}

	public void setActualPosition(String actualPosition) {
		this.actualPosition = actualPosition;
	}

}
