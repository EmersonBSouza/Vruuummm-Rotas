package br.uefs.vrum.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CoordenadasGUI {


	public static CoordenadasGUI coordenadas;
	ImageIcon iconePonto = new ImageIcon("C:\\Users\\Emerloko\\git\\Vruuummm Rotas\\resources");
	private List<JLabel> listaCoordenadas = new ArrayList<JLabel>();

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
	
	public void salvarCoordenadas(int x, int y){
		JLabel label = new JLabel("Ola");
		label.setIcon(iconePonto);
		label.setToolTipText("Ola");
		label.setBounds(x, y, 20, 20);
		listaCoordenadas.add(label);
	}
	
	public List<JLabel> getListaCoordenadas() {
		return listaCoordenadas;
	}

	public void setListaCoordenadas(List<JLabel> listaCoordenadas) {
		this.listaCoordenadas = listaCoordenadas;
	}
}
