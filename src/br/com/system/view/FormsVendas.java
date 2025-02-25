//ANOT COD
/*
 * telaPag.getTxtTotalVendaPagamento().setText(String.valueOf(total)); PERTO DE LINHA 650
 * -AO PEGAR 'TxtTotalVendaPagamento()' DA UM ERRO POIS ELE COMPONENTE NO 'FormsPagamento é 'PRIVETE'
 * -DESSA FORMA FAZ UM METODO PARA PEGAR ESSE ELEMENTO, PEGANDO O ELEMENTO AQUI COM O GET 'getTxtTotalVendaPagamento()' 
 * -LOGO APÓS É PRECIS ALTERAR O SEU VALOR PARA 'STRING' USANDO 'String.valueOf(total)'
 * 
 * 
 * PROXIMO A LINHA 663
 * - Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
 * - 'SwingUtilities.getWindowAncestor((Component)' BUSCA O COMPONENTE ANTIGO MAIS PROXIMO QUE SEJA UMA JANELA
 *      E QUARDA EM 'window';
 * */

package br.com.system.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.system.dao.ClientesDao;
import br.com.system.dao.ProdutosDao;
import br.com.system.model.Clientes;
import br.com.system.model.Produtos;
import br.com.system.utilitarios.Utilitarios;


public class FormsVendas extends javax.swing.JFrame {
	
	Clientes obj = new Clientes();
    double preco, subtotal, total;
    int quantidade;
    DefaultTableModel meus_produtos;

	
    // INICIALIZADOR DE VERSÃO SERIALIZADA
    private static final long serialVersionUID = 1L;
    
    private JTextField txtNomeCliente;
    private JTextField txtEncontreProdutoAqui;
    private JTable tabela_de_produtos;
    private JTable tabela_carrinho_compra;
    private JTextField txtCodigoProduto;
    private JTextField txtNomeProduto;
    private JTextField txtPrecoProduto;
    private JTextField txtEstoque;
    private JTextField txtQtdProduto;
    public JTextField txtTotalVenda;
    private  JFormattedTextField txtData;

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
    
    
    
    public void listar() {
    	ProdutosDao dao = new ProdutosDao();
    	List<Produtos> lista = dao.Listar();
    	DefaultTableModel dados = (DefaultTableModel) tabela_de_produtos.getModel(); //CONVERTIDO PARA A TABELA 'DEFAULTTABLEMODEL'
    	dados.setNumRows(0); //0 IGUAL A POSIÇÃO INICIAL DA MATRIZ
    	for(Produtos p : lista) {
    		dados.addRow(new Object[]{
    			p.getId(),
    			p.getDescricao(),
    			p.getPreco(),
    			p.getQtd_estoque(),
    			p.getFornecedores().getNome()
    			//PODE-SE PEGAR QUALQUER ATRIBUTO DO FORNECEDOR.
    		});
    	}
    }
    

    
    // INICIALIZAÇÃO
    public FormsVendas() {
        initialize();
    }

    
    private void initialize() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowActivated(WindowEvent e) {
    			Date dataAtual = new Date();
    			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
    			String dataFormatada = formato.format(dataAtual);
    			txtData.setText(dataFormatada);
    		}
    	});
    	
    	
    	 addWindowListener(new WindowAdapter() {
         	@Override
         	public void windowActivated(WindowEvent e) {
         		listar();
         	}
         });
    	 
        setTitle("Ponto de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 1300, 749);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        getContentPane().setBackground(SystemColor.controlHighlight);
        
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
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        painel_header.add(lblNewLabel, gbc);
        
        JPanel grid_box01 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, grid_box01, 1, SpringLayout.SOUTH, painel_header);
        springLayout.putConstraint(SpringLayout.WEST, grid_box01, 0, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, grid_box01, -250, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, grid_box01, 682, SpringLayout.WEST, getContentPane());
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
        
        txtNomeCliente = new JTextField();
        txtNomeCliente.setBounds(108, 36, 309, 20);
        grid_box01.add(txtNomeCliente);
        txtNomeCliente.setColumns(10);
        
        JLabel lblCpfCliente = new JLabel("CPF :");
        lblCpfCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblCpfCliente.setBounds(60, 68, 46, 14);
        grid_box01.add(lblCpfCliente);
        
        JFormattedTextField txtCpfCliente = new JFormattedTextField();
        txtCpfCliente.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            		String cpf = txtCpfCliente.getText();

                	ClientesDao dao = new ClientesDao();
               		
               		obj = dao.BuscarClienteCPF(cpf);
               		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
               		if(obj.getCpf() != null) {
               			txtNomeCliente.setText(obj.getNome());
               		} else {
               			JOptionPane.showMessageDialog(null, "ERRO: Cpf invalido! ");
               		}
           			
           		}
       	}
        });
        txtCpfCliente.setBounds(107, 64, 110, 20);
        try {
        	MaskFormatter mask = new MaskFormatter("###.###.### / ##");
        	mask.setValidCharacters("0123456789");
        	txtCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
        grid_box01.add(txtCpfCliente);
        
        
        
        JLabel lblData = new JLabel("Data :");
        lblData.setFont(new Font("Arial", Font.BOLD, 12));
        lblData.setBounds(467, 68, 40, 14);
        grid_box01.add(lblData);
        
        txtData = new JFormattedTextField();
        txtData.setEnabled(false);
        txtData.setBounds(505, 64, 128, 20);
//        try {
//			MaskFormatter mask = new MaskFormatter("## / ## / ####");
//			mask.setValidCharacters("0123456789");
//			txtDataNascCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
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
        
        grid_box01.add(txtData);
        
        
        
        JLabel lblEncontreProdutoAqui = new JLabel("Encontre o produto por aqui :");
        lblEncontreProdutoAqui.setFont(new Font("Arial", Font.BOLD, 12));
        lblEncontreProdutoAqui.setBounds(61, 106, 168, 14);
        grid_box01.add(lblEncontreProdutoAqui);
        
        txtEncontreProdutoAqui = new JTextField();
        txtEncontreProdutoAqui.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		String filtNome = "%"+txtEncontreProdutoAqui.getText()+"%";
        		ProdutosDao dao = new ProdutosDao();
            	List<Produtos> lista = dao.Filtrar(filtNome);
            	DefaultTableModel dados = (DefaultTableModel) tabela_de_produtos.getModel(); //CONVERTIDO PARA A TABELA 'DEFAULTTABLEMODEL'
            	dados.setNumRows(0); //0 IGUAL A POSIÇÃO INICIAL DA MATRIZ
            	for(Produtos p : lista) {
            		dados.addRow(new Object[]{
            			p.getId(),
            			p.getDescricao(),
            			p.getPreco(),
            			p.getQtd_estoque(),
            		});
            		txtCodigoProduto.setEnabled(false);
            	}
        	}
        });
        txtEncontreProdutoAqui.setColumns(10);
        txtEncontreProdutoAqui.setBounds(237, 103, 396, 20);
        grid_box01.add(txtEncontreProdutoAqui);
        
        JButton btnPesquisarClientes = new JButton("Pesquisar");
        btnPesquisarClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String cpf = txtCpfCliente.getText();

            	ClientesDao dao = new ClientesDao();
           		
           		obj = dao.BuscarClienteCPF(cpf);
           		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
           		if(obj.getCpf() != null) {
           			txtNomeCliente.setText(obj.getNome());
           		} else {
           			JOptionPane.showMessageDialog(null, "ERRO: Cpf invalido! ");
           		}
        	}
        });
        btnPesquisarClientes.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonVendasProcurar18px.png"));
        btnPesquisarClientes.setFont(new Font("Arial", Font.BOLD, 12));
        btnPesquisarClientes.setBounds(286, 63, 131, 25);
        grid_box01.add(btnPesquisarClientes);
        
        
        tabela_de_produtos = new JTable();
        tabela_de_produtos.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		txtCodigoProduto.setText(tabela_de_produtos.getValueAt(tabela_de_produtos.getSelectedRow(), 0). toString());
        		txtNomeProduto.setText(tabela_de_produtos.getValueAt(tabela_de_produtos.getSelectedRow(), 1). toString());
        		txtPrecoProduto.setText(tabela_de_produtos.getValueAt(tabela_de_produtos.getSelectedRow(), 2). toString());
        		txtEstoque.setText(tabela_de_produtos.getValueAt(tabela_de_produtos.getSelectedRow(), 3). toString());
        		
        		txtCodigoProduto.setEnabled(false);
        	}
        });
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
        tabela_de_produtos.getColumnModel().getColumn(4).setPreferredWidth(185);
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
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box03, 6, SpringLayout.SOUTH, grid_box01);
     	springLayout.putConstraint(SpringLayout.SOUTH, grid_box03, 0, SpringLayout.SOUTH, getContentPane());
     	springLayout.putConstraint(SpringLayout.EAST, grid_box03, 682, SpringLayout.WEST, getContentPane());
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
     	
     	txtCodigoProduto = new JTextField();
     	txtCodigoProduto.addKeyListener(new KeyAdapter() {
     		@Override
     		public void keyPressed(KeyEvent e) {
     			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			int id = Integer.valueOf(txtCodigoProduto.getText());
            		Produtos obj = new Produtos();
            		ProdutosDao dao = new ProdutosDao();
            	
            		obj = dao.BuscarProdutosID(id);
            		//SE O OBJETO FOR DIFERENTE DE 0 É POR QUE TEM ALGO
            		if(obj.getId() != 0) {
            			txtCodigoProduto.setText(String.valueOf(obj.getId())); 
            			txtNomeProduto.setText(obj.getDescricao());
            			txtPrecoProduto.setText(String.valueOf(obj.getPreco()));
            			txtEstoque.setText(String.valueOf(obj.getQtd_estoque()));
            			
            			//APÓS O TERMINO DO METODO DESATIVA O CAMPO CÓDIGO
            			txtCodigoProduto.setEnabled(false);
            		} else {
            			JOptionPane.showMessageDialog(null, "ERRO: Código invalido! ");
            		}
        			
        		}	
     		}
     	});
     	txtCodigoProduto.setBounds(115, 36, 60, 20);
     	grid_box03.add(txtCodigoProduto);
     	txtCodigoProduto.setColumns(10);
     	
     	JLabel lblNomeProduto = new JLabel("Produto :");
     	lblNomeProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblNomeProduto.setBounds(59, 68, 66, 14);
     	grid_box03.add(lblNomeProduto);
     	
     	txtNomeProduto = new JTextField();
     	txtNomeProduto.setEnabled(false);
     	txtNomeProduto.setColumns(10);
     	txtNomeProduto.setBounds(115, 65, 182, 20);
     	grid_box03.add(txtNomeProduto);
     	
     	JLabel lblPrecoProduto = new JLabel("Preço :");
     	lblPrecoProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblPrecoProduto.setBounds(59, 97, 46, 14);
     	grid_box03.add(lblPrecoProduto);
     	
     	txtPrecoProduto = new JTextField();
     	txtPrecoProduto.setEnabled(false);
     	txtPrecoProduto.setColumns(10);
     	txtPrecoProduto.setBounds(115, 95, 116, 20);
     	grid_box03.add(txtPrecoProduto);
     	
     	txtEstoque = new JTextField();
     	txtEstoque.setEnabled(false);
     	txtEstoque.setColumns(10);
     	txtEstoque.setBounds(353, 95, 60, 20);
     	grid_box03.add(txtEstoque);
     	
     	JLabel lblEstoque = new JLabel("Estoque :");
     	lblEstoque.setFont(new Font("Arial", Font.BOLD, 12));
     	lblEstoque.setBounds(296, 97, 58, 14);
     	grid_box03.add(lblEstoque);
     	
     	JButton btnPesquisarProduto = new JButton("Pesquisar");
     	btnPesquisarProduto.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			int id = Integer.valueOf(txtCodigoProduto.getText());
        		Produtos obj = new Produtos();
        		ProdutosDao dao = new ProdutosDao();
        	
        		obj = dao.BuscarProdutosID(id);
        		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
        		if(obj.getId() != 0) {
        			txtCodigoProduto.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
        			txtNomeProduto.setText(obj.getDescricao());
        			txtPrecoProduto.setText(String.valueOf(obj.getPreco()));
        			txtEstoque.setText(String.valueOf(obj.getQtd_estoque()));
        			
        			//APÓS O TÉRMINO DO METODO DESATIVA O CAMPO CÓDIGO
        			txtCodigoProduto.setEnabled(false);
        		} else {
        			JOptionPane.showMessageDialog(null, "ERRO: Código invalido! ");
        		}
     		}
     	});
     	btnPesquisarProduto.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonVendasProcurar18px.png"));
     	btnPesquisarProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	btnPesquisarProduto.setBounds(353, 62, 280, 25);
     	grid_box03.add(btnPesquisarProduto);
     	
     	JLabel lblQtdProduto = new JLabel("Quantidade:");
     	lblQtdProduto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblQtdProduto.setBounds(427, 97, 79, 14);
     	grid_box03.add(lblQtdProduto);
     	
     	txtQtdProduto = new JTextField();
     	txtQtdProduto.setText("1");
     	txtQtdProduto.setColumns(10);
     	txtQtdProduto.setBounds(503, 95, 130, 20);
     	grid_box03.add(txtQtdProduto);
     	
     	JLabel lblDesconto = new JLabel("Desconsto em % :");
     	lblDesconto.setFont(new Font("Arial", Font.BOLD, 12));
     	lblDesconto.setBounds(393, 39, 100, 14);
     	grid_box03.add(lblDesconto);
     	
     	JFormattedTextField txtDesconto = new JFormattedTextField();
     	txtDesconto.setBounds(503, 36, 130, 20);
     	grid_box03.add(txtDesconto);
     	
     	JButton btnAdicionarProduto = new JButton("Adicionar Item");
     	btnAdicionarProduto.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			String nome = txtNomeProduto.getText();
     			Produtos obj = new Produtos();
     			ProdutosDao daop = new ProdutosDao();
     			//OBTEM O OBJETO PRODUTO PELO NOME
     			obj = daop.BuscarProdutos(nome);
     			
     			if(obj.getDescricao() != null) {
     				int estoque = Integer.valueOf(txtEstoque.getText());
     				int quantidade = Integer.valueOf(txtQtdProduto.getText());
     				
     				preco = Double.valueOf(txtPrecoProduto.getText());
     				quantidade = Integer.valueOf(txtQtdProduto.getText());
     				subtotal = preco*quantidade;
     				total+=subtotal;
     				
     				if(estoque>=quantidade) {
     					txtTotalVenda.setText(String.valueOf(total));
     					meus_produtos = (DefaultTableModel)tabela_carrinho_compra.getModel();
     					meus_produtos.addRow(new Object[] {
     							txtCodigoProduto.getText(),
     							txtNomeProduto.getText(),
     							txtQtdProduto.getText(),
     							txtPrecoProduto.getText(),
     							subtotal//PELO FATO DE NÃO TER CAMPO, NÃO USA-SE 'GETTEXT()'
     							
     					});
     				} else {
     					JOptionPane.showMessageDialog(null, "A quantidade desejada é maior do que a quantidade do estoque! ");
     				}
     				
     			}else {
     				JOptionPane.showMessageDialog(null, "Não foi possivel adicionar ao carrinho. \n Faltam informações.");
     			}
     		}
     	});
     	btnAdicionarProduto.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
     	btnAdicionarProduto.setFont(new Font("Arial", Font.BOLD, 18));
     	btnAdicionarProduto.setBounds(59, 180, 574, 42);
     	grid_box03.add(btnAdicionarProduto);
     	
     	JPanel grid_box02 = new JPanel();
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box02, 0, SpringLayout.SOUTH, painel_header);
     	springLayout.putConstraint(SpringLayout.WEST, grid_box02, 6, SpringLayout.EAST, grid_box01);
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
     	scrollTabelaCarrinho.setBounds(49, 39, 491, 278);
     	grid_box02.add(scrollTabelaCarrinho);
     	
     	JPanel grid_box04 = new JPanel();
     	springLayout.putConstraint(SpringLayout.NORTH, grid_box04, 0, SpringLayout.SOUTH, grid_box02);
     	springLayout.putConstraint(SpringLayout.WEST, grid_box04, 6, SpringLayout.EAST, grid_box03);
     	
     	JButton btnLimparDadosProdutos = new JButton("Limpar Campos");
     	btnLimparDadosProdutos.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconVassoura26px.png"));
     	btnLimparDadosProdutos.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			Utilitarios util = new Utilitarios();
     			util.LimpaTela(grid_box03);
     			
     			txtCodigoProduto.setEnabled(true);
     			txtCodigoProduto.requestFocus();
     			
     		}
     	});
     	btnLimparDadosProdutos.setFont(new Font("Arial", Font.BOLD, 16));
     	btnLimparDadosProdutos.setBounds(59, 139, 574, 36);
     	grid_box03.add(btnLimparDadosProdutos);
     	springLayout.putConstraint(SpringLayout.SOUTH, grid_box04, 26, SpringLayout.SOUTH, getContentPane());
     	springLayout.putConstraint(SpringLayout.EAST, grid_box04, 0, SpringLayout.EAST, getContentPane());
     	grid_box04.setBackground(new Color(255, 255, 255));
     	getContentPane().add(grid_box04);
     	grid_box04.setLayout(null);
     	 
     	JLabel lblTotalVenda = new JLabel("Total da Venda");
     	lblTotalVenda.setBounds(187, 40, 149, 17);
     	lblTotalVenda.setFont(new Font("Arial", Font.BOLD, 20));
     	grid_box04.add(lblTotalVenda);
     	
     	txtTotalVenda = new JTextField();
     	txtTotalVenda.setEditable(false);
     	txtTotalVenda.setFont(new Font("Arial", Font.BOLD, 16));
     	txtTotalVenda.setBounds(346, 36, 107, 30);
     	grid_box04.add(txtTotalVenda);
     	txtTotalVenda.setColumns(10);
     	
     	JButton btnPagamento = new JButton("Pagamento");
     	btnPagamento.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			String nome = txtNomeCliente.getText();
     			String cpf = txtCpfCliente.getText(); 
     			
     			//VERIFICA SE OS CAMPOS NOME E EMAIL ESTÃO VAZIOS
     			if (nome.isEmpty() || cpf.isEmpty()) {
     	            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente, incluindo o NOME e CPF.");
     	            return; 
     	        }
     			
     			obj = new Clientes();
     			ClientesDao daoc = new ClientesDao();
     			
     			//PEGA O OBJETO DE CLIENTE POR INTEIRO;
     			obj = daoc.BuscarCliente(nome);
     			obj = daoc.BuscarClienteCPF(cpf);
     			
     			if(obj.getNome() != null && obj.getCpf() != null) {
     				//OBTENDO AS VARIAVEIS GLOBAIS DE 'FormsPagamento'
     				FormsPagamento telaPag = new FormsPagamento();
     				telaPag.clientes = obj;
     				telaPag.meus_produtos = meus_produtos; //se der erro veja essa parte
     				
     				//OBTENDO O TOTAL DA VENDA DE 'FormsVendas'
     				telaPag.getTxtTotalVendaPagamento().setText(String.valueOf(total));
     				//MOSTRA A TELA DE PAGAMENTO
     				telaPag.setVisible(true);
     				
     				//FECHA A TELA DE VENDAS AO ABRIR A DE PAGAMENTO.
     				Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
     				if(window != null) {
     					window.dispose();//FECHA A JANELA 
     				}
     				
     			} else {
     				JOptionPane.showMessageDialog(null, "Verifique se você preecheu todos os campos corretamente! \n Incluindo o NOME e CPF");
     			}
     		}
     	});
     	btnPagamento.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconForrmaDePagamento30px.png"));
     	btnPagamento.setFont(new Font("Arial", Font.BOLD, 18));
     	btnPagamento.setBounds(75, 81, 212, 54);
     	grid_box04.add(btnPagamento);
     	
     	JButton btnCalcelar = new JButton("Cancelar");
     	btnCalcelar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconCancelarPagamento30px.png"));
     	btnCalcelar.setFont(new Font("Arial", Font.BOLD, 18));
     	btnCalcelar.setBounds(310, 81, 212, 54);
     	grid_box04.add(btnCalcelar);
     	grid_box02.revalidate();
     	grid_box02.repaint();
   
    }
}
