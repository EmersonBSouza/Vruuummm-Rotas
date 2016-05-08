package br.uefs.vrum.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CoordenadasGUI {


	public static CoordenadasGUI coordenadas;
	ImageIcon iconePonto = new ImageIcon("C:\\Users\\Emerloko\\git\\Vruuummm Rotas\\resources");
	//private List<JLabel> listaCoordenadas = new ArrayList<JLabel>();
	private List<Ponto> listaCoordenadas = new ArrayList<Ponto>();
	private CoordenadasGUI(){
		
	}
	
	public static CoordenadasGUI getInstance(){
		if(coordenadas==null){
			coordenadas = new CoordenadasGUI();
		}
		else{
			return coordenadas;
		}
		return coordenadas;
	}
	
	public void salvarCoordenadas(int x, int y, String nome){
		
		Ponto ponto = new Ponto();
		JLabel label = new JLabel();
		label.setIcon(iconePonto);
		label.setBounds(x, y, 20, 20);
		label.setText(nome);
		ponto.setPonto(label);
		ponto.setEstaNaTela(true);
		listaCoordenadas.add(ponto);
		
	}
	
	public List<Ponto> getListaCoordenadas() {
		return listaCoordenadas;
	}

	public void setListaCoordenadas(List<Ponto> listaCoordenadas) {
		this.listaCoordenadas = listaCoordenadas;
	}
}
