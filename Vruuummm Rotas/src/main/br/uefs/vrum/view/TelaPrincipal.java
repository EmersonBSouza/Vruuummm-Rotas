package br.uefs.vrum.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
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
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);

		Iterator<JLabel> iterador = coordenadas.getListaCoordenadas().iterator();
		java.net.URL url = null;
		JLabel atual;
		while(iterador.hasNext()) {
			Font myFont = new Font ("Comic Sans", 1, 50);
			g2d.setFont(myFont);
			atual = (JLabel) iterador.next();
			url = this.getClass().getResource("icone_Ponto.png");
			g2d.drawImage(new ImageIcon(url).getImage(), atual.getX(), atual.getY(), null);
			
			g2d.drawString(atual.getText(), atual.getX()+15, atual.getY()+60);
		}
	}
}
