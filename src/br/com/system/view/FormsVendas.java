package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;


public class FormsVendas extends javax.swing.JFrame {
	
    // INICIALIZADOR DE VERSÃO SERIALIZADA
    private static final long serialVersionUID = 1L;
    
    private JTextField textNomeCliente;
    private JTextField textEncontreProdutoAqui;
    private JTable tabela_de_produtos;
    private JTable tabela_carrinho_compra;
    private JTextField textCodigoProduto;
    private JTextField textNomeProduto;
    private JTextField textPrecoProduto;
    private JTextField textEstoque;
    private JTextField textQtdProduto;
    private JTextField textTotalVenda;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FormsVendas window = new FormsVendas();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // INICIALIZAÇÃO
    public FormsVendas() {
    	getContentPane().setBackground(SystemColor.controlHighlight);
        initialize();
    }

    private void initialize() {
        setTitle("Ponto de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 1300, 749);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        //HEADER
        JPanel painel_header = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, painel_header, 0, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, painel_header, 0, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, painel_header, 136, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, painel_header, 0, SpringLayout.EAST, getContentPane());
        painel_header.setBackground(new Color(0, 0, 0));
        getContentPane().add(painel_header);

        GridBagLayout gbl_painel_header = new GridBagLayout();
        gbl_painel_header.columnWeights = new double[]{0.0};
        painel_header.setLayout(gbl_painel_header);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        gbc.weightx = 1;  
        gbc.weighty = 1; 
        gbc.anchor = GridBagConstraints.CENTER; // CENTRALIZANDO O LABEL
        gbc.fill = GridBagConstraints.BOTH;     // PREENCHE O ESPACO

        JLabel lblNewLabel = new JLabel("Ponto de Vendas", SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        painel_header.add(lblNewLabel, gbc);
        
        JPanel grid_box01 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, grid_box01, 1, SpringLayout.SOUTH, painel_header);
        springLayout.putConstraint(SpringLayout.WEST, grid_box01, 0, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, grid_box01, -250, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, grid_box01, 707, SpringLayout.WEST, getContentPane());
        grid_box01.setBackground(Color.WHITE);
        getContentPane().add(grid_box01);
        grid_box01.setLayout(null);
        
        JLabel lblDadosDoCliente = new JLabel("Dados do Cliente ");
        lblDadosDoCliente.setFont(new Font("Arial", Font.BOLD, 14));
        lblDadosDoCliente.setBounds(46, 11, 128, 14);
        grid_box01.add(lblDadosDoCliente);
        
        JLabel lblNomeCliente = new JLabel("Nome :");
        lblNomeCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblNomeCliente.setBounds(61, 38, 46, 14);
        grid_box01.add(lblNomeCliente);
        
        textNomeCliente = new JTextField();
        textNomeCliente.setBounds(108, 36, 331, 20);
        grid_box01.add(textNomeCliente);
        textNomeCliente.setColumns(10);
        
        JLabel lblCpfCliente = new JLabel("CPF :");
        lblCpfCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblCpfCliente.setBounds(60, 68, 46, 14);
        grid_box01.add(lblCpfCliente);
        
        JFormattedTextField txtCpfCliente = new JFormattedTextField();
        txtCpfCliente.setBounds(107, 64, 110, 20);
        try {
        	MaskFormatter mask = new MaskFormatter("###.###.### / ##");
        	mask.setValidCharacters("0123456789");
        	txtCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
        grid_box01.add(txtCpfCliente);
        
        
        
        JLabel lblDataNascCliente = new JLabel("Data de Nascimento :");
        lblDataNascCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblDataNascCliente.setBounds(237, 68, 124, 14);
        grid_box01.add(lblDataNascCliente);
        
        JFormattedTextField txtDataNascCliente = new JFormattedTextField();
        txtDataNascCliente.setBounds(365, 65, 74, 20);
        try {
			MaskFormatter mask = new MaskFormatter("## / ## / ####");
			mask.setValidCharacters("0123456789");
			txtDataNascCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //VERIFICAÇÃO DE MÊS~
//        txtDataNascCliente.addFocusListener(new FocusAdapter() {
//        	@Override
//        	public void focusLost(FocusEvent e) {
//        		String data = txtDataNascCliente.getText();
//        		
//        		if(data.length() >= 5) {
//        			String mes = data.substring(3,5);
//        			
//        			try {
//						int mesInt = Integer.parseInt(mes);
//						if(mesInt < 1 || mesInt >12) {
//							JOptionPane.showMessageDialog(null, "Data invalida! Forneça um mês válida entre 01 e 12. ");
//							txtDataNascCliente.setText("");
//						}
//					} catch (Exception e2) {
//						JOptionPane.showMessageDialog(null, "Data invalida. Forneça uma data válida! ");
//						txtDataNascCliente.setText("");
//					}
//        		}
//        	}
//        });
        
        grid_box01.add(txtDataNascCliente);
        
        
        
        JLabel lblEncontreProdutoAqui = new JLabel("Encontre o produto por aqui :");
        lblEncontreProdutoAqui.setFont(new Font("Arial", Font.BOLD, 12));
        lblEncontreProdutoAqui.setBounds(61, 106, 168, 14);
        grid_box01.add(lblEncontreProdutoAqui);
        
        textEncontreProdutoAqui = new JTextField();
        textEncontreProdutoAqui.setColumns(10);
        textEncontreProdutoAqui.setBounds(237, 103, 202, 20);
        grid_box01.add(textEncontreProdutoAqui);
        
        JButton btnPesquisarClientes = new JButton("Pesquisar");
        btnPesquisarClientes.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonVendasProcurar18px.png"));
        btnPesquisarClientes.setFont(new Font("Arial", Font.BOLD, 12));
        btnPesquisarClientes.setBounds(518, 34, 119, 25);
        grid_box01.add(btnPesquisarClientes);
        
        
        tabela_de_produtos = new JTable();
        tabela_de_produtos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
        tabela_de_produtos.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela_de_produtos.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Descrição", "Preço", "Qtd Estoque", "Fornecedor"
        	}
        ));
        tabela_de_produtos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabela_de_produtos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela_de_produtos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela_de_produtos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela_de_produtos.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabela_de_produtos.setBounds(69, 144, 600, 182);
        grid_box01.add(tabela_de_produtos);
        
        Font font = new Font("Arial", Font.BOLD, 14);
     	TitledBorder titledBorder = new TitledBorder("Produtos Cadastrados");
     	titledBorder.setTitleFont(font); 
     	titledBorder.setTitleJustification(TitledBorder.LEADING); 
     	titledBorder.setTitlePosition(TitledBorder.TOP); 
     	tabela_de_produtos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     	JScrollPane scrollConsulta = new JScrollPane(tabela_de_produtos);
     	scrollConsulta.setBorder(titledBorder); 
     	grid_box01.setLayout(null);
     	scrollConsulta.setBounds(59, 135, 578, 179);
     	grid_box01.add(scrollConsulta);
     	
     	
     	
     	JPanel grid_box03 = new JPanel();
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box03, 10, SpringLayout.SOUTH, grid_box01);
     	springLayout.putConstraint(SpringLayout.SOUTH, grid_box03, 0, SpringLayout.SOUTH, getContentPane());
     	springLayout.putConstraint(SpringLayout.EAST, grid_box03, 707, SpringLayout.WEST, getContentPane());
     	springLayout.putConstraint(SpringLayout.WEST, grid_box03, 0, SpringLayout.WEST, getContentPane());
     	grid_box03.setBackground(Color.WHITE);
     	getContentPane().add(grid_box03);
     	grid_box03.setLayout(null);
     	
     	JLabel lblDadosDoProduto = new JLabel("Dados do Produto");
     	lblDadosDoProduto.setBounds(44, 11, 140, 17);
     	lblDadosDoProduto.setFont(new Font("Arial", Font.BOLD, 14));
     	grid_box03.add(lblDadosDoProduto);
     	
     	JLabel lblCodigoProduto = new JLabel("Código :");
     	lblCodigoProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblCodigoProduto.setBounds(59, 39, 46, 14);
     	grid_box03.add(lblCodigoProduto);
     	
     	textCodigoProduto = new JTextField();
     	textCodigoProduto.setBounds(115, 36, 60, 20);
     	grid_box03.add(textCodigoProduto);
     	textCodigoProduto.setColumns(10);
     	
     	JLabel lblNomeProduto = new JLabel("Produto :");
     	lblNomeProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblNomeProduto.setBounds(59, 68, 66, 14);
     	grid_box03.add(lblNomeProduto);
     	
     	textNomeProduto = new JTextField();
     	textNomeProduto.setColumns(10);
     	textNomeProduto.setBounds(115, 65, 182, 20);
     	grid_box03.add(textNomeProduto);
     	
     	JLabel lblPrecoProduto = new JLabel("Preço :");
     	lblPrecoProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblPrecoProduto.setBounds(59, 97, 46, 14);
     	grid_box03.add(lblPrecoProduto);
     	
     	textPrecoProduto = new JTextField();
     	textPrecoProduto.setColumns(10);
     	textPrecoProduto.setBounds(115, 95, 116, 20);
     	grid_box03.add(textPrecoProduto);
     	
     	textEstoque = new JTextField();
     	textEstoque.setColumns(10);
     	textEstoque.setBounds(310, 95, 116, 20);
     	grid_box03.add(textEstoque);
     	
     	JLabel lblEstoque = new JLabel("Estoque :");
     	lblEstoque.setFont(new Font("Arial", Font.BOLD, 12));
     	lblEstoque.setBounds(253, 97, 58, 14);
     	grid_box03.add(lblEstoque);
     	
     	JButton btnPesquisarProduto = new JButton("Pesquisar");
     	btnPesquisarProduto.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonVendasProcurar18px.png"));
     	btnPesquisarProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	btnPesquisarProduto.setBounds(337, 62, 296, 25);
     	grid_box03.add(btnPesquisarProduto);
     	
     	JLabel lblQtdProduto = new JLabel("Preço :");
     	lblQtdProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblQtdProduto.setBounds(447, 97, 46, 14);
     	grid_box03.add(lblQtdProduto);
     	
     	textQtdProduto = new JTextField();
     	textQtdProduto.setColumns(10);
     	textQtdProduto.setBounds(503, 95, 130, 20);
     	grid_box03.add(textQtdProduto);
     	
     	JLabel lblNewLabel_1_1_2 = new JLabel("Desconsto em % :");
     	lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 12));
     	lblNewLabel_1_1_2.setBounds(393, 39, 100, 14);
     	grid_box03.add(lblNewLabel_1_1_2);
     	
     	JFormattedTextField formattedTextField_2 = new JFormattedTextField();
     	formattedTextField_2.setBounds(503, 36, 130, 20);
     	grid_box03.add(formattedTextField_2);
     	
     	JButton btnAdicionarProduto = new JButton("Adicionar Item");
     	btnAdicionarProduto.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
     	btnAdicionarProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	btnAdicionarProduto.setBounds(59, 139, 574, 25);
     	grid_box03.add(btnAdicionarProduto);
     	
     	JPanel grid_box02 = new JPanel();
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box02, 0, SpringLayout.SOUTH, painel_header);
     	springLayout.putConstraint(SpringLayout.WEST, grid_box02, 10, SpringLayout.EAST, grid_box01);
     	springLayout.putConstraint(SpringLayout.SOUTH, grid_box02, -202, SpringLayout.SOUTH, getContentPane());
     	springLayout.putConstraint(SpringLayout.EAST, grid_box02, 0, SpringLayout.EAST, getContentPane());
     	grid_box02.setBackground(new Color(255, 255, 255));
     	getContentPane().add(grid_box02);
     	grid_box02.setLayout(null);
     	
     	JLabel lblCarrinhoDeCompras = new JLabel("Carrinho de Compras");
     	lblCarrinhoDeCompras.setBounds(24, 11, 157, 17);
     	lblCarrinhoDeCompras.setFont(new Font("Arial", Font.BOLD, 14));
     	grid_box02.add(lblCarrinhoDeCompras);
     	
     	
     	
     	tabela_carrinho_compra = new JTable();
     	tabela_carrinho_compra.setBounds(71, 85, 449, 182);
     	grid_box02.add(tabela_carrinho_compra);
     	tabela_carrinho_compra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
     	tabela_carrinho_compra.setFont(new Font("Arial", Font.PLAIN, 12));

     	DefaultTableModel modelTableNew = new DefaultTableModel(
     	        new Object[][] {},
     	        new String[] {
     	            "ID", "Produto", "Quantidade", "Preço", "Subtotal"
     	        }
     	);

     	tabela_carrinho_compra.setModel(modelTableNew);
     	tabela_carrinho_compra.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

     	TableColumnModel columnModel = tabela_carrinho_compra.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(50);  
     	columnModel.getColumn(1).setPreferredWidth(120); 
     	columnModel.getColumn(2).setPreferredWidth(60);  
     	columnModel.getColumn(3).setPreferredWidth(80);  
     	columnModel.getColumn(4).setPreferredWidth(80);  


     	Font fontTabelaCarrinho = new Font("Arial", Font.BOLD, 14);
     	TitledBorder titledBorderTabelaCarrinho = new TitledBorder("Produtos Adicionados");
     	titledBorderTabelaCarrinho.setTitleFont(fontTabelaCarrinho); 
     	titledBorderTabelaCarrinho.setTitleJustification(TitledBorder.LEADING); 
     	titledBorderTabelaCarrinho.setTitlePosition(TitledBorder.TOP);
     	tabela_carrinho_compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     	JScrollPane scrollTabelaCarrinho = new JScrollPane(tabela_carrinho_compra);
     	scrollTabelaCarrinho.setBorder(titledBorderTabelaCarrinho);
     	grid_box02.setLayout(null);
     	scrollTabelaCarrinho.setBounds(49, 39, 445, 278);
     	grid_box02.add(scrollTabelaCarrinho);
     	
     	JPanel grid_box04 = new JPanel();
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box04, 508, SpringLayout.NORTH, getContentPane());
     	springLayout.putConstraint(SpringLayout.WEST, grid_box04, 10, SpringLayout.EAST, grid_box03);
     	springLayout.putConstraint(SpringLayout.EAST, grid_box04, 0, SpringLayout.EAST, getContentPane());
     	grid_box04.setBackground(new Color(255, 255, 255));
     	springLayout.putConstraint(SpringLayout.SOUTH, grid_box04, 228, SpringLayout.SOUTH, grid_box02);
     	getContentPane().add(grid_box04);
     	grid_box04.setLayout(null);
     	
     	JLabel lblTotalVenda = new JLabel("Total da Venda");
     	lblTotalVenda.setBounds(140, 40, 149, 17);
     	lblTotalVenda.setFont(new Font("Arial", Font.BOLD, 20));
     	grid_box04.add(lblTotalVenda);
     	
     	textTotalVenda = new JTextField();
     	textTotalVenda.setFont(new Font("Arial", Font.PLAIN, 12));
     	textTotalVenda.setBounds(299, 36, 107, 30);
     	grid_box04.add(textTotalVenda);
     	textTotalVenda.setColumns(10);
     	
     	JButton btnPagamento = new JButton("Pagamento");
     	btnPagamento.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconForrmaDePagamento30px.png"));
     	btnPagamento.setFont(new Font("Arial", Font.BOLD, 18));
     	btnPagamento.setBounds(49, 81, 212, 54);
     	grid_box04.add(btnPagamento);
     	
     	JButton btnCalcelar = new JButton("Cancelar");
     	btnCalcelar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconCancelarPagamento30px.png"));
     	btnCalcelar.setFont(new Font("Arial", Font.BOLD, 18));
     	btnCalcelar.setBounds(282, 81, 212, 54);
     	grid_box04.add(btnCalcelar);
     	grid_box02.revalidate();
     	grid_box02.repaint();

     	
     	
        
    }
}
