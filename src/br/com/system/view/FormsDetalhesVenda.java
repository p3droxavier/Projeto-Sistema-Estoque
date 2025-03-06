package br.com.system.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class FormsDetalhesVenda extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtCodigo;
	public JFormattedTextField txtDataVenda;
	public JTextField txtTotal;
	public JTextField txtCliente;
	public JTextArea txtObservacoes;
	public JTable tabela;
	JTextArea txtObs;
	JFormattedTextField txtDataDaVenda;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					FormsDetalhesVenda window = new FormsDetalhesVenda();
//					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public FormsDetalhesVenda(java.awt.Frame parent, boolean modal){
		super(parent, modal);
		inicialize();
	}
	
	
	
	public void inicialize() {
		setTitle("Detalhes da Venda");
		setBounds(100, 100, 789, 505);
		contentPane = new JPanel();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 294, 473);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Detalhes da Venda");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 294, 466);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPanel grid_box01 = new JPanel();
		grid_box01.setBackground(new Color(255, 255, 255));
		grid_box01.setBounds(294, 0, 480, 287);
		contentPane.add(grid_box01);
		grid_box01.setLayout(null);
		
		JLabel lblDadsDoProduto = new JLabel("Dados do Produto :");
		lblDadsDoProduto.setFont(new Font("Arial", Font.BOLD, 14));
		lblDadsDoProduto.setBounds(20, 11, 143, 14);
		grid_box01.add(lblDadsDoProduto);
		
		JLabel lblCodigo = new JLabel("Código :");
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
		lblCodigo.setBounds(32, 38, 47, 14);
		grid_box01.add(lblCodigo);
		
		JLabel lblCliente = new JLabel("Cliente :");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 12));
		lblCliente.setBounds(32, 66, 65, 14);
		grid_box01.add(lblCliente);
		
		JLabel lblDataVenda = new JLabel("Data da Venda :");
		lblDataVenda.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataVenda.setBounds(147, 38, 100, 14);
		grid_box01.add(lblDataVenda);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 12));
		lblTotal.setBounds(337, 38, 41, 14);
		grid_box01.add(lblTotal);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(87, 35, 47, 20);
		grid_box01.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(378, 35, 77, 20);
		grid_box01.add(txtTotal);
		
		txtCliente = new JTextField();
		txtCliente.setColumns(10);
		txtCliente.setBounds(87, 63, 368, 20);
		grid_box01.add(txtCliente);
		
		
		txtDataDaVenda = new JFormattedTextField();
		txtDataDaVenda.setBounds(246, 35, 74, 20);
		try {
			MaskFormatter mask = new MaskFormatter("##/##/20##");
			mask.setValidCharacters("0123456789");
			txtDataDaVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		grid_box01.add(txtDataDaVenda);
		
		
		txtObs = new JTextArea();
		Font fontObs = new Font("Arial", Font.BOLD, 12);

		TitledBorder titledBorderObs = new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Observações",
		    TitledBorder.LEADING,
		    TitledBorder.TOP,
		    null,
		    new Color(0, 0, 0)
		);
		titledBorderObs.setTitleFont(fontObs);
		txtObs.setBorder(titledBorderObs);
		txtObs.setBounds(32, 105, 423, 123);
		grid_box01.add(txtObs);

		

		
		JButton btnImprimirVia = new JButton("Imprimir 2° Via");
		btnImprimirVia.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnImprimir18px.png"));
		btnImprimirVia.setFont(new Font("Arial", Font.BOLD, 12));
		btnImprimirVia.setBounds(32, 239, 423, 35);
		grid_box01.add(btnImprimirVia);
		
		JPanel grid_box02 = new JPanel();
		grid_box02.setBackground(new Color(255, 255, 255));
		grid_box02.setBounds(294, 295, 481, 178);
		contentPane.add(grid_box02);
		
		
		 tabela = new JTable();
	      tabela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
	      tabela.setFont(new Font("Arial", Font.PLAIN, 12));

	        // MODELO TABELA 
	        DefaultTableModel modelTable1new = new DefaultTableModel(
	            	new Object[][] {
	            	},
	            	new String[] {
	            		"Código", "Produto", "QTD", "Preço", "Subtotal"
	            	}
	            ); 
	        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));//AJUSTANDO A FONTED O HEADER
	        tabela.setModel(modelTable1new);
	        
	        TableColumnModel columnModel = tabela.getColumnModel();
	     	columnModel.getColumn(0).setPreferredWidth(50);  // CODIGO
	     	columnModel.getColumn(1).setPreferredWidth(150); // PRODUTO
	     	columnModel.getColumn(2).setPreferredWidth(50); // QTD
	     	columnModel.getColumn(3).setPreferredWidth(70); // PREÇO
	     	columnModel.getColumn(4).setPreferredWidth(70); // SUBTOTAL
	     	
	     	
	     	
	        // DEFININDO A FONTE PARA O TÍTULO DA BORDA
	     	Font font = new Font("Arial", Font.BOLD, 14);
	     	TitledBorder titledBorder = new TitledBorder("Destalhes da Venda");
	     	titledBorder.setTitleFont(font); 
	     	titledBorder.setTitleJustification(TitledBorder.LEADING); 
	     	titledBorder.setTitlePosition(TitledBorder.TOP); 
	     	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	     	JScrollPane scrollConsulta = new JScrollPane(tabela);
	     	scrollConsulta.setBorder(titledBorder); 
	     	grid_box02.setLayout(null);
	     	scrollConsulta.setBounds(31, 11, 423, 155);
	     	grid_box02.add(scrollConsulta);
	}
	
	
	
	public JTextArea getTxtObservacoes() {
		return txtObs;
	}
	public JFormattedTextField getTxtDataVenda() {
		return txtDataDaVenda;
	}
	public JTextField getTxtCliente() {
		return txtCliente;
	}
	public JTextField getTxtTotal() {
		return txtTotal;
	}
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
	
	
}
