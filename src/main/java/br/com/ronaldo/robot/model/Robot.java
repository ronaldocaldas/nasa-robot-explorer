package br.com.ronaldo.robot.model;

public class Robot {

	private Position posicao;
	
	

	public Robot() {
		this.posicao =  new Position();
	}

	public String processEntry(String entry) throws Exception {

		verifyInvalidChars(entry);
		for (int i = 0; i < entry.length(); i++) {

		}

		return entry;

	}

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
