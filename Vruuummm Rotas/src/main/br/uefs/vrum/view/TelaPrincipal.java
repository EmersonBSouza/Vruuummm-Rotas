package br.uefs.vrum.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.uefs.vrum.controller.Controller;
import br.uefs.vrum.exceptions.verticeInexistenteException;
import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.Vertice;

public class TelaPrincipal extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordenadasGUI coordenadas = CoordenadasGUI.getInstance();
	public List<Vertice> menorCaminho = new ArrayList<Vertice>();
	public List<Linha> linhas = new ArrayList<Linha>();
	public List<Linha> linhasVizinhas;
	public ImageIcon iconePonto = new ImageIcon(TelaPrincipal.class.getResource("/br/uefs/vrum/view/icone_Ponto.png"));
	private Controller controller = new Controller();
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JTextPane alertas;
	private JComboBox<Vertice> cBpontoOrigem;
	private JComboBox<Vertice> cBpontoDestino;
	private JComboBox<Vertice> cBdefinirEstacionamento;
	private JComboBox<Vertice> cBdefinirBanco;
	private JComboBox<Vertice> cBdefinirPontoColeta;
	private JComboBox<Vertice> cBdestinoRemocao;
	private JComboBox<Vertice> cBorigemRemocao;
	private List<List<Vertice>> menoresCaminhosPonto;
	private List<List<Vertice>> menoresCaminhosBanco;
	private JComboBox<List<List<Vertice>>> cBmenoresCaminhosPonto;
	private JComboBox<List<List<Vertice>>> cBmenoresCaminhosBanco;
	private JTextField textTempoPercurso;



	public void init(){
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-50);
	}
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
				Ponto label = encontrarPonto(posicaoMouse.x,posicaoMouse.y);
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
					for(Vertice v : controller.getGrafo().getListaVertices())
						if(v.getIndice().equals(nome)) {
							JOptionPane.showMessageDialog(null, "Já foi cadastrado um local com esse nome!");
							return;
						}
					if(nome != null) {
						adicionarPontoTela(posicaoMouse.x, posicaoMouse.y,nome);
						Vertice novoPonto = controller.adicionarPonto(nome);
						adicionarAoComboBox(novoPonto);	
					}
				}else if((arg0.getModifiers() & MouseEvent.BUTTON3_MASK)!=0){
					Point posicaoMouse = getContentPane().getMousePosition();
					Ponto ponto = encontrarPonto(posicaoMouse.x,posicaoMouse.y);
					if(ponto!=null){
						removerPonto(ponto);
						encontrarLinhasVizinhas(ponto);
						removerLinha();
						removerDoComboBox(ponto);
						atualizarCaminho();
						repaint();
					}
				}
			}
		});

		panel.setBounds(10, 11, 1120, 625);
		getContentPane().add(panel);

		panel_1.setBounds(1133, 11, 211, 625);
		getContentPane().add(panel_1);
		panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 159, 234), 6, true));
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);

		JButton btnCalcularMenorRota = new JButton("Calcular Menor Rota");
		btnCalcularMenorRota.setBounds(45, 351, 129, 28);
		btnCalcularMenorRota.addActionListener(new CalcularMenorCaminhoAction());
		panel_1.add(btnCalcularMenorRota);

		cBmenoresCaminhosPonto = new JComboBox<List<List<Vertice>>>();
		cBmenoresCaminhosPonto.setToolTipText("Escolha o caminho que deseja visualizar");
		cBmenoresCaminhosPonto.setVisible(false);
		cBmenoresCaminhosPonto.addActionListener(new ExibirCaminhoAction());
		panel_1.add(cBmenoresCaminhosPonto);

		cBmenoresCaminhosBanco = new JComboBox<List<List<Vertice>>>();
		cBmenoresCaminhosBanco.setToolTipText("Escolha o caminho que deseja visualizar");
		cBmenoresCaminhosBanco.setVisible(false);
		cBmenoresCaminhosBanco.addActionListener(new ExibirCaminhoAction());
		panel_1.add(cBmenoresCaminhosBanco);

		JTextPane txtAdicionarCaminho = new JTextPane();
		txtAdicionarCaminho.setBackground(new Color(240,240,240));
		txtAdicionarCaminho.setText("Adicionar Caminho");
		txtAdicionarCaminho.setBounds(70, 11, 99, 20);
		txtAdicionarCaminho.setEditable(false);
		panel_1.add(txtAdicionarCaminho);

		cBpontoOrigem = new JComboBox<Vertice>();
		cBpontoOrigem.setToolTipText("Selecione um ponto da liga\u00E7\u00E3o da rota");
		cBpontoOrigem.setBounds(10, 48, 80, 20);
		panel_1.add(cBpontoOrigem);

		cBpontoDestino = new JComboBox<Vertice>();
		cBpontoDestino.setToolTipText("Selecione um ponto de liga\u00E7\u00E3o da rota");
		cBpontoDestino.setBounds(121, 48, 80, 20);
		panel_1.add(cBpontoDestino);

		JButton btnAdicionarLigao = new JButton("Adicionar Liga\u00E7\u00E3o");
		btnAdicionarLigao.setToolTipText("Cria uma liga\u00E7\u00E3o entre dois pontos selecionados");
		btnAdicionarLigao.setBounds(45, 112, 129, 23);
		btnAdicionarLigao.addActionListener(new gerarCaminhoAction());
		panel_1.add(btnAdicionarLigao);

		JTextPane txtDefinirEstacionamento = new JTextPane();
		txtDefinirEstacionamento.setText("Definir Estacionamento");
		txtDefinirEstacionamento.setBounds(53, 170, 116, 20);
		txtDefinirEstacionamento.setBackground(new Color(240,240,240));
		txtDefinirEstacionamento.setEditable(false);
		panel_1.add(txtDefinirEstacionamento);

		cBdefinirEstacionamento = new JComboBox<Vertice>();
		cBdefinirEstacionamento.setBounds(70, 201, 80, 20);
		panel_1.add(cBdefinirEstacionamento);

		JTextPane txtDefinirPontoColeta = new JTextPane();
		txtDefinirPontoColeta.setEditable(false);
		txtDefinirPontoColeta.setText("Definir Ponto de Coleta");
		txtDefinirPontoColeta.setBackground(new Color(240,240,240));
		txtDefinirPontoColeta.setBounds(54, 232, 120, 20);
		panel_1.add(txtDefinirPontoColeta);

		cBdefinirPontoColeta = new JComboBox<Vertice>();
		cBdefinirPontoColeta.setBounds(70, 254, 80, 20);
		panel_1.add(cBdefinirPontoColeta);

		JTextPane txtpnDefinirBanco = new JTextPane();
		txtpnDefinirBanco.setText("Definir Banco");
		txtpnDefinirBanco.setBackground(new Color(240,240,240));
		txtpnDefinirBanco.setBounds(72, 285, 78, 20);
		panel_1.add(txtpnDefinirBanco);

		cBdefinirBanco = new JComboBox<Vertice>();
		cBdefinirBanco.setBounds(70, 308, 80, 20);
		panel_1.add(cBdefinirBanco);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPane.setText("\u2194");
		textPane.setBounds(95, 42, 26, 20);
		textPane.setBackground(new Color(240,240,240));
		panel_1.add(textPane);

		textTempoPercurso = new JTextField();
		textTempoPercurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();

				if(!(Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE )){
					arg0.consume();
				}
			}
		});
		textTempoPercurso.setBounds(121, 79, 26, 20);
		panel_1.add(textTempoPercurso);
		textTempoPercurso.setColumns(10);

		JLabel lblTempoDoPercurso = new JLabel("Tempo do Percurso:");
		lblTempoDoPercurso.setBounds(20, 79, 101, 22);
		panel_1.add(lblTempoDoPercurso);

		JTextPane txtpnMinutos = new JTextPane();
		txtpnMinutos.setText("Minutos");
		txtpnMinutos.setBackground(new Color(240,240,240));
		txtpnMinutos.setBounds(153, 79, 44, 20);
		panel_1.add(txtpnMinutos);

		JTextPane txtpnRemoverCaminho = new JTextPane();
		txtpnRemoverCaminho.setText("Remover Caminho");
		txtpnRemoverCaminho.setBounds(70, 492, 99, 20);
		txtpnRemoverCaminho.setBackground(new Color(240,240,240));
		panel_1.add(txtpnRemoverCaminho);

		cBorigemRemocao = new JComboBox<Vertice>();
		cBorigemRemocao.setBounds(10, 524, 80, 20);
		panel_1.add(cBorigemRemocao);

		JTextPane txtpnu = new JTextPane();
		txtpnu.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnu.setText("\u2194");
		txtpnu.setBounds(95, 519, 26, 20);
		txtpnu.setBackground(new Color(240,240,240));
		panel_1.add(txtpnu);

		cBdestinoRemocao = new JComboBox<Vertice>();
		cBdestinoRemocao.setBounds(121, 524, 80, 20);
		panel_1.add(cBdestinoRemocao);

		JButton btnRemoverLigao = new JButton("Remover Liga\u00E7\u00E3o");
		btnRemoverLigao.setBounds(45, 562, 129, 23);
		btnRemoverLigao.addActionListener(new RemoverLigacao());
		panel_1.add(btnRemoverLigao);
		
		alertas = new JTextPane();
		alertas.setForeground(Color.RED);
		alertas.setEditable(false);
		alertas.setBackground(new Color(240,240,240));
		alertas.setBounds(45, 141, 138, 28);
		panel_1.add(alertas);
		//	setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-50);
	}

	public void adicionarPontoTela(int x,int y, String nome){
		coordenadas.salvarCoordenadas(x, y, nome);
		repaint();
	}

	public void adicionarAoComboBox(Vertice novoPonto){
		cBpontoOrigem.addItem(novoPonto);
		cBpontoDestino.addItem(novoPonto);
		cBdefinirEstacionamento.addItem(novoPonto);
		cBdefinirBanco.addItem(novoPonto);
		cBdefinirPontoColeta.addItem(novoPonto);
		cBorigemRemocao.addItem(novoPonto);
		cBdestinoRemocao.addItem(novoPonto);
		
	}
	public void removerDoComboBox(Ponto ponto){
		Vertice aRemover = null;
		try {
			aRemover = controller.recuperarPonto(ponto.getPonto().getText());
			coordenadas.getListaCoordenadas().remove(coordenadas.getListaCoordenadas().indexOf(ponto));
			controller.removerVertice(ponto.getPonto().getText());	
		} catch (verticeInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NoSuchElementException e){
			if(aRemover != null){
				cBpontoOrigem.removeItem(aRemover);
				cBpontoDestino.removeItem(aRemover);
				cBdefinirEstacionamento.removeItem(aRemover);
				cBdefinirBanco.removeItem(aRemover);
				cBdefinirPontoColeta.removeItem(aRemover);
				cBorigemRemocao.removeItem(aRemover);
				cBdestinoRemocao.removeItem(aRemover);
			}
		}
			if(aRemover != null){
			cBpontoOrigem.removeItem(aRemover);
			cBpontoDestino.removeItem(aRemover);
			cBdefinirEstacionamento.removeItem(aRemover);
			cBdefinirBanco.removeItem(aRemover);
			cBdefinirPontoColeta.removeItem(aRemover);
			cBorigemRemocao.removeItem(aRemover);
			cBdestinoRemocao.removeItem(aRemover);
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);

		Iterator<Ponto> iterador = coordenadas.getListaCoordenadas().iterator();
		Ponto atual;
		while(iterador.hasNext()) {
			atual = (Ponto) iterador.next();
			g2d.drawImage(iconePonto.getImage(), atual.getPonto().getX()-20, atual.getPonto().getY()-20, null);
			g2d.drawString(atual.getPonto().getText(),(float)atual.getPonto().getBounds().getCenterX()-atual.getPonto().getText().length()*3,(float)atual.getPonto().getBounds().getY()-25);
			if(!atual.isEstaNaTela()){
				g2d.setBackground(new Color(240,240,240));
				g2d.clearRect((int) atual.getPonto().getBounds().getX()-20, atual.getPonto().getY()-35, iconePonto.getImage().getWidth(this), iconePonto.getImage().getHeight(this)+15);
			}
		}
		for(Linha l : linhas){
			if(l.isParteDoMenorCaminho())
				g2d.setColor(Color.red);
			else
				g2d.setColor(Color.black);
			if(!l.isEstaNaTela()){
				g2d.setColor(new Color(240,240,240,0));
			}
			g2d.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());

		}
	}

	public Ponto encontrarPonto(int x, int y){

		for(Ponto ponto:coordenadas.getListaCoordenadas()){
			if(ponto.getPonto().getBounds().getMinX()-20<=x && ponto.getPonto().getBounds().getMaxX()+8>=x && ponto.getPonto().getBounds().getMinY()-20<=y && ponto.getPonto().getBounds().getMaxY()+10>= y){
				return ponto;
			}
		}
		return null;
	}
	public List<Linha> encontrarLinhasVizinhas(Ponto ponto){

		linhasVizinhas = new ArrayList<>();
		for(Linha linha:linhas){
			if(linha.getX1() == ponto.getPonto().getBounds().getCenterX() || linha.getX2() == ponto.getPonto().getBounds().getCenterX()){
				if(linha.getY1() == ponto.getPonto().getBounds().getCenterY() || linha.getY2()==ponto.getPonto().getBounds().getCenterY()){
					linhasVizinhas.add(linha);
				}
			}
		}
		return linhasVizinhas;
	}
	public void removerLinha(){
		for(Linha linha:linhasVizinhas){
			linha.setEstaNaTela(false);
		}
	}
	public void removerPonto(Ponto ponto){
		ponto.setEstaNaTela(false);
	}
	
	public void atualizarCaminho(){
		
		for(Linha linha:linhas){
			linha.setParteDoMenorCaminho(false);
		}	
	}

	public class gerarCaminhoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Vertice origem = (Vertice) cBpontoOrigem.getSelectedItem();
			Vertice destino = (Vertice) cBpontoDestino.getSelectedItem();
			if(origem.equals(destino)){
				alertas.setText("Operação não permitida");
				return;
			}
			else{
				alertas.setText(null);
			}
						
			for(Aresta aresta:origem.getListaAdj()){
				if(aresta.getDestino().equals(destino)){
					alertas.setText("Aresta já existente");
					return;
				}
			}
			
			int tempo = Integer.parseInt(textTempoPercurso.getText());
			controller.adicionarCaminho(origem, destino, tempo);
			Ponto atual;
			Iterator<Ponto> iterador = coordenadas.getListaCoordenadas().iterator();
			int x1 = 0 ,x2 = 0,y1 = 0,y2 = 0;
			while(iterador.hasNext()) {
				atual = (Ponto) iterador.next();
				if(atual.getPonto().getText().equals(origem.getIndice())) {
					x1 = (int) atual.getPonto().getBounds().getCenterX();
					y1 = (int) atual.getPonto().getBounds().getCenterY();
				}
				else if(atual.getPonto().getText().equals(destino.getIndice())) {
					x2 = (int) atual.getPonto().getBounds().getCenterX();
					y2 = (int) atual.getPonto().getBounds().getCenterY();
				}

			}
			Linha l = new Linha(x1, y1, x2, y2);
			l.setNomePonto1(origem.getIndice());
			l.setNomePonto2(destino.getIndice());
			l.setEstaNaTela(true);
			linhas.add(l);
			repaint();
		}
	}

	public class CalcularMenorCaminhoAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Vertice origem = (Vertice) cBdefinirEstacionamento.getSelectedItem();
			Vertice pontoDeColeta = (Vertice) cBdefinirPontoColeta.getSelectedItem();
			Vertice destino = (Vertice) cBdefinirBanco.getSelectedItem();
			cBmenoresCaminhosPonto.setVisible(false);
			cBmenoresCaminhosBanco.setVisible(false);

			try {
				menoresCaminhosPonto = controller.calcularMenorCaminho(origem.getIndice(), pontoDeColeta.getIndice());
			} catch (verticeInexistenteException e1) {
				e1.printStackTrace();
			}

			if (menoresCaminhosPonto.size() > 1) {
				cBmenoresCaminhosPonto.setVisible(true);
				cBmenoresCaminhosPonto.addItem(menoresCaminhosPonto);
				cBmenoresCaminhosPonto.setSelectedItem(menoresCaminhosPonto.get(0));
			}

			else if(menoresCaminhosPonto.size() == 0)
				JOptionPane.showMessageDialog(null, "Impossível chegar ao Ponto de Coleta a partir do estacionamento!");

			menorCaminho = menoresCaminhosPonto.get(0);

			try {
				menoresCaminhosBanco = controller.calcularMenorCaminho(pontoDeColeta.getIndice(), destino.getIndice());
			} catch (verticeInexistenteException e1) {
				e1.printStackTrace();
			}

			if (menoresCaminhosBanco.size() > 1) {
				cBmenoresCaminhosBanco.setVisible(true);
				cBmenoresCaminhosBanco.addItem(menoresCaminhosBanco);
				cBmenoresCaminhosBanco.setSelectedItem(menoresCaminhosBanco.get(0));
			}

			else if(menoresCaminhosBanco.size() == 0)
				JOptionPane.showMessageDialog(null, "Impossível chegar ao Banco a partir do Ponto de Coleta");

			menorCaminho.addAll(menoresCaminhosBanco.get(0));

			for(Linha l : linhas) {
				for(Vertice v : menorCaminho) {
					int posicaoAtual = 0;
					Vertice[] vetorMenorCaminho = (Vertice[]) menorCaminho.toArray(new Vertice[menorCaminho.size()]);
					while((posicaoAtual<vetorMenorCaminho.length-1)) {
						if(vetorMenorCaminho[posicaoAtual].getIndice().equals(l.getNomePonto1()) && vetorMenorCaminho[posicaoAtual+1].getIndice().equals(l.getNomePonto2()))
							l.setParteDoMenorCaminho(true);
						posicaoAtual++;
					}
				}
			}
			repaint();
		}
	}

	public class ExibirCaminhoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<Vertice> caminho = (List<Vertice>) cBmenoresCaminhosPonto.getSelectedItem();
			List<Vertice> caminho2 = (List<Vertice>) cBmenoresCaminhosBanco.getSelectedItem();
			caminho.addAll(caminho2);

			for(Linha l : linhas)
				l.setParteDoMenorCaminho(false);

			for(Linha l : linhas) {
				for(Vertice v : caminho) {
					int posicaoAtual = 0;
					Vertice[] vetorCaminho = (Vertice[]) caminho.toArray(new Vertice[caminho.size()]);
					while((posicaoAtual<vetorCaminho.length-1)) {
						if(vetorCaminho[posicaoAtual].getIndice().equals(l.getNomePonto1()) && vetorCaminho[posicaoAtual+1].getIndice().equals(l.getNomePonto2()))
							l.setParteDoMenorCaminho(true);
						posicaoAtual++;
					}
				}
			}
			repaint();
		}
	}

	public class RemoverLigacao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			linhasVizinhas = new ArrayList<>();
			Vertice origem = (Vertice) cBorigemRemocao.getSelectedItem();
			Vertice destino = (Vertice)cBdestinoRemocao.getSelectedItem();
			for(Linha linha : linhas){
				if(linha.getNomePonto1().equals(origem.getIndice())|| linha.getNomePonto2().equals(origem.getIndice())){
					if(linha.getNomePonto1().equals(destino.getIndice())|| linha.getNomePonto2().equals(destino.getIndice())){
						linhasVizinhas.add(linha);
					}
				}
			}
			if(linhasVizinhas.size()!=0){
				removerLinha();
				repaint();
			}

		}

	}
	
	
}

