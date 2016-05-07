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

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class TelaPrincipal extends JApplet {

	public CoordenadasGUI coordenadas = CoordenadasGUI.getInstance();
	public List<JLabel> vertices = new ArrayList<JLabel>();
	JPanel panel = new JPanel();
	public JPanel panel_1 = new JPanel();
	/**
	 * Create the applet.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public TelaPrincipal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		getContentPane().setLayout(null);
		panel.setBackground(new Color(213,131,123));
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Point posicaoMouse = getContentPane().getMousePosition();
				adicionarPontoTela(posicaoMouse.x, posicaoMouse.y);
			}
		});
		panel.setBounds(10, 11, 1120, 685);
		getContentPane().add(panel);
		
		panel_1.setBounds(1133, 11, 211, 685);
		getContentPane().add(panel_1);
		panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 159, 234), 6, true));
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JButton btnCalcularMenorRota = new JButton("Calcular Menor Rota");
		btnCalcularMenorRota.setBounds(45, 464, 129, 28);
		panel_1.add(btnCalcularMenorRota);
		
		JTextPane txtpnConfiguraesDeArestas = new JTextPane();
		txtpnConfiguraesDeArestas.setBackground(new Color(240,240,240));
		txtpnConfiguraesDeArestas.setText("Configura\u00E7\u00F5es de Arestas");
		txtpnConfiguraesDeArestas.setBounds(45, 38, 131, 20);
		txtpnConfiguraesDeArestas.setEditable(false);
		panel_1.add(txtpnConfiguraesDeArestas);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Selecione um ponto da liga\u00E7\u00E3o da rota");
		comboBox.setBounds(48, 69, 58, 20);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Selecione um ponto de liga\u00E7\u00E3o da rota");
		comboBox_1.setBounds(116, 69, 58, 20);
		panel_1.add(comboBox_1);
		
		JButton btnAdicionarLigao = new JButton("Adicionar Liga\u00E7\u00E3o");
		btnAdicionarLigao.setToolTipText("Cria uma liga\u00E7\u00E3o entre dois pontos selecionados");
		btnAdicionarLigao.setBounds(45, 100, 129, 23);
		panel_1.add(btnAdicionarLigao);
		
		JTextPane txtpnDefinirEstacionamento = new JTextPane();
		txtpnDefinirEstacionamento.setText("Definir Estacionamento");
		txtpnDefinirEstacionamento.setBounds(53, 146, 116, 20);
		txtpnDefinirEstacionamento.setBackground(new Color(240,240,240));
		panel_1.add(txtpnDefinirEstacionamento);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(96, 177, 38, 20);
		panel_1.add(comboBox_2);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-50);
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
