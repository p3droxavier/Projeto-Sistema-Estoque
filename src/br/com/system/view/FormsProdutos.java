//Anot Project
/*
 AJUSTAR O SALVAR PRODUTOS E TERMINAR DE CODIFICAR O LIST DO DAO PARA SALVAR 
*/

package br.com.system.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.BorderFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.system.dao.ClientesDao;
import br.com.system.dao.FornecedoresDao;
import br.com.system.dao.ProdutosDao;
import br.com.system.model.Clientes;
import br.com.system.model.Fornecedores;
import br.com.system.model.Produtos;
import br.com.system.utilitarios.Utilitarios;




public class FormsProdutos extends javax.swing.JFrame{
    /**
	 * 
	 */
	//INICIALIZADOR DE VERSÃO SERIALIZADA 
	private static final long serialVersionUID = 1L;
	
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JTextField txtQtdEstoque;
    private JComboBox<String> cbFornecedor; //ESTADO
    private JTextField txtNomeConsultaCliente;

    private JTable tabela;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FormsProdutos window = new FormsProdutos();
                    
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    //LISTAGEM DE PRODUTOS NA TABELA
    public void listar() {
    	ProdutosDao dao = new ProdutosDao();
    	List<Produtos> lista = dao.Listar();
    	DefaultTableModel dados = (DefaultTableModel) tabela.getModel(); //CONVERTIDO PARA A TABELA 'DEFAULTTABLEMODEL'
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
    

    //INICIALIZAÇÃO
    public FormsProdutos() {
        initialize();
    }
    

    private void initialize() {
       
        setTitle("Formulário de Produtos");
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowActivated(WindowEvent e) {
        		listar();
        	}
        });
        
        setResizable(false);
        setBounds(100, 100, 808, 410);
        // CENTRALIZA A JANELA NA TELA
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 1° ABA DO FORMULÁRIO
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        getContentPane().add(panel, BorderLayout.WEST);
        panel.setPreferredSize(new Dimension(320, 60));
        panel.setLayout(null);

        // TITLE LABEL
        JLabel lblNewLabel = new JLabel("Cadastro de Produtos");
        lblNewLabel.setBounds(0, 0, 320, 332);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblNewLabel);

      
        JTabbedPane painel_guias_tab = new JTabbedPane(JTabbedPane.TOP);
        painel_guias_tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        painel_guias_tab.setFont(new Font("Arial", Font.PLAIN, 11));
        painel_guias_tab.setBackground(Color.GRAY);
        getContentPane().add(painel_guias_tab, BorderLayout.CENTER);
        
        
        //PAINEL 1
        JPanel painel_do_produto = new JPanel();
        painel_do_produto.setBackground(new Color(255, 255, 255));
        painel_guias_tab.addTab("Dados do Produto", null, painel_do_produto, null);
        painel_guias_tab.setBackgroundAt(0, Color.WHITE);
        painel_guias_tab.setEnabledAt(0, true);
        
        JLabel lblCodigo = new JLabel("Codigo: ");
        lblCodigo.setBounds(20, 39, 56, 14);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(66, 36, 70, 20);
        txtCodigo.setColumns(10);
        
        JLabel lblDescricao = new JLabel("Descrição : ");
        lblDescricao.setBounds(20, 68, 88, 14);
        lblDescricao.setFont(new Font("Arial", Font.BOLD, 12));
        
        
 
        
        
        txtDescricao = new JTextField();
        //BUSCA AS INFOERMAÇÕES DO BANCO AO PRESSIONAR ENTER
        txtDescricao.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			String nome = txtDescricao.getText();
            		Clientes obj = new Clientes();
            		ClientesDao dao = new ClientesDao();
            		
            		obj = dao.BuscarCliente(nome);
            		if(obj.getNome() != null) {
            			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
            			txtDescricao.setText(obj.getNome());
            			txtPreco.setText(obj.getEmail());
            			txtQtdEstoque.setText(String.valueOf(obj.getNumero()));
            			cbFornecedor.setSelectedItem(obj.getEstado());	
            		} else {
            			JOptionPane.showMessageDialog(null, "ERRO: Cliente não encontradao! \nVerifique se não a erros ao digitar o nome. ");
            		}
        			
        		}
        	}
        });
        txtDescricao.setBounds(92, 67, 241, 20);
        txtDescricao.setToolTipText("");
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 11));
        txtDescricao.setColumns(10);
        
        JLabel lblPreco = new JLabel("Preço : ");
        lblPreco.setBounds(20, 99, 46, 14);
        lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtPreco = new JTextField();
        txtPreco.setBounds(65, 96, 70, 20);
        txtPreco.setFont(new Font("Arial", Font.PLAIN, 11));
        txtPreco.setColumns(10);
        
		
		
		JLabel lblQtdEstoque = new JLabel("QTD Estoque : ");
		lblQtdEstoque.setBounds(177, 99, 88, 14);
		lblQtdEstoque.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtQtdEstoque = new JTextField();
		txtQtdEstoque.setBounds(263, 96, 70, 20);
		txtQtdEstoque.setColumns(10);
		
		
		JLabel lblNewLabel_3_1 = new JLabel("Identificação");
		lblNewLabel_3_1.setBounds(10, 11, 108, 14);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		

		//PASSANDO FORNECEDORES COMO PARAMETRO PARA ELIMINAR ERRO DE TIPO
		JComboBox<Fornecedores> cbFornecedor = new JComboBox<>(); 
		cbFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FornecedoresDao dao = new FornecedoresDao();
				List<Fornecedores> lista = dao.Listar();
				cbFornecedor.removeAllItems(); //EVITA DUPLICAÇÃO DE DADOS
				for(Fornecedores f : lista) {
					cbFornecedor.addItem(f);
				}
			}
		});
		
	       //AÇÃO DO BOTÃO PESQUISAR PRODUTO
        JButton btnPesquisar = new JButton("pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nome = txtDescricao.getText();
        		Produtos obj = new Produtos();
        		ProdutosDao dao = new ProdutosDao();
        		
        		Fornecedores f = new Fornecedores();
        		FornecedoresDao daof = new FornecedoresDao();
        		
        		obj = dao.BuscarProdutos(nome);
        		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
        		if(obj.getDescricao() != null) {
        			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
        			txtDescricao.setText(obj.getDescricao());
        			txtPreco.setText(String.valueOf(obj.getPreco()));
        			txtQtdEstoque.setText(String.valueOf(obj.getQtd_estoque()));
        			
        			f = daof.BuscarFornecedores(obj.getFornecedores().getNome());
        			cbFornecedor.getModel().setSelectedItem(f);
        		} else {
        			JOptionPane.showMessageDialog(null, "ERRO: Produto não encontradao! \nVerifique se não a erros ao digitar o nome do produto.");
        		}
        	}
        });
       
        btnPesquisar.setBounds(358, 64, 88, 23);
        btnPesquisar.setBackground(Color.WHITE);
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 11));
        
		
		


		cbFornecedor.setBounds(102, 124, 231, 22);
		cbFornecedor.setBackground(Color.WHITE);
		cbFornecedor.setFont(new Font("Arial", Font.PLAIN, 11));
		cbFornecedor.setToolTipText("#");
		painel_do_produto.add(cbFornecedor);
		
        
        
        //INCLUSÃO DOS COMPONENTES NO PAINEL
        painel_do_produto.setLayout(null);
		painel_do_produto.add(lblNewLabel_3_1);
		painel_do_produto.add(lblCodigo);
		painel_do_produto.add(txtCodigo);
		painel_do_produto.add(lblQtdEstoque);
		painel_do_produto.add(btnPesquisar);
		painel_do_produto.add(txtQtdEstoque);
		painel_do_produto.add(cbFornecedor);
		painel_do_produto.add(lblDescricao);
		painel_do_produto.add(txtDescricao);
		painel_do_produto.add(lblPreco);
		painel_do_produto.add(txtPreco); 
		
		JLabel lblFornecedor = new JLabel("Fornecedor :");
		lblFornecedor.setFont(new Font("Arial", Font.BOLD, 12));
		lblFornecedor.setBounds(20, 128, 80, 14);
		painel_do_produto.add(lblFornecedor);
		
		
		
		
		//2° ABA DO FORMULÁRIO
        JPanel painel_consulta_produtos = new JPanel();
        painel_consulta_produtos.setBackground(Color.WHITE);
        painel_guias_tab.addTab("Consulta de Produtos", null, painel_consulta_produtos, null);
        painel_consulta_produtos.setLayout(null);
        
        JLabel lblDescricaoConsultaProduto = new JLabel("Descrição : ");
        lblDescricaoConsultaProduto.setFont(new Font("Arial", Font.BOLD, 12));
        lblDescricaoConsultaProduto.setBounds(9, 13, 67, 14);
        painel_consulta_produtos.add(lblDescricaoConsultaProduto);
        
        txtNomeConsultaCliente = new JTextField();
        txtNomeConsultaCliente.addKeyListener(new KeyAdapter() {
        	@Override
        	//FILTRAGEM DE PRODUTOS
        	public void keyReleased(KeyEvent e) {
        		String filtNome = "%"+txtNomeConsultaCliente.getText()+"%";
        		ClientesDao dao = new ClientesDao();
            	List<Clientes> lista = dao.Filtrar(filtNome);
            	DefaultTableModel dados = (DefaultTableModel) tabela.getModel(); //CONVERTIDO PARA A TABELA 'DEFAULTTABLEMODEL'
            	dados.setNumRows(0); //0 IGUAL A POSIÇÃO INICIAL DA MATRIZ
            	for(Clientes c : lista) {
            		dados.addRow(new Object[]{
            			c.getId(),
            			c.getNome(),
            			c.getRg(),
            			c.getCpf(),
            			c.getEmail(),
            			c.getTelefone(),
            			c.getCelular(),
            			c.getCep(),
            			c.getEndereco(),
            			c.getNumero(),
            			c.getComplemento(),
            			c.getBairro(),
            			c.getCidade(),
            			c.getEstado()	
            		});
            	}
        	}
        });
        
        
        txtNomeConsultaCliente.setToolTipText("");
        txtNomeConsultaCliente.setFont(new Font("Arial", Font.PLAIN, 11));
        txtNomeConsultaCliente.setColumns(10);
        txtNomeConsultaCliente.setBounds(86, 11, 219, 20);
        painel_consulta_produtos.add(txtNomeConsultaCliente);
        //FILTRAGEM DE PRODUTOS AO PRESSIONAR O BOTÃO
        JButton btnPesquisaProdutos = new JButton("pesquisar");
        btnPesquisaProdutos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String filtNome = "%"+txtNomeConsultaCliente.getText()+"%";
        		ClientesDao dao = new ClientesDao();
            	List<Clientes> lista = dao.Filtrar(filtNome);
            	DefaultTableModel dados = (DefaultTableModel) tabela.getModel(); //CONVERTIDO PARA A TABELA 'DEFAULTTABLEMODEL'
            	dados.setNumRows(0); //0 IGUAL A POSIÇÃO INICIAL DA MATRIZ
            	for(Clientes c : lista) {
            		dados.addRow(new Object[]{
            			c.getId(),
            			c.getNome(),
            			c.getRg(),
            			c.getCpf(),
            			c.getEmail(),
            			c.getTelefone(),
            			c.getCelular(),
            			c.getCep(),
            			c.getEndereco(),
            			c.getNumero(),
            			c.getComplemento(),
            			c.getBairro(),
            			c.getCidade(),
            			c.getEstado()	
            		});
            	}
        	}
        });
        btnPesquisaProdutos.setFont(new Font("Arial", Font.BOLD, 11));
        btnPesquisaProdutos.setBackground(Color.WHITE);
        btnPesquisaProdutos.setBounds(369, 9, 88, 23);
        painel_consulta_produtos.add(btnPesquisaProdutos);
        
        
        
       
        
        tabela = new JTable();
        tabela.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		painel_guias_tab.setSelectedIndex(0);
        		txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());
        		txtDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1). toString());
        		txtPreco.setText(tabela.getValueAt(tabela.getSelectedRow(), 2). toString());
        		txtQtdEstoque.setText(tabela.getValueAt(tabela.getSelectedRow(), 3). toString());
        		
        		Fornecedores f = new Fornecedores();
        		FornecedoresDao daof = new FornecedoresDao();
        		f = daof.BuscarFornecedores(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
        		cbFornecedor.removeAllItems();
        		cbFornecedor.getModel().setSelectedItem(f);
        		
        		
        	}
        });
        
        
        tabela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));

        // MODELO TABELA 
        DefaultTableModel modelTable1new = new DefaultTableModel(
            	new Object[][] {
            	},
            	new String[] {
            		"Código", "Descrição", "Preço", "QTD Estoque", "Fornecedores"
            	}
            );
        
        
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));//AJUSTANDO A FONTED O HEADER
        tabela.setModel(modelTable1new);
        
        TableColumnModel columnModel = tabela.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(50);  // ID
     	columnModel.getColumn(1).setPreferredWidth(150); // DESCRIÇÃO
     	columnModel.getColumn(2).setPreferredWidth(100); // PREÇO
     	columnModel.getColumn(3).setPreferredWidth(100); // QTS ESTOQUE
     	columnModel.getColumn(4).setPreferredWidth(180); // FORNECEDOR
     	
     	
     	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	JScrollPane scrollConsultaClientes = new JScrollPane(tabela);
     	scrollConsultaClientes.setBounds(10, 38, 447, 256);
     	painel_consulta_produtos.add(scrollConsultaClientes);
     	
     	
    
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel_3.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panel_3, BorderLayout.SOUTH);
        
        
        //{INICIO DOS BOTÕES FOOTER}
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_produto);
        	}
        });
        btnNovo.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
        btnNovo.setBackground(Color.LIGHT_GRAY);
        btnNovo.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnNovo);
        
        
        JButton btnSalvar = new JButton("Salvar"); 
        btnSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Produtos obj = new Produtos();
            	obj.setDescricao(txtDescricao.getText());
                obj.setPreco(Double.valueOf(txtPreco.getText())); //CONVERTENDO PARA DOUBLE
            	obj.setQtd_estoque(Integer.valueOf(txtQtdEstoque.getText()));
           		obj.setFornecedores((Fornecedores)cbFornecedor.getSelectedItem()); //CONVERTENDO PARA O TIPO FORNECEDORES
           		
           		ProdutosDao dao = new ProdutosDao();
           		dao.Salvar(obj); 
           		
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_produto);
           		
        	}
        });
        
        btnSalvar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnSave18px.png"));
        btnSalvar.setBackground(Color.LIGHT_GRAY);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnSalvar);
        
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Produtos obj = new Produtos();
        		obj.setId(Integer.valueOf(txtCodigo.getText()));
            	obj.setDescricao(txtDescricao.getText());
                obj.setPreco(Double.valueOf(txtPreco.getText()));
            	obj.setQtd_estoque(Integer.valueOf(txtQtdEstoque.getText()));
           		
    			Fornecedores f = new Fornecedores();
    			f = (Fornecedores) cbFornecedor.getSelectedItem();
    			obj.setFornecedores(f);
    			
           		
           		ProdutosDao daop = new ProdutosDao();
           		daop.Editar(obj);
           		
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_produto);
        	}
        });
        
        btnEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnEdit18px.png"));
        btnEditar.setBackground(Color.LIGHT_GRAY);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnEditar);
        
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Produtos obj = new Produtos();
        		obj.setId(Integer.valueOf(txtCodigo.getText()));
        		ProdutosDao dao = new ProdutosDao();
        		dao.Excluir(obj);
        		
        		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_produto);
        	}
        });
        
        btnExcluir.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnDelete18px.png"));
        btnExcluir.setBackground(Color.LIGHT_GRAY);
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnExcluir);
        
        
        JButton btnImprimir = new JButton("Imprimir");
        
        btnImprimir.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnImprimir18px.png"));
        btnImprimir.setBackground(Color.LIGHT_GRAY);
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnImprimir);
        //{FIM DOS BOTÕES}

    }
}
