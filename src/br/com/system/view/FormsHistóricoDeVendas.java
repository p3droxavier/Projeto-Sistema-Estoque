//ANOT COD
/*
 * DIFERENTEMENTE DAS OUTRAS TELAS AO CLICAR NA TABELA E PEGAR AS INFORMAÇÕES
 * - NESSE CASO SERA MANDADO DE UMA TELA A OUTRA
 * */

package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import br.com.system.dao.ItensVendasDao;
import br.com.system.dao.VendasDao;
import br.com.system.model.ItensVendas;
import br.com.system.model.Vendas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		initialize();
	}
	
	
	
	public void initialize() {
		setTitle("Histórico de Vendas");
		setBounds(100, 100, 808, 457);
		container = new JPanel();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		  tabela.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		//QUANDO CLICADO REDIRECIONA PARA 'FormsDetalhesVenda'
		  		FormsDetalhesVenda fdv = new FormsDetalhesVenda(FormsHistóricoDeVendas.this, true);
		  		fdv.txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
		  		fdv.txtCliente.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
		  		fdv.txtDataDaVenda.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
		  		fdv.txtTotal.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
		  		fdv.txtObs.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
		  		
		  		/*
		  		 * E QUANDO REDIRECIONADO CARREGA AS INFORMAÇÕES DA COMPRA
		  		 * - A QUANTIDADE DE PRODUTOS COMPRADOS, ETC
		  		 * 
		  		 * */
		  		
		  		//CRIAÇÃO DE UMA VARIAVEL INTEIRA QUE RECEBE O CODIGO DO PRODUTO
		  		int venda_id = Integer.valueOf(fdv.txtCodigo.getText());
		  		ItensVendasDao dao = new ItensVendasDao();
		  		
		  		//USA O 'VENDA_ID'(CODIGO DA VENDA) COMO PARAMETRO
		  		List<ItensVendas>lista = dao.ListarItens(venda_id);
		  		DefaultTableModel dados = (DefaultTableModel) fdv.tabela.getModel();
		  		for(ItensVendas i:lista) {
		  			dados.addRow(new Object[] {
		  					i.getProdutos().getId(),
		  					i.getProdutos().getDescricao(),
		  					i.getQtd(),
		  					i.getProdutos().getPreco(),
		  					i.getSubtotal()
		  			});
		  		}
		  		 
		  		fdv.setVisible(true);
		  		
		  	}
		  });
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
	     	btnPesquisar.addActionListener(new ActionListener() {
	     		public void actionPerformed(ActionEvent e) {
	     			
	     			try {
	     				//REMOVENDO ESPAÇOS EM BRANCO DA STRING
	     				String dataInicioStr = txtDataInicio.getText().trim(); //pra que serve o trim
	     				String dataFimStr = txtDataFinal.getText().trim();
	     				
	     				//VERIFICAÇÃO DE DATAS VAZIAS
	     				if(dataInicioStr.isEmpty() || dataFimStr.isEmpty()) {
	     					JOptionPane.showMessageDialog(null, "Preencha as datas corretamente. ");
	     					return;
	     				}
	     				
	     				//CONVERTENDO DATA PARA O LOCAL DATE
	     				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		     			LocalDate data_inicio = LocalDate.parse(txtDataInicio.getText(), formato);
		     			LocalDate data_fim = LocalDate.parse(txtDataFinal.getText(), formato);
		     			
		     			//CONSULTA VENDAS COM INTERVALO DE DATAS
		     			VendasDao vd = new VendasDao();
		     			List<Vendas>lista = vd.HistoricoDeVendas(data_inicio, data_fim);
		     			DefaultTableModel historico = (DefaultTableModel) tabela.getModel();
		     			historico.setNumRows(0);
		     			
		     			for(Vendas v : lista) {
		     				historico.addRow(new Object[] {
		     					v.getId(),
		     					v.getClientes().getNome(),
		     					v.getData_venda(),
		     					v.getTotal_venda(),
		     					v.getObservacoes()
		     				});
		     			}
						
					} catch (Exception e2) {
						 JOptionPane.showMessageDialog(null, "Erro ao processar as datas. Verifique o formato.");
				            e2.printStackTrace();
					}
	     			
	     		}
	     	});
	     	btnPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
	     	btnPesquisar.setBounds(419, 75, 257, 34);
	     	container.add(btnPesquisar);
	     	container.revalidate();
	     	container.repaint();
	}
}
