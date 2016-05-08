package br.uefs.vrum.view;

import javax.swing.JLabel;

public class Ponto {

	private JLabel ponto;
	private boolean estaNaTela;
	
	public JLabel getPonto() {
		return ponto;
	}
	public void setPonto(JLabel ponto) {
		this.ponto = ponto;
	}
	public boolean isEstaNaTela() {
		return estaNaTela;
	}
	public void setEstaNaTela(boolean estaNaTela) {
		this.estaNaTela = estaNaTela;
	}
}
