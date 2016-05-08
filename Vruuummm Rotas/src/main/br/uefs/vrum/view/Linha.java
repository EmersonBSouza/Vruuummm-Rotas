package br.uefs.vrum.view;

public class Linha {
	
	private String nomePonto1;
	private String nomePonto2;
	private boolean parteDoMenorCaminho;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	
	public Linha(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	/**
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}
	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}
	/**
	 * @return the y1
	 */
	public int getY1() {
		return y1;
	}
	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}
	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}
	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	/**
	 * @return the nomePonto1
	 */
	public String getNomePonto1() {
		return nomePonto1;
	}
	/**
	 * @param nomePonto1 the nomePonto1 to set
	 */
	public void setNomePonto1(String nomePonto1) {
		this.nomePonto1 = nomePonto1;
	}
	/**
	 * @return the nomePonto2
	 */
	public String getNomePonto2() {
		return nomePonto2;
	}
	/**
	 * @param nomePonto2 the nomePonto2 to set
	 */
	public void setNomePonto2(String nomePonto2) {
		this.nomePonto2 = nomePonto2;
	}
	/**
	 * @return the parteDoMenorCaminho
	 */
	public boolean isParteDoMenorCaminho() {
		return parteDoMenorCaminho;
	}
	/**
	 * @param parteDoMenorCaminho the parteDoMenorCaminho to set
	 */
	public void setParteDoMenorCaminho(boolean parteDoMenorCaminho) {
		this.parteDoMenorCaminho = parteDoMenorCaminho;
	}
}
