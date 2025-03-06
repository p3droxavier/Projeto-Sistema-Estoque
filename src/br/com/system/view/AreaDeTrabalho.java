//ANOT COD

/*

  EM 'consultProdutos.painel_guias.setSelectedIndex(1);' PROX LINHA 180
  EXISTE UM ERRO INICIAL, POIS AO BUSCAR TAO ELEMENTO ELE NÃO É ACHADO POIS EM SEU ENCAPSULAMENTO ESTA COMO 'PRIVATE'
  
  PROXIMO A LINHA 118...
  -RESOLVENDO ERRO PASSANDO 'AreaDeTrabalho', QUE FAZ REFERENCIA A INSTANCIA ATUAL DA CLASSE AreaDeTrabalho, QUE É UM JFRAME
  -RESOLVENDO O ERRO POIS AreaDeTrabalho  É UM JFrame,COMPATIVEL COM O TIPO ESPERADO NO CONSTRUTOR DE FormsCliente
  -FormsCliente, AGORA, É DEPENDENTE DA AreaDeTrabalho PARA SER INICIALIZADA
  
  -FormsCliente formCliente = new FormsCliente(AreaDeTrabalho.this, true);   'AreaDeTrabalho.this' É PASSADO COMO 'parent(pai)' PARA FormsCliente
  -formCliente.setModal(rootPaneCheckingEnabled);   DEFINE O FormsCliente COMO MODAL, SENDO OBRIGATORIO FECHAR ELE PARA MEXER EM OUTROS COMPONENTES
  -formCliente.setVisible(true);
  
*/


package br.com.system.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AreaDeTrabalho extends javax.swing.JFrame {
	
    public String usuarioLogado;
    public String emailUsuarioLogado;
    public String nivelDeAcessoUser;
    
//    public JMenuItem menu_funcionario;
//    public JMenuItem menu_fornecedores;
//    public JMenuItem menu_controle_estoque;
	
    // INICIALIZADOR DE VERSÃO SERIALIZADA 
    private static final long serialVersionUID = 1L;
    
    private JMenu aba_menu_funcionario;
    private JMenu aba_menu_fornecedores;
    
    private JMenuItem menu_funcionario;
    private JMenuItem menu_fornecedores;
    private JMenuItem menu_controle_estoque;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    AreaDeTrabalho window = new AreaDeTrabalho();
                    
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    

    // INICIALIZAÇÃO
    public AreaDeTrabalho() {
        initialize();
    }
    
    
    

    private void initialize() {
    	setTitle("Área de Trabalho");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    
    	setLocationRelativeTo(null);

        
        
        JMenuBar menuBar =new JMenuBar();
        setJMenuBar(menuBar);
        
        // MENU DE CLIENTES
        JMenu mnNewMenu = new JMenu("Clientes");
        mnNewMenu.setFont(new Font("Arial", Font.BOLD, 14));
        mnNewMenu.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconClientesAreaDeTrabalho24px.png"));
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Formulário de Clientes");
        mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	//CHAMANDO O FORMSCLIENTES NA AREA DE TRABALHO
        	public void actionPerformed(ActionEvent e) {
        		
        		FormsCliente formCliente = new FormsCliente(AreaDeTrabalho.this, true);
        		formCliente.setModal(rootPaneCheckingEnabled);
        		formCliente.setVisible(true);
        		
        	}
        });
        mntmNewMenuItem.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu.add(mntmNewMenuItem);

        
        
        // MENU DE FUNCIONÁRIOS
        aba_menu_funcionario = new JMenu("Funcionários");
        aba_menu_funcionario.setFont(new Font("Arial", Font.BOLD, 14));
        aba_menu_funcionario.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconFuncionariosAreaDeTrabalho24px.png"));
        menuBar.add(aba_menu_funcionario);
        
        menu_funcionario = new JMenuItem("Formulário de Funcionários");
        menu_funcionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu_funcionario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsFuncionarios formFuncionarios = new FormsFuncionarios();
        		formFuncionarios.setVisible(true);
        	}
        });
        menu_funcionario.setFont(new Font("Arial", Font.BOLD, 12));
        aba_menu_funcionario.add(menu_funcionario);

        
        
        // MENU DE FORNECEDORES
        aba_menu_fornecedores = new JMenu("Fornecedores");
        aba_menu_fornecedores.setFont(new Font("Arial", Font.BOLD, 14));
        aba_menu_fornecedores.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\flatIcon_iconFornecedoresAreaDeTrabalho.png"));
        menuBar.add(aba_menu_fornecedores);
        
        menu_fornecedores = new JMenuItem("Formulário de Fornecedores");
        menu_fornecedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu_fornecedores.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsFornecedores formFornecedores = new FormsFornecedores();
        		formFornecedores.setVisible(true);
        	}
        });
        menu_fornecedores.setFont(new Font("Arial", Font.BOLD, 12));
        aba_menu_fornecedores.add(menu_fornecedores);

        
        
        // MENU DE PRODUTOS
        JMenu mnNewMenu_3 = new JMenu("Produtos");
        mnNewMenu_3.setFont(new Font("Arial", Font.BOLD, 14));
        mnNewMenu_3.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconProdutosAreaDeTrabalho24px.png"));
        menuBar.add(mnNewMenu_3);
        
        JMenu mnNewMenu_7 = new JMenu("Meus Produtos");
        mnNewMenu_7.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_3.add(mnNewMenu_7);
        
        menu_controle_estoque = new JMenuItem("Controle de Estoque");
        menu_controle_estoque.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsEstoque controlEstoque = new FormsEstoque();
        		controlEstoque.setVisible(true);
        	}
        });
        
        menu_controle_estoque.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_7.add(menu_controle_estoque);
        
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Consulta de Produtos");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsProdutos consultProdutos = new FormsProdutos();
        		consultProdutos.painel_guias_tab.setSelectedIndex(1);
        		consultProdutos.setVisible(true);
        	}
        });
        mntmNewMenuItem_4.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_7.add(mntmNewMenuItem_4);
        
        JMenuItem mntmNewMenuItem_10 = new JMenuItem("Formulário de Produtos");
        mntmNewMenuItem_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsProdutos formProdutos = new FormsProdutos();
        		formProdutos.setVisible(true);
        	}
        });
        mntmNewMenuItem_10.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_7.add(mntmNewMenuItem_10);
        

        
        // MENU DE VENDAS
        JMenu mnNewMenu_4 = new JMenu("Vendas");
        mnNewMenu_4.setFont(new Font("Arial", Font.BOLD, 14));
        mnNewMenu_4.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconVendasAreaDeTrabalho24px.png"));
        menuBar.add(mnNewMenu_4);
        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir PDV");
        mntmNewMenuItem_5.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_4.add(mntmNewMenuItem_5);
        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Posição do Dia");
        mntmNewMenuItem_6.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_4.add(mntmNewMenuItem_6);
        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Histórico de Vendas");
        mntmNewMenuItem_7.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_4.add(mntmNewMenuItem_7);

        
        // MENU DE CONSFIGURAÇÕES
        JMenu mnNewMenu_5 = new JMenu("Configurações");
        mnNewMenu_5.setFont(new Font("Arial", Font.BOLD, 14));
        mnNewMenu_5.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconConfiguraçõesAreaDeTrabalho20px.png"));
        menuBar.add(mnNewMenu_5);
        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Trocar de Usuário");
        mntmNewMenuItem_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormsLogin login = new FormsLogin();
        		
        		//NÃO USA O 'THIS.DISPOSE()', POIS IRA SE REFERIR AOPROPRIO 'ACTION LISTENER'
        		dispose();
        		login.setVisible(true);
        	}
        });
        mntmNewMenuItem_8.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_5.add(mntmNewMenuItem_8);

        
        // MENU SAIR
        JMenu mnNewMenu_6 = new JMenu("Sair");
        mnNewMenu_6.setFont(new Font("Arial", Font.BOLD, 14));
        mnNewMenu_6.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconSairAreaDeTrabalho20px.png"));
        menuBar.add(mnNewMenu_6);
        JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sair do Sistema");
        mntmNewMenuItem_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//MOSTRA UMA JANELA DE CONFIRMAÇÃO, 'SIM' 'NÃO' 'CANCELAR'
        		//ONDE 'SIM' É IGUAL A POSIÇÃO '0', 'NÃO' É IGUAL A '1', E 'CANCELAR' É IGUAL A '2'.
        		int janela = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do sistema?");
        		if(janela==0) {
        			System.exit(0);
        		}else if(janela==2) {
        			JOptionPane.showMessageDialog(null, "Saída Cancelada! ");
        		}
        	}
        });
        mntmNewMenuItem_9.setFont(new Font("Arial", Font.BOLD, 12));
        mnNewMenu_6.add(mntmNewMenuItem_9);
        
        
        //RETIRA O AVISO 'SERIAL'
        @SuppressWarnings("serial")
		JDesktopPane painel_desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                ImageIcon icon = new ImageIcon("C:/Users/Usuario/git/repository/Sistema_Estoque/src/imgBackgroundSystem/wallpaperBackGroundWindows.jpg");
                Image image = icon.getImage();
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); //DESENHANDO A IMAGEM DE FUNDO
                } else {
                    System.out.println("Erro ao carregar a imagem! Caminho incorreto?");
                }
            }
        };
        getContentPane().add(painel_desktop, BorderLayout.CENTER);
        painel_desktop.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new java.awt.Dimension(906, 80)); 
        painel_desktop.setLayout(new BorderLayout());
        painel_desktop.add(panel, BorderLayout.SOUTH);
        panel.setLayout(null);
        
        JLabel lblTitleStatus = new JLabel("Informações do usuário");
        lblTitleStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitleStatus.setBounds(10, 11, 166, 14);
        panel.add(lblTitleStatus);
        
        JLabel lblTitleNome = new JLabel("Nome :");
        lblTitleNome.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitleNome.setBounds(20, 31, 49, 14);
        panel.add(lblTitleNome);
        
        JLabel lblTitleEmail = new JLabel("E-mail :");
        lblTitleEmail.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitleEmail.setBounds(20, 55, 49, 14);
        panel.add(lblTitleEmail);
        
        JLabel lblUserLogado = new JLabel("");
        lblUserLogado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblUserLogado.setBounds(69, 31, 165, 14);
        panel.add(lblUserLogado);
        
        JLabel lblEmailUserLogado = new JLabel("");
        lblEmailUserLogado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEmailUserLogado.setBounds(69, 55, 165, 14);
        panel.add(lblEmailUserLogado);
        
        JLabel lblTitleNivelAcesso = new JLabel("Nível de acesso atual :");
        lblTitleNivelAcesso.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitleNivelAcesso.setBounds(274, 31, 140, 14);
        panel.add(lblTitleNivelAcesso);
        
        JLabel lblNivelDeAcessoUser = new JLabel("");
        lblNivelDeAcessoUser.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNivelDeAcessoUser.setBounds(409, 31, 165, 14);
        panel.add(lblNivelDeAcessoUser);

        
        
        //MAXIMIZA A JANELA AO SER ATIVADA.
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                
                lblUserLogado.setText(usuarioLogado);
                lblEmailUserLogado.setText(emailUsuarioLogado);
                lblNivelDeAcessoUser.setText(nivelDeAcessoUser);
            }
        });
              
    }    

    public JMenu getAbaMenuFuncionarios() {
		return aba_menu_funcionario;
	}
	
	public JMenu getAbaMenuFornecedores() {
		return aba_menu_fornecedores;
	}
	
	public JMenuItem getMenu_controle_estoque() {
		return menu_controle_estoque;
	}
}
