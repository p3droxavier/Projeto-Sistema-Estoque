package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.system.model.Clientes;
import br.com.system.model.ItensVendas;
import javax.swing.JTextArea;

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
	private JTextField txtTotalVendaPagamento;

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
		inicialize();
	}
	
	
	
	private void inicialize() {
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		txtDinheiro.setBounds(83, 24, 120, 20);
		grid_box01.add(txtDinheiro);
		txtDinheiro.setColumns(10);
		
		JLabel lblCartao = new JLabel("Cartão :");
		lblCartao.setBounds(20, 62, 65, 14);
		grid_box01.add(lblCartao);
		lblCartao.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtCartao = new JTextField();
		txtCartao.setBounds(83, 59, 120, 20);
		grid_box01.add(txtCartao);
		txtCartao.setColumns(10);
		
		JLabel lblCheque = new JLabel("Cheque :");
		lblCheque.setBounds(20, 98, 65, 14);
		grid_box01.add(lblCheque);
		lblCheque.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtCheque = new JTextField();
		txtCheque.setBounds(82, 95, 121, 20);
		grid_box01.add(txtCheque);
		txtCheque.setColumns(10);
		
		JLabel lblTroco = new JLabel("Troco :");
		lblTroco.setBounds(20, 134, 55, 14);
		grid_box01.add(lblTroco);
		lblTroco.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtTroco = new JTextField();
		txtTroco.setBounds(83, 131, 120, 20);
		grid_box01.add(txtTroco);
		txtTroco.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(20, 217, 65, 14);
		grid_box01.add(lblTotal);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		
		txtTotalVendaPagamento = new JTextField();
		txtTotalVendaPagamento.setEditable(false);
		txtTotalVendaPagamento.setBounds(83, 205, 120, 40);
		grid_box01.add(txtTotalVendaPagamento);
		txtTotalVendaPagamento.setColumns(10);
		
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
	}
	public JTextField getTxtTotalVendaPagamento() {
		return txtTotalVendaPagamento;
	}
}
