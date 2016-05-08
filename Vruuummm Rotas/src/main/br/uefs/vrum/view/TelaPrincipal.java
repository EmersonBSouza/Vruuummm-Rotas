package br.uefs.vrum.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.uefs.vrum.controller.Controller;
import br.uefs.vrum.util.Vertice;
import java.awt.Font;
import javax.swing.JTextField;

public class TelaPrincipal extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoordenadasGUI coordenadas = CoordenadasGUI.getInstance();
	public List<JLabel> vertices = new ArrayList<JLabel>();
	Controller controller = new Controller();
	JPanel panel = new JPanel();
	public JPanel panel_1 = new JPanel();
	JComboBox<Vertice> cBpontoOrigem;
	JComboBox<Vertice> cBpontoDestino;
	JComboBox<Vertice> cBdefinirEstacionamento;
	JComboBox<Vertice> cBdefinirBanco;
	JComboBox<Vertice> cBdefinirPontoColeta;
	private JTextField textTempoPercurso;
	
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
					String nome = JOptionPane.showInputDialog("Insira o nome do novo local:");
					if(nome != null) {
					adicionarPontoTela(posicaoMouse.x, posicaoMouse.y,nome);
					Vertice novoPonto = controller.adicionarPonto(nome);
					cBpontoOrigem.addItem(novoPonto);
					cBpontoDestino.addItem(novoPonto);
					cBdefinirEstacionamento.addItem(novoPonto);
					cBdefinirBanco.addItem(novoPonto);
					cBdefinirPontoColeta.addItem(novoPonto);
					}
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
		
		JTextPane txtAdicionarCaminho = new JTextPane();
		txtAdicionarCaminho.setBackground(new Color(240,240,240));
		txtAdicionarCaminho.setText("Adicionar Caminho");
		txtAdicionarCaminho.setBounds(70, 38, 99, 20);
		txtAdicionarCaminho.setEditable(false);
		panel_1.add(txtAdicionarCaminho);
		
		cBpontoOrigem = new JComboBox<Vertice>();
		cBpontoOrigem.setToolTipText("Selecione um ponto da liga\u00E7\u00E3o da rota");
		cBpontoOrigem.setBounds(10, 75, 80, 20);
		panel_1.add(cBpontoOrigem);
		
		cBpontoDestino = new JComboBox<Vertice>();
		cBpontoDestino.setToolTipText("Selecione um ponto de liga\u00E7\u00E3o da rota");
		cBpontoDestino.setBounds(121, 75, 80, 20);
		panel_1.add(cBpontoDestino);
		
		JButton btnAdicionarLigao = new JButton("Adicionar Liga\u00E7\u00E3o");
		btnAdicionarLigao.setToolTipText("Cria uma liga\u00E7\u00E3o entre dois pontos selecionados");
		btnAdicionarLigao.setBounds(45, 139, 129, 23);
		btnAdicionarLigao.addActionListener(new gerarCaminhoAction());
		panel_1.add(btnAdicionarLigao);
		
		JTextPane txtDefinirEstacionamento = new JTextPane();
		txtDefinirEstacionamento.setText("Definir Estacionamento");
		txtDefinirEstacionamento.setBounds(53, 185, 116, 20);
		txtDefinirEstacionamento.setBackground(new Color(240,240,240));
		txtDefinirEstacionamento.setEditable(false);
		panel_1.add(txtDefinirEstacionamento);
		
		cBdefinirEstacionamento = new JComboBox<Vertice>();
		cBdefinirEstacionamento.setBounds(70, 216, 80, 20);
		panel_1.add(cBdefinirEstacionamento);
		
		JTextPane txtDefinirPontoColeta = new JTextPane();
		txtDefinirPontoColeta.setEditable(false);
		txtDefinirPontoColeta.setText("Definir Ponto de Coleta");
		txtDefinirPontoColeta.setBackground(new Color(240,240,240));
		txtDefinirPontoColeta.setBounds(56, 261, 120, 20);
		panel_1.add(txtDefinirPontoColeta);
		
		cBdefinirPontoColeta = new JComboBox<Vertice>();
		cBdefinirPontoColeta.setBounds(70, 298, 80, 20);
		panel_1.add(cBdefinirPontoColeta);
		
		JTextPane txtpnDefinirBanco = new JTextPane();
		txtpnDefinirBanco.setText("Definir Banco");
		txtpnDefinirBanco.setBackground(new Color(240,240,240));
		txtpnDefinirBanco.setBounds(82, 341, 78, 20);
		panel_1.add(txtpnDefinirBanco);
		
		cBdefinirBanco = new JComboBox<Vertice>();
		cBdefinirBanco.setBounds(70, 382, 80, 20);
		panel_1.add(cBdefinirBanco);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPane.setText("\u2194");
		textPane.setBounds(95, 69, 26, 20);
		textPane.setBackground(new Color(240,240,240));
		panel_1.add(textPane);
		
		textTempoPercurso = new JTextField();
		textTempoPercurso.setBounds(121, 106, 26, 20);
		panel_1.add(textTempoPercurso);
		textTempoPercurso.setColumns(10);
		
		JLabel lblTempoDoPercurso = new JLabel("Tempo do Percurso:");
		lblTempoDoPercurso.setBounds(20, 106, 101, 22);
		panel_1.add(lblTempoDoPercurso);
		
		JTextPane txtpnMinutos = new JTextPane();
		txtpnMinutos.setText("Minutos");
		txtpnMinutos.setBackground(new Color(240,240,240));
		txtpnMinutos.setBounds(153, 106, 44, 20);
		panel_1.add(txtpnMinutos);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-50);
	}
	
	public void adicionarPontoTela(int x,int y, String nome){
		coordenadas.salvarCoordenadas(x, y, nome);
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
			g2d.drawString(atual.getText(),(float)atual.getBounds().getCenterX()-atual.getText().length()*3,(float)atual.getBounds().getY()-25);
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
		JTextPane g = new JTextPane();
		g.setText(texto);
		g.setEditable(false);
		g.setBounds(label.getX(), label.getY()-40, 126, 25);
		JOptionPane.showMessageDialog(g, g.getText());
	}
	
	public class gerarCaminhoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Vertice origem = (Vertice) cBpontoOrigem.getSelectedItem();
			Vertice destino = (Vertice) cBpontoDestino.getSelectedItem();
			controller.adicionarCaminho(origem, destino, 0);
		}
	}
}
