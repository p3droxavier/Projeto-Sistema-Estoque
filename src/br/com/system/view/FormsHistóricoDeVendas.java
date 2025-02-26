package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

public class FormsHistóricoDeVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JTable tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FormsHistóricoDeVendas window = new FormsHistóricoDeVendas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public FormsHistóricoDeVendas() {
		setTitle("Histórico de Vendas");
		initialize();
	}
	
	
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 808, 457);
		container = new JPanel();
		container.setBackground(new Color(255, 255, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(container);
		container.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 305, 426);
		container.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Histórico de Vendas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 305, 415);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblTituloConsultaPorData = new JLabel("Consulta por data : ");
		lblTituloConsultaPorData.setFont(new Font("Arial", Font.BOLD, 14));
		lblTituloConsultaPorData.setBounds(315, 21, 141, 14);
		container.add(lblTituloConsultaPorData);
		
		JLabel lblDataInicio = new JLabel("Data de Inicío :");
		lblDataInicio.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataInicio.setBounds(328, 46, 84, 14);
		container.add(lblDataInicio);
		
		JFormattedTextField txtDataInicio = new JFormattedTextField();
		txtDataInicio.setBounds(419, 43, 128, 20);
		try {
			MaskFormatter mask = new MaskFormatter("##/##/20##");
			mask.setValidCharacters("0123456789");
			txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		container.add(txtDataInicio);
		
		
		
		JFormattedTextField txtDataFinal = new JFormattedTextField();
		txtDataFinal.setBounds(642, 43, 128, 20);
		try {
			MaskFormatter mask = new MaskFormatter("##/##/20##");
			mask.setValidCharacters("0123456789");
			txtDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		container.add(txtDataFinal);
		
		JLabel lblDataFinal = new JLabel("Data Final :");
		lblDataFinal.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataFinal.setBounds(575, 46, 69, 14);
		container.add(lblDataFinal);
		
		
		  tabela = new JTable();
//	        tabela.addMouseListener(new MouseAdapter() {
//	        	@Override
//	        	public void mouseClicked(MouseEvent e) {
//	        		//'idProduto' E 'txtCodigo' AMBOS INICIAM COM 0 
//	        		idProduto = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());
//	        		txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());
//	        		txtDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1). toString());
//	        		txtQtd_Atual.setText(tabela.getValueAt(tabela.getSelectedRow(), 3). toString());
//	        	}
//	        });
	        
	        
	        tabela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
	        tabela.setFont(new Font("Arial", Font.PLAIN, 12));

	        // MODELO TABELA 
	        DefaultTableModel modelTable1new = new DefaultTableModel(
	            	new Object[][] {
	            	},
	            	new String[] {
	            		"Código", "Cliente", "Data da Venda", "Total da Venda", "Observações"
	            	}
	            ); 
	        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));//AJUSTANDO A FONTED O HEADER
	        tabela.setModel(modelTable1new);
	        
	        TableColumnModel columnModel = tabela.getColumnModel();
	     	columnModel.getColumn(0).setPreferredWidth(50);  // CODIGO
	     	columnModel.getColumn(1).setPreferredWidth(150); // CLIENTE
	     	columnModel.getColumn(2).setPreferredWidth(100); // DATA DA VENDA
	     	columnModel.getColumn(3).setPreferredWidth(100); // TOTAL DA VENDA
	     	columnModel.getColumn(4).setPreferredWidth(180); // OBSERVAÇÕES
	     	
	     	
	     	
	        // DEFININDO A FONTE PARA O TÍTULO DA BORDA
	     	Font font = new Font("Arial", Font.BOLD, 14);
	     	TitledBorder titledBorder = new TitledBorder("Histórico de Vendas");
	     	titledBorder.setTitleFont(font); 
	     	titledBorder.setTitleJustification(TitledBorder.LEADING); 
	     	titledBorder.setTitlePosition(TitledBorder.TOP); 
	     	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	     	JScrollPane scrollConsulta = new JScrollPane(tabela);
	     	scrollConsulta.setBorder(titledBorder); 
	     	container.setLayout(null);
	     	scrollConsulta.setBounds(320, 137, 462, 270);
	     	container.add(scrollConsulta);
	     	
	     	JButton btnPesquisar = new JButton("Pesquisar");
	     	btnPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
	     	btnPesquisar.setBounds(419, 75, 257, 34);
	     	container.add(btnPesquisar);
	     	container.revalidate();
	     	container.repaint();
	}
}
