package br.uefs.vrum.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TelaPrincipal extends JApplet {

	public CoordenadasGUI coordenadas = CoordenadasGUI.getInstance();
	public List<JLabel> vertices = new ArrayList<JLabel>();
	JPanel panel = new JPanel();
	public JPanel panel_1 = new JPanel();
	/**
	 * Create the applet.
	 */
	public TelaPrincipal() {
		getContentPane().setLayout(null);
		panel.setBackground(new Color(213,131,123));
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Point posicaoMouse = getContentPane().getMousePosition();
				adicionarPontoTela(posicaoMouse.x, posicaoMouse.y);
			}
		});
		panel.setBounds(10, 11, 1120, 711);
		getContentPane().add(panel);
		
		
		panel_1.setBounds(1133, 11, 211, 711);
		getContentPane().add(panel_1);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-10);
	}
	
	public void adicionarPontoTela(int x,int y){
		
		coordenadas.salvarCoordenadas(x, y);
		panel.add(coordenadas.getListaCoordenadas().get(coordenadas.getListaCoordenadas().size()-1));
	}
}
