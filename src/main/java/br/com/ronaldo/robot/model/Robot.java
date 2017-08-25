package br.com.ronaldo.robot.model;

/**
 * Classe representando o Robô
 * 
 * @author ronaldocaldas
 *
 */
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
					this.getPosicao().verifyLimits();
					this.getPosicao().getLimites()[1]++;
					this.getPosicao().verifyLimits();
					break;
				case 'S':
					this.getPosicao().getLimites()[0]++;
					this.getPosicao().verifyLimits();
					this.getPosicao().getLimites()[1]--;
					this.getPosicao().verifyLimits();
					break;
				case 'L':
					this.getPosicao().getLimites()[2]--;
					this.getPosicao().verifyLimits();
					this.getPosicao().getLimites()[3]++;
					this.getPosicao().verifyLimits();
					break;
				case 'W':
					this.getPosicao().getLimites()[2]++;
					this.getPosicao().verifyLimits();
					this.getPosicao().getLimites()[3]--;
					this.getPosicao().verifyLimits();
					break;
				default:
					break;
				}
			}
		}

		this.getPosicao().setActualPosition(this.getPosicao().updateActualPosition());
		return entry;

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

	public Position getPosicao() {
		return posicao;
	}

	public void setPosicao(Position posicao) {
		this.posicao = posicao;
	}
}
