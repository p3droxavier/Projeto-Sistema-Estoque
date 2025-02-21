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


//import javax.swing.*;
import javax.swing.text.*;
//import java.awt.*;
//import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.border.TitledBorder;
//import javax.swing.event.AncestorEvent;
//import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
//import javax.swing.text.MaskFormatter;
//
//import br.com.system.dao.FornecedoresDao;
import br.com.system.dao.ProdutosDao;
import br.com.system.model.Fornecedores;
import br.com.system.model.Produtos;
import br.com.system.utilitarios.Utilitarios;
import javax.swing.JFormattedTextField;




public class FormsEstoque extends javax.swing.JFrame{
	int idProduto, qtd_atualizada;
    /**
	 * 
	 */
	//INICIALIZADOR DE VERSÃO SERIALIZADA 
	private static final long serialVersionUID = 1L;
	
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtQtd_Atual;

    private JTable tabela;
    private JButton btnAdicionar;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FormsEstoque window = new FormsEstoque();
                    
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
    public FormsEstoque() {
        initialize();
    }
    

    private void initialize() {
       
        setTitle("Formulário Controle de Estoque");
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowActivated(WindowEvent e) {
        		listar();
        	}
        });
        
        setResizable(false);
        setBounds(100, 100, 808, 504);
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
        JLabel lblNewLabel = new JLabel("Controle de Estoque");
        lblNewLabel.setBounds(0, 0, 320, 426);
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
        JPanel painel_do_estoque = new JPanel();
        painel_do_estoque.setBackground(new Color(255, 255, 255));
        painel_guias_tab.addTab("Dados do Produto", null, painel_do_estoque, null);
        painel_guias_tab.setBackgroundAt(0, Color.WHITE);
        painel_guias_tab.setEnabledAt(0, true);
        
        JLabel lblCodigo = new JLabel("Codigo : ");
        lblCodigo.setBounds(20, 39, 56, 14);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(77, 36, 70, 20);
        txtCodigo.setColumns(10);
        
        JLabel lblDescricao = new JLabel("Descrição : ");
        lblDescricao.setBounds(20, 68, 88, 14);
        lblDescricao.setFont(new Font("Arial", Font.BOLD, 12));
        
        
        JLabel lblQtd_atual = new JLabel("Quantidade Atual :");
        lblQtd_atual.setBounds(20, 102, 116, 14);
        lblQtd_atual.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtQtd_Atual = new JTextField();
        txtQtd_Atual.setBounds(129, 100, 45, 20);
        txtQtd_Atual.setFont(new Font("Arial", Font.PLAIN, 11));
        txtQtd_Atual.setColumns(10);
        
		
		
		JLabel lblQuantidade = new JLabel("Quantidade :");
		lblQuantidade.setBounds(196, 103, 88, 14);
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 12));
		
		JFormattedTextField txtQtd_nova = new JFormattedTextField();
        txtQtd_nova.setBounds(277, 100, 45, 20);
        
        // CRIANDO O FILTRO PARA ACEITAR APENAS NUMEROS
        ((AbstractDocument) txtQtd_nova.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*")) { // Aceita apenas números
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*")) { // Aceita apenas números
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        painel_do_estoque.add(txtQtd_nova);
		
	
		
		JLabel LabelIdentificação = new JLabel("Identificação");
		LabelIdentificação.setBounds(10, 11, 108, 14);
		LabelIdentificação.setFont(new Font("Arial", Font.BOLD, 14));
		
		


	       //AÇÃO DO BOTÃO PESQUISAR PRODUTO
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nome = txtDescricao.getText();
        		Produtos obj = new Produtos();
        		ProdutosDao dao = new ProdutosDao();
        		
        		obj = dao.BuscarProdutos(nome);
        		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
        		if(obj.getDescricao() != null) {
        			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
        			txtDescricao.setText(obj.getDescricao());
        			txtQtd_Atual.setText(String.valueOf(obj.getPreco()));
        			txtQtd_nova.setText(String.valueOf(obj.getQtd_estoque()));
        			

        		} else {
        			JOptionPane.showMessageDialog(null, "ERRO: Produto não encontradao! \nVerifique se não a erros ao digitar o nome do produto.");
        		}
        	}
        });
       
        btnPesquisar.setBounds(341, 64, 116, 27);
        btnPesquisar.setBackground(Color.WHITE);
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 11));
        


        txtDescricao = new JTextField();
        //BUSCA AS INFOERMAÇÕES DO BANCO AO PRESSIONAR ENTER
        txtDescricao.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			String nome = txtDescricao.getText();
            		Produtos obj = new Produtos();
            		ProdutosDao dao = new ProdutosDao();
            		
            		obj = dao.BuscarProdutos(nome);
            		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
            		if(obj.getDescricao() != null) {
            			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
            			txtDescricao.setText(obj.getDescricao());
            			txtQtd_Atual.setText(String.valueOf(obj.getPreco()));
            			txtQtd_nova.setText(String.valueOf(obj.getQtd_estoque()));
            			

            		} else {
            			JOptionPane.showMessageDialog(null, "ERRO: Produto não encontradao! \nVerifique se não a erros ao digitar o nome do produto.");
            		}
        			
        		}
        	}
        });
        txtDescricao.setBounds(92, 67, 230, 20);
        txtDescricao.setToolTipText("");
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 11));
        txtDescricao.setColumns(10);
		
        
        
        //INCLUSÃO DOS COMPONENTES NO PAINEL
        painel_do_estoque.setLayout(null);
		painel_do_estoque.add(LabelIdentificação);
		painel_do_estoque.add(lblCodigo);
		painel_do_estoque.add(txtCodigo);
		painel_do_estoque.add(lblQuantidade);
		painel_do_estoque.add(btnPesquisar);
		painel_do_estoque.add(lblDescricao);
		painel_do_estoque.add(txtDescricao);
		painel_do_estoque.add(lblQtd_atual);
		painel_do_estoque.add(txtQtd_Atual);
		
		
        tabela = new JTable();
        tabela.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//'idProduto' E 'txtCodigo' AMBOS INICIAM COM 0 
        		idProduto = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());
        		txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());
        		txtDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1). toString());
        		txtQtd_Atual.setText(tabela.getValueAt(tabela.getSelectedRow(), 3). toString());
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
     	
     	
     	
        // DEFININDO A FONTE PARA O TÍTULO DA BORDA
     	Font font = new Font("Arial", Font.BOLD, 14);
     	TitledBorder titledBorder = new TitledBorder("Produtos Cadastrados");
     	titledBorder.setTitleFont(font); 
     	titledBorder.setTitleJustification(TitledBorder.LEADING); 
     	titledBorder.setTitlePosition(TitledBorder.TOP); 
     	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     	JScrollPane scrollConsulta = new JScrollPane(tabela);
     	scrollConsulta.setBorder(titledBorder); 
     	painel_do_estoque.setLayout(null);
     	scrollConsulta.setBounds(10, 135, 447, 253);
     	painel_do_estoque.add(scrollConsulta);
     	painel_do_estoque.revalidate();
     	painel_do_estoque.repaint();

     
     	
     	btnAdicionar = new JButton("Adicionar");
     	btnAdicionar.addActionListener(new ActionListener() {
     	    public void actionPerformed(ActionEvent e) {
     	        try {
     	            int qtdAtual, qtd_nova, qtd_atualizada;
     	            String nome = txtDescricao.getText();  // Pega o nome do produto
     	            Produtos obj = new Produtos();
     	            ProdutosDao daop = new ProdutosDao();
     	            
     	            qtdAtual = Integer.valueOf(txtQtd_Atual.getText());
     	            qtd_nova = Integer.valueOf(txtQtd_nova.getText());
     	            qtd_atualizada = qtdAtual + qtd_nova;
     	        
     	            obj = daop.BuscarProdutos(nome);
     	            txtDescricao.setText(obj.getDescricao());
     	            daop.adicionarEstoque(obj.getId(), qtd_atualizada);
     	            
     	            
     	            
     	            JOptionPane.showMessageDialog(null, "Total de " + obj.getDescricao()+ " após a adição: "  + qtd_atualizada);
     	        } catch (Exception err) {
     	            JOptionPane.showMessageDialog(null, "ERRO! " + err);
     	        }
     	    }
     	});

     	btnAdicionar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
     	btnAdicionar.setFont(new Font("Arial", Font.BOLD, 11));
     	btnAdicionar.setBounds(341, 97, 116, 27);
     	painel_do_estoque.add(btnAdicionar);

     	
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel_3.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panel_3, BorderLayout.SOUTH);
        
        
        //{INICIO DOS BOTÕES FOOTER}
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_estoque);
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
                obj.setPreco(Double.valueOf(txtQtd_Atual.getText())); //CONVERTENDO PARA DOUBLE
            	obj.setQtd_estoque(Integer.valueOf(txtQtd_nova.getText()));
           		
           		ProdutosDao dao = new ProdutosDao();
           		dao.Salvar(obj); 
           		
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_estoque);
           		
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
                obj.setPreco(Double.valueOf(txtQtd_Atual.getText()));
            	obj.setQtd_estoque(Integer.valueOf(txtQtd_nova.getText()));
           		
    			Fornecedores f = new Fornecedores();
    			obj.setFornecedores(f);
    			
           		
           		ProdutosDao daop = new ProdutosDao();
           		daop.Editar(obj);
           		
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_do_estoque);
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
        		util.LimpaTela(painel_do_estoque);
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
