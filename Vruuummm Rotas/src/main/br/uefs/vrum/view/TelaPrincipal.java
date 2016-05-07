package br.uefs.vrum.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Point posicaoMouse = getContentPane().getMousePosition();
				JLabel label = encontrarPonto(posicaoMouse.x,posicaoMouse.y);
				if(label!=null){
					getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}else{
					getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		panel.setBackground(new Color(240,240,240));
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if((arg0.getModifiers() & MouseEvent.BUTTON1_MASK)!=0){
					Point posicaoMouse = getContentPane().getMousePosition();
					adicionarPontoTela(posicaoMouse.x, posicaoMouse.y);
				}else if((arg0.getModifiers() & MouseEvent.BUTTON3_MASK)!=0){
					Point posicaoMouse = getContentPane().getMousePosition();
					JLabel label = encontrarPonto(posicaoMouse.x,posicaoMouse.y);
					if(label!=null){
						exibirDados(label,label.getText());
					}
				}
			}
		});
		
		panel.setBounds(10, 11, 1120, 625);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/uefs/vrum/view/icone_Ponto.png")));
		lblNewLabel.setBackground(Color.YELLOW);
		panel.add(lblNewLabel);
		
		panel_1.setBounds(1133, 11, 211, 625);
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
		txtpnDefinirEstacionamento.setEditable(false);
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
		JLabel atual;
		while(iterador.hasNext()) {
			atual = (JLabel) iterador.next();
			g2d.drawImage(new ImageIcon(TelaPrincipal.class.getResource("/br/uefs/vrum/view/icone_Ponto.png")).getImage(), atual.getX()-20, atual.getY()-20, null);
		}
	}
	
	public JLabel encontrarPonto(int x, int y){
		
		for(JLabel label:coordenadas.getListaCoordenadas()){
			if(label.getBounds().getMinX()-20<=x && label.getBounds().getMaxX()+8>=x && label.getBounds().getMinY()-20<=y && label.getBounds().getMaxY()+10>= y){
				return label;
			}
		}
		return null;
	}
	
	public void exibirDados(JLabel label,String texto){
		JTextPane j = new JTextPane();
		j.setText(texto);
		j.setEditable(false);
		j.setBounds(label.getX(), label.getY()-40, 126, 25);
		JOptionPane.showMessageDialog(j, j.getText());
	}
	
	public void cadastrarPonto(){
		CadastroPonto ponto = new CadastroPonto();
	}
	
	public void init(){
		try {
			new TelaPrincipal();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
