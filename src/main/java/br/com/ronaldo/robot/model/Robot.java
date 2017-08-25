package br.com.ronaldo.robot.model;

public class Robot {

	private Position posicao;

	public Robot() {
		this.posicao = new Position();
	}

	/**
	 * Processa a variável {movimento} enviada na URL
	 * http:///rest/mars/{movimento}
	 * 
	 * @param entry
	 * @return
	 * @throws Exception
	 */
	public String processEntry(String entry) throws Exception {

		verifyInvalidChars(entry);
		for (int i = 0; i < entry.length(); i++) {
			char caractere = entry.charAt(i);
			// Se o comando é mudança de direção ele só muda a direção atual
			if (caractere != 'M') {

				// Atualiza o valor da direção de acordo com o lado que o robo
				// vira.
				this.posicao.setDirection(updateDirection(this.posicao.getDirection(), caractere));
				// se o comando é movimento (M) verifica qual é a direção
				// e atualiza os valores dos this.getPosicao().getLimites()
			} else {
				switch (this.posicao.getDirection()) {
				case 'N':
					this.getPosicao().getLimites()[0]--;
					verifyLimits(this.getPosicao().getLimites());
					this.getPosicao().getLimites()[1]++;
					verifyLimits(this.getPosicao().getLimites());
					break;
				case 'S':
					this.getPosicao().getLimites()[0]++;
					verifyLimits(this.getPosicao().getLimites());
					this.getPosicao().getLimites()[1]--;
					verifyLimits(this.getPosicao().getLimites());
					break;
				case 'L':
					this.getPosicao().getLimites()[2]--;
					verifyLimits(this.getPosicao().getLimites());
					this.getPosicao().getLimites()[3]++;
					verifyLimits(this.getPosicao().getLimites());
					break;
				case 'W':
					this.getPosicao().getLimites()[2]++;
					verifyLimits(this.getPosicao().getLimites());
					this.getPosicao().getLimites()[3]--;
					verifyLimits(this.getPosicao().getLimites());
					break;
				default:
					break;
				}
			}
		}

		this.getPosicao().setActualPosition(updateActualPosition(this.getPosicao().getLimites()));
		return entry;

	}

	/**
	 * Verifica se o robo ultrapassou os limites do terreno, isto é, caso o
	 * valor de alguns dos limites for menor que 0
	 * 
	 * @param limites
	 */
	private void verifyLimits(int[] limites) {
		for (int i = 0; i < limites.length; i++) {
			if (limites[i] < 0) {
				throw new IllegalArgumentException("The robot fell out dude! Build another one...");
			}
		}
	}

	/**
	 * Atualiza a direção do robô
	 * 
	 * @param direction
	 * @param caractere
	 * @return
	 */
	private char updateDirection(char direction, char side) {
		switch (side) {
		case 'R':
			if (direction == 'N') {
				direction = 'L';
			} else if (direction == 'S') {
				direction = 'W';
			} else if (direction == 'L') {
				direction = 'S';
			} else if (direction == 'W') {
				direction = 'N';
			}
			break;
		case 'L':
			if (direction == 'N') {
				direction = 'W';
			} else if (direction == 'S') {
				direction = 'L';
			} else if (direction == 'L') {
				direction = 'N';
			} else if (direction == 'W') {
				direction = 'S';
			}
			break;

		default:
			break;
		}

		return direction;
	}

	/**
	 * Verifica se a string de entrada tem somente os caracteres validos
	 * 
	 * @param entry
	 * @throws Exception
	 */
	private void verifyInvalidChars(String entry) throws Exception {
		for (int i = 0; i < entry.length(); i++) {
			char caractere = entry.charAt(i);

			if (caractere != 'M' && caractere != 'L' && caractere != 'R') {
				throw new IllegalArgumentException("The 'movimento' parameter must not be diferent of 'M', 'L' or 'R'");
			}
		}
	}
	
	
	private String updateActualPosition(int[] limits){
		if(this.posicao.getDirection() == 'S' || this.posicao.getDirection() == 'N'){
			return "("+limits[2]+", "+limits[1]+", "+this.posicao.getDirection()+")";
		}else{
			return "("+limits[3]+", "+limits[1]+", "+this.posicao.getDirection()+")";
		}
		
	}

	public Position getPosicao() {
		return posicao;
	}

	public void setPosicao(Position posicao) {
		this.posicao = posicao;
	}
}
