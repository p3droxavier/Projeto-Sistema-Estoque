package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.system.dao.VendasDao;
import br.com.system.dao.ItensVendasDao;
import br.com.system.dao.ProdutosDao;

import br.com.system.model.Clientes;
import br.com.system.model.ItensVendas;
import br.com.system.model.Produtos;
import br.com.system.model.Vendas;

public class FormsPagamento extends JFrame {
	
	ItensVendas obj = new ItensVendas();
	Clientes clientes = new Clientes();
	
	//ARMAZENA OS CONTEÚDOS DA TABELA
	DefaultTableModel meus_produtos;

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtDinheiro;
	private JTextField txtCartao;
	private JTextField txtCheque;
	private JTextField txtTroco;
	private JTextField txtTotalVenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FormsPagamento window = new FormsPagamento();
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
	public FormsPagamento() {
		setTitle("Pagamentos");
		inicialize();
		
		//SETANDO A INICIALIZAÇÃO DOS CAMPOS COMO 0
		txtDinheiro.setText("0");
		txtCartao.setText("0");
		txtCheque.setText("0");
	}
	
	
	
	private void inicialize() {
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 425);
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_title = new JPanel();
		panel_title.setBackground(new Color(0, 0, 0));
		panel_title.setBounds(0, 0, 459, 81);
		contentPane.add(panel_title);
		panel_title.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Campo de Pagamento");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 458, 81);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel_title.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPanel grid_box01 = new JPanel();
		grid_box01.setBounds(0, 81, 226, 305);
		contentPane.add(grid_box01);
		grid_box01.setLayout(null);
		
		JLabel lblDinheiro = new JLabel("Dinheiro :");
		lblDinheiro.setBounds(20, 27, 65, 14);
		grid_box01.add(lblDinheiro);
		lblDinheiro.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtDinheiro = new JTextField();
		txtDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
		txtDinheiro.setFont(new Font("Arial", Font.BOLD, 11));
		txtDinheiro.setBounds(83, 24, 120, 20);
		grid_box01.add(txtDinheiro);
		txtDinheiro.setColumns(10);
		
		JLabel lblCartao = new JLabel("Cartão :");
		lblCartao.setBounds(20, 62, 65, 14);
		grid_box01.add(lblCartao);
		lblCartao.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtCartao = new JTextField();
		txtCartao.setHorizontalAlignment(SwingConstants.CENTER);
		txtCartao.setFont(new Font("Arial", Font.BOLD, 11));
		txtCartao.setBounds(83, 59, 120, 20);
		grid_box01.add(txtCartao);
		txtCartao.setColumns(10);
		
		JLabel lblCheque = new JLabel("Cheque :");
		lblCheque.setBounds(20, 98, 65, 14);
		grid_box01.add(lblCheque);
		lblCheque.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtCheque = new JTextField();
		txtCheque.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheque.setFont(new Font("Arial", Font.BOLD, 11));
		txtCheque.setBounds(82, 95, 121, 20);
		grid_box01.add(txtCheque);
		txtCheque.setColumns(10);
		
		JLabel lblTroco = new JLabel("Troco :");
		lblTroco.setBounds(20, 134, 55, 14);
		grid_box01.add(lblTroco);
		lblTroco.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtTroco = new JTextField();
		txtTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtTroco.setFont(new Font("Arial", Font.BOLD, 11));
		txtTroco.setBounds(83, 131, 120, 20);
		grid_box01.add(txtTroco);
		txtTroco.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(20, 217, 65, 14);
		grid_box01.add(lblTotal);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		
		txtTotalVenda = new JTextField();
		txtTotalVenda.setEnabled(false);
		txtTotalVenda.setFont(new Font("Arial", Font.BOLD, 16));
		txtTotalVenda.setBounds(83, 205, 120, 40);
		grid_box01.add(txtTotalVenda);
		txtTotalVenda.setColumns(10);
		
		JPanel grid_box02 = new JPanel();
		grid_box02.setLayout(null);
		grid_box02.setBounds(233, 81, 226, 305);
		contentPane.add(grid_box02);
		
		JLabel lblObs = new JLabel("Observações :");
		lblObs.setFont(new Font("Arial", Font.BOLD, 12));
		lblObs.setBounds(23, 27, 89, 14);
		grid_box02.add(lblObs);
		
		JTextArea txtObs = new JTextArea();
		txtObs.setColumns(10);
		txtObs.setRows(10);
		txtObs.setBorder(UIManager.getBorder("ComboBox.border"));
		txtObs.setBounds(23, 52, 180, 97);
		grid_box02.add(txtObs);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//OBTENDO VALORES DOS CAMPOS
				double dinheiro = Double.valueOf(txtDinheiro.getText());
				double cartao = Double.valueOf(txtCartao.getText());
				double cheque = Double.valueOf(txtCheque.getText());
				double totalVenda = Double.valueOf(txtTotalVenda.getText());
				
				double totalPago = dinheiro+cartao+cheque;
				double troco = totalPago-totalVenda;
				
				//APOS SUBTRAIR OS VALORES, ADICIONA O TROCO
				txtTroco.setText(String.valueOf(troco));
				
				
				if(totalPago>=totalVenda) {
					Vendas v = new Vendas();
					v.setClientes(clientes);
					
					//SALVANDO A DATA DA VENDA E FORMATANDO A DATA PARA O FORMATO AMERICANO
					Date dataAgora = new Date();
					SimpleDateFormat dataEUA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dataMySql = dataEUA.format(dataAgora);
					
					v.setData_venda(dataMySql); 
					v.setTotal_venda(totalVenda);
					v.setObservacoes(txtObs.getText());
					
					VendasDao vd = new VendasDao();
					vd.Salvar(v);
					v.setId(vd.RetornoDoIdVenda());
					
					JOptionPane.showMessageDialog(null, "Id da ultima venda! " + v.getId());
					
					//S=SALVANDO ITENS DO CARRINHO
					for(int i=0; i<meus_produtos.getRowCount(); i++) {
						int qtd_estoque, qtd_comprada, qtd_atualizada;
						
						Produtos p = new Produtos();
						ProdutosDao pd = new ProdutosDao();
						ItensVendas item = new ItensVendas();
						
						//1° SALVAR ITEM DA VENDA 
						item.setVendas(v);
						
						//2° SALVAR O ID DO PRODUTO
						p.setId(Integer.valueOf(meus_produtos.getValueAt(i, 0).toString()));
						item.setProdutos(p);
						
						//3° SALVAR QUANTIDADE(QTD)
						item.setQtd(Integer.valueOf(meus_produtos.getValueAt(i, 2).toString()));
						
						//4° SALVAR SUBTOTAL
						item.setSubtotal(Double.valueOf(meus_produtos.getValueAt(i, 4).toString()));
						qtd_estoque = pd.RetornarQtdAtualEstoque(p.getId());
						
						//'qtd_comprada' RECEBE O VALOR QUE ESTA SEMDO COMPRADO NO CARRINHO
						qtd_comprada = Integer.valueOf(meus_produtos.getValueAt(i, 2).toString());
						qtd_atualizada = qtd_estoque - qtd_comprada;
						pd.baixaEstoque(p. getId(), qtd_atualizada);
						
						ItensVendasDao ivd = new ItensVendasDao();
						ivd.Salvar(item);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Não foi possivel realizar a venda. O valor dado é menor que o exigido! ");
				}
				
				
			}
		});
		btnPagar.setFont(new Font("Arial", Font.BOLD, 14));
		btnPagar.setBounds(23, 204, 180, 43);
		grid_box02.add(btnPagar);
	}
	public JTextField getTxtTotalVendaPagamento() {
		return txtTotalVenda;
	}
	
	public JTextField getTextTroco() {
		return txtTroco;
	}
}
