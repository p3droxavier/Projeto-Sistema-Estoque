//Anot Project
/*
 ->1° ABA DO FORMULARIO (LINHA 138) 
 ->2° ABA DO FORMULARIO (LINHA 455) 
 
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
import java.	awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.system.dao.ClientesDao;
import br.com.system.model.Clientes;
import br.com.system.utilitarios.Utilitarios;




public class FormsCliente extends javax.swing.JDialog{
    /**
	 * 
	 */
	//INICIALIZADOR DE VERSÃO SERIALIZADA 
	private static final long serialVersionUID = 1L;
	
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JFormattedTextField txtRg; //FORMATADO
    private JFormattedTextField txtCpf; //FORMATADO
    private JTextField txtEmail;
    private JFormattedTextField txtTelefone; //FORMATADO
    private JFormattedTextField txtCelular; //FORMATADO
    private JFormattedTextField txtCep; //FORMATADO
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JComboBox<String> cbUf; //ESTADO
    private JTextField txtNomeConsultaCliente;

    private JTable tabela;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                    FormsCliente window = new FormsCliente();
                    
//                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    //LISTAGEM DE USUARIOS NA TABELA
    public void listar() {
    	ClientesDao dao = new ClientesDao();
    	List<Clientes> lista = dao.Listar();
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
    

    //INICIALIZAÇÃO
    public FormsCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initialize();
    }

    

    private void initialize() {
        // Create the main frame only once
        setTitle("Formulário de Clientes");
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowActivated(WindowEvent e) {
        		listar();
        	}
        });
        
        setResizable(false);
        setBounds(100, 100, 875, 492);
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
        JLabel lblNewLabel = new JLabel("Cadastro de Clientes");
        lblNewLabel.setBounds(0, 0, 320, 420);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblNewLabel);

        // CREATE THE JTABLEPANEL
        JTabbedPane painel_guias_tab = new JTabbedPane(JTabbedPane.TOP);
        painel_guias_tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        painel_guias_tab.setFont(new Font("Arial", Font.PLAIN, 11));
        painel_guias_tab.setBackground(Color.GRAY);
        getContentPane().add(painel_guias_tab, BorderLayout.CENTER);
        
        
        //PAINEL 1
        JPanel painel_dados_pessoais = new JPanel();
        painel_dados_pessoais.setBackground(new Color(255, 255, 255));
        painel_guias_tab.addTab("Dados Pessoais", null, painel_dados_pessoais, null);
        painel_guias_tab.setBackgroundAt(0, Color.WHITE);
        painel_guias_tab.setEnabledAt(0, true);
        
        JLabel lblCodigo = new JLabel("Codigo: ");
        lblCodigo.setBounds(20, 39, 56, 14);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(66, 36, 108, 20);
        txtCodigo.setColumns(10);
        
        JLabel lblNome = new JLabel("Nome : ");
        lblNome.setBounds(20, 68, 46, 14);
        lblNome.setFont(new Font("Arial", Font.BOLD, 12));
        
        
        //AÇÃO DO BOTÃO PESQUISAR CLIENTE
        JButton btnPesquisar = new JButton("pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nome = txtNome.getText();
        		Clientes obj = new Clientes();
        		ClientesDao dao = new ClientesDao();
        		
        		obj = dao.BuscarCliente(nome);
        		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
        		if(obj.getNome() != null) {
        			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
        			txtNome.setText(obj.getNome());
        			txtRg.setText(obj.getRg());
        			txtCpf.setText(obj.getCpf());
        			txtEmail.setText(obj.getEmail());
        			txtTelefone.setText(obj.getTelefone());
        			txtCelular.setText(obj.getCelular());
        			txtCep.setText(obj.getCep());
        			txtEndereco.setText(obj.getEndereco());
        			txtNumero.setText(String.valueOf(obj.getNumero()));
        			txtComplemento.setText(obj.getComplemento());
        			txtBairro.setText(obj.getBairro());
        			txtCidade.setText(obj.getCidade());
        			cbUf.setSelectedItem(obj.getEstado());	
        		} else {
        			JOptionPane.showMessageDialog(null, "ERRO: Cliente não encontradao! \nVerifique se não a erros ao digitar o nome. ");
        		}
        	}
        });
        
        
        
        
        btnPesquisar.setBounds(419, 64, 88, 23);
        btnPesquisar.setBackground(Color.WHITE);
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 11));
        
        txtNome = new JTextField();
        //BUSCA AS INFOERMAÇÕES DO BANCO AO PRESSIONAR ENTER
        txtNome.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        			String nome = txtNome.getText();
            		Clientes obj = new Clientes();
            		ClientesDao dao = new ClientesDao();
            		
            		obj = dao.BuscarCliente(nome);
            		//SE O OBJETO FOR DIFERENTE DE NULO É POR QUE TEM ALGO
            		if(obj.getNome() != null) {
            			txtCodigo.setText(String.valueOf(obj.getId())); //ARRUMANDO ERRO DE ICOMPATIBILADE DE TIPO
            			txtNome.setText(obj.getNome());
            			txtRg.setText(obj.getRg());
            			txtCpf.setText(obj.getCpf());
            			txtEmail.setText(obj.getEmail());
            			txtTelefone.setText(obj.getTelefone());
            			txtCelular.setText(obj.getCelular());
            			txtCep.setText(obj.getCep());
            			txtEndereco.setText(obj.getEndereco());
            			txtNumero.setText(String.valueOf(obj.getNumero()));
            			txtComplemento.setText(obj.getComplemento());
            			txtBairro.setText(obj.getBairro());
            			txtCidade.setText(obj.getCidade());
            			cbUf.setSelectedItem(obj.getEstado());	
            		} else {
            			JOptionPane.showMessageDialog(null, "ERRO: Cliente não encontradao! \nVerifique se não a erros ao digitar o nome. ");
            		}
        			
        		}
        	}
        });
        txtNome.setBounds(65, 67, 338, 20);
        txtNome.setToolTipText("");
        txtNome.setFont(new Font("Arial", Font.PLAIN, 11));
        txtNome.setColumns(10);
        
        JLabel lblEmail = new JLabel("E-mail : ");
        lblEmail.setBounds(20, 99, 46, 14);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtEmail = new JTextField();
        txtEmail.setBounds(65, 96, 338, 20);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        txtEmail.setColumns(10);
        
        
        JLabel lblNewLabel_3_3 = new JLabel("Dados para Contato");
		lblNewLabel_3_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(10, 132, 185, 14);
		painel_dados_pessoais.add(lblNewLabel_3_3);
		
        
        // Campo de celular
        JLabel lblCelular = new JLabel("Celular :");
        lblCelular.setBounds(20, 160, 46, 14);
        lblCelular.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCelular = new JFormattedTextField();
        txtCelular.setBounds(76, 157, 125, 20);
        txtCelular.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Máscara do campo celular
        try {
            MaskFormatter mask = new MaskFormatter("(##) ## ##### - ####");
            mask.setValidCharacters("0123456789");  // Permite apenas números
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // Campo de telefone
        JLabel lblTelefone = new JLabel("Telefone :");
        lblTelefone.setBounds(212, 160, 55, 14);
        lblTelefone.setFont(new Font("Arial", Font.BOLD, 12));
                
        txtTelefone = new JFormattedTextField();
        txtTelefone.setBounds(278, 157, 125, 20);
        txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Máscara do campo telefone
        try {
            MaskFormatter mask = new MaskFormatter("(##) ## ##### - ####");
            mask.setValidCharacters("0123456789");  // Permite apenas números
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //Campo Cep
        JLabel lblCep = new JLabel("CEP :");
        lblCep.setBounds(20, 220, 46, 14);
        lblCep.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCep = new JFormattedTextField();
        txtCep.setBounds(66, 217, 75, 20);
        txtCep.setFont(new Font("Arial", Font.PLAIN, 12));
        
        //Mascara do campo Cep
        try {
			MaskFormatter mask = new MaskFormatter("##### - ###");
			mask.setValidCharacters("0123456789"); //Permite apenas números
			txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        //Area Endereço etc
		txtEndereco = new JTextField();
		txtEndereco.setBounds(221, 217, 182, 20);
		txtEndereco.setColumns(10);
		
		
		JLabel lblEndereco = new JLabel("Endereço : ");
		lblEndereco.setBounds(151, 220, 71, 14);
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		JLabel lblNumero = new JLabel("N° :");
		lblNumero.setBounds(419, 220, 26, 14);
		lblNumero.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtNumero = new JTextField();
		txtNumero.setBounds(461, 217, 46, 20);
		txtNumero.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Informações Residênciais ");
		lblNewLabel_3.setBounds(10, 192, 185, 14);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		JLabel lblNewLabel_3_1 = new JLabel("Identificação");
		lblNewLabel_3_1.setBounds(10, 11, 108, 14);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		JLabel lblBairro = new JLabel("Bairro : ");
		lblBairro.setBounds(20, 250, 56, 14);
		lblBairro.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtBairro = new JTextField();
		txtBairro.setBounds(66, 247, 120, 20);
		txtBairro.setColumns(10);
		
		
		JLabel lblCidade = new JLabel("Cidade : ");
		lblCidade.setBounds(217, 249, 56, 14);
		lblCidade.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtCidade = new JTextField();
		txtCidade.setBounds(268, 246, 135, 20);
		txtCidade.setColumns(10);
		
		
		JLabel lblComplemento = new JLabel("Complemento : ");
		lblComplemento.setBounds(20, 278, 97, 14);
		lblComplemento.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(116, 275, 287, 20);
		txtComplemento.setColumns(10);
		
		
		// Criação do JComboBox com lista de estados
		cbUf = new JComboBox<>();
		cbUf.setModel(new DefaultComboBoxModel<>(new String[] {
		    "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)",
		    "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)",
		    "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)",
		    "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)",
		    "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)"
		}));

		// Configurações do JComboBox
		cbUf.setBounds(419, 245, 88, 22);
		cbUf.setBackground(Color.WHITE);
		cbUf.setFont(new Font("Arial", Font.PLAIN, 11));
		cbUf.setToolTipText("Selecione o estado");
		painel_dados_pessoais.add(cbUf);

		
		
		JLabel lblNewLabel_3_2 = new JLabel("Complemento de identificação");
		lblNewLabel_3_2.setBounds(10, 316, 216, 14);
		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 14));
		
		
	
		//Campo RG
		JLabel lblRg = new JLabel("RG : ");
		lblRg.setBounds(20, 344, 34, 14);
		lblRg.setFont(new Font("Arial", Font.BOLD, 12));
	
		txtRg = new JFormattedTextField();
		txtRg.setBounds(49, 341, 90, 20);
		
		
		//Mascara do RG
		try {
			MaskFormatter mask = new MaskFormatter("##.###.### - ##");
			mask.setValidCharacters("0123456789"); 
			txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Camp CPF
		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setBounds(154, 344, 34, 14);
		lblCpf.setFont(new Font("Arial", Font.BOLD, 12));
		
	    txtCpf = new JFormattedTextField();
		txtCpf.setBounds(196, 341, 95, 20);
        
		//Mascara do CPF
        try {
			MaskFormatter mask = new MaskFormatter("###.###.### / ##");
			mask.setValidCharacters("0123456789");
			txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        //INCLUSÃO DOS COMPONENTES NO PAINEL
        painel_dados_pessoais.setLayout(null);
		painel_dados_pessoais.add(lblNewLabel_3_1);
		painel_dados_pessoais.add(lblCodigo);
		painel_dados_pessoais.add(txtCodigo);
		painel_dados_pessoais.add(lblCelular);
		painel_dados_pessoais.add(txtCelular);
		painel_dados_pessoais.add(lblTelefone);
		painel_dados_pessoais.add(txtTelefone);
		painel_dados_pessoais.add(lblNewLabel_3);
		painel_dados_pessoais.add(lblCep);
		painel_dados_pessoais.add(txtCep);
		painel_dados_pessoais.add(lblEndereco);
		painel_dados_pessoais.add(txtEndereco);
		painel_dados_pessoais.add(lblNumero);
		painel_dados_pessoais.add(btnPesquisar);
		painel_dados_pessoais.add(txtNumero);
		painel_dados_pessoais.add(txtComplemento);
		painel_dados_pessoais.add(lblComplemento);
		painel_dados_pessoais.add(lblBairro);
		painel_dados_pessoais.add(txtBairro);
		painel_dados_pessoais.add(lblCidade);
		painel_dados_pessoais.add(txtCidade);
		painel_dados_pessoais.add(cbUf);
		painel_dados_pessoais.add(lblNewLabel_3_2);
		painel_dados_pessoais.add(lblRg);
		painel_dados_pessoais.add(txtRg);
		painel_dados_pessoais.add(lblCpf);
		painel_dados_pessoais.add(txtCpf);
		painel_dados_pessoais.add(lblNome);
		painel_dados_pessoais.add(txtNome);
		painel_dados_pessoais.add(lblEmail);
		painel_dados_pessoais.add(txtEmail); 
		
		
		
		//2° ABA DO FORMULÁRIO
        JPanel painel_guias = new JPanel();
        painel_guias.setBackground(Color.WHITE);
        painel_guias_tab.addTab("Consulta de Clientes", null, painel_guias, null);
        painel_guias.setLayout(null);
        
        JLabel lblNomeConsultaCliente = new JLabel("Nome : ");
        lblNomeConsultaCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblNomeConsultaCliente.setBounds(9, 13, 46, 14);
        painel_guias.add(lblNomeConsultaCliente);
        
        txtNomeConsultaCliente = new JTextField();
        txtNomeConsultaCliente.addKeyListener(new KeyAdapter() {
        	@Override
        	//FILTRAGEM DE CLENTES
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
        txtNomeConsultaCliente.setBounds(65, 11, 326, 20);
        painel_guias.add(txtNomeConsultaCliente);
        //FILTRAGEM DE CLIENTES AO PRESSIONAR O BOTÃO
        JButton btnPesquisaCliente = new JButton("pesquisar");
        btnPesquisaCliente.addActionListener(new ActionListener() {
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
        btnPesquisaCliente.setFont(new Font("Arial", Font.BOLD, 11));
        btnPesquisaCliente.setBackground(Color.WHITE);
        btnPesquisaCliente.setBounds(436, 9, 88, 23);
        painel_guias.add(btnPesquisaCliente);
        
        
        
        // CRIANDO A TABELA
        tabela = new JTable();
        tabela.addMouseListener(new MouseAdapter() {
        	//ADICIONANDO EVENTO DE CLICK NA TABELA
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		painel_guias_tab.setSelectedIndex(0);//NO CASO REPRESENTA A 1° ABA(PAINEL)
        		txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0). toString());//DAQUI EM DIANTE REPRESETA O 2°
        		txtNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1). toString());
        		txtRg.setText(tabela.getValueAt(tabela.getSelectedRow(), 2). toString());
        		txtCpf.setText(tabela.getValueAt(tabela.getSelectedRow(), 3). toString());
        		txtEmail.setText(tabela.getValueAt(tabela.getSelectedRow(), 4). toString());
        		txtTelefone.setText(tabela.getValueAt(tabela.getSelectedRow(), 5). toString());
        		txtCelular.setText(tabela.getValueAt(tabela.getSelectedRow(), 6). toString());
        		txtCep.setText(tabela.getValueAt(tabela.getSelectedRow(), 7). toString());
        		txtEndereco.setText(tabela.getValueAt(tabela.getSelectedRow(), 8). toString());
        		txtNumero.setText(tabela.getValueAt(tabela.getSelectedRow(), 9). toString());
        		txtComplemento.setText(tabela.getValueAt(tabela.getSelectedRow(), 10). toString());
        		txtBairro.setText(tabela.getValueAt(tabela.getSelectedRow(), 11). toString());
        		txtCidade.setText(tabela.getValueAt(tabela.getSelectedRow(),12). toString());
        		cbUf.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 13). toString());
        	}
        });
        tabela.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));

        // MODELO TABELA 
        DefaultTableModel modelTable1 = new DefaultTableModel(
            new Object[][] {}, //INICIA SEM CELULAS
            new String[] {  // CABEÇALHO DAS COLUNAS
                "Id", "Nome", "RG", "CPF", "Email", "Telefone", "Celular", "CEP", "Endereco", "Número", "Complemento", "Bairro", "Cidade",  "UF"
                }
        );
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));//AJUSTANDO A FONTED O HEADER
        tabela.setModel(modelTable1);

        // AJUSTE DA LARGURA DAS COLUNAS
        TableColumnModel columnModel = tabela.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(30);  // ID
     	columnModel.getColumn(1).setPreferredWidth(150); // NOME
     	columnModel.getColumn(2).setPreferredWidth(100); // RG
     	columnModel.getColumn(3).setPreferredWidth(100); // CPF
     	columnModel.getColumn(4).setPreferredWidth(180); // E-MAIL
     	columnModel.getColumn(5).setPreferredWidth(125); // TELEFONE
     	columnModel.getColumn(6).setPreferredWidth(125); // CELULAR
     	columnModel.getColumn(7).setPreferredWidth(70);  // CEP
     	columnModel.getColumn(8).setPreferredWidth(150); // ENDERECO
     	columnModel.getColumn(9).setPreferredWidth(50);  // NÚMERO
     	columnModel.getColumn(10).setPreferredWidth(180); // COMPLEMENTO
     	columnModel.getColumn(11).setPreferredWidth(100); // BAIRRO
     	columnModel.getColumn(12).setPreferredWidth(100); // CIDADE
     	columnModel.getColumn(13).setPreferredWidth(100); // UF
     	
     	// Ajustar o comportamento de redimensionamento da tabela
     	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// DESLIGA O REDIMENCIONAMENTO AUTMATICO 
    	JScrollPane scrollConsultaClientes = new JScrollPane(tabela);// CRIANDO O JSCROLLPANE PARA A TABELA
     	scrollConsultaClientes.setBounds(10, 38, 514, 338);// DEFININDO O TAMANHO DO JSCROLLPANE
     	painel_guias.add(scrollConsultaClientes);// ADICIONANDO O JSCROLLPANE AO PAINEL 
     	
     	
    
     	//CAMPO DE BOTÕES, FOOTER
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel_3.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panel_3, BorderLayout.SOUTH);
        
        
        //{INICIO DOS BOTÕES FOOTER}
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
        	//INSTANCIA E ADICIONA O 'LIMPATELA'
        	public void actionPerformed(ActionEvent e) {
        		Utilitarios util = new Utilitarios();
        		//PEGA O METODO QUE ESTA DENTRO DO UTILITARIO
        		util.LimpaTela(painel_dados_pessoais);
        	}
        });
        btnNovo.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
        btnNovo.setBackground(Color.LIGHT_GRAY);
        btnNovo.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnNovo);
        
        
        //AÇÃO DO BOTÃO SALVAR CLIENTE
        //INSTANCIANDO A CLASSE MODELO DE CLIENTES
        JButton btnSalvar = new JButton("Salvar"); //SALVAR
        btnSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Clientes obj = new Clientes();
            	obj.setNome(txtNome.getText());
            	obj.setRg(txtRg.getText());
            	obj.setCpf(txtCpf.getText());
                obj.setEmail(txtEmail.getText());
            	obj.setTelefone(txtTelefone.getText());
            	obj.setCelular(txtCelular.getText());
           		obj.setCep(txtCep.getText());
            	obj.setEndereco(txtEndereco.getText());
            	obj.setNumero(Integer.valueOf(txtNumero.getText()));//RESOLVENDO INCOMPATIBIDADE DE TIPO
            	obj.setComplemento(txtComplemento.getText());
           		obj.setBairro(txtBairro.getText());
           		obj.setCidade(txtCidade.getText());
           		obj.setEstado(cbUf.getSelectedItem().toString());//Pelo fato de ser 'ComboBox' muda a forma de obter ele
           		
           		//AQUI PODE ADICIONAR A VERIFICAÇÃO SE ESTA DEIXANDO ALGUM CAMPO VAZIO
           		
           		ClientesDao dao = new ClientesDao();
           		dao.Salvar(obj); //SALVAR
           		
           		//LIMPA OS CAMPOS APÓS SALVAR
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_dados_pessoais);
           		
        	}
        });
        
        btnSalvar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnSave18px.png"));
        btnSalvar.setBackground(Color.LIGHT_GRAY);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnSalvar);
        
        
        //AÇÃO DO BOTÃO EDITAR CLIENTE
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Clientes obj = new Clientes();
            	obj.setNome(txtNome.getText());
            	obj.setRg(txtRg.getText());
            	obj.setCpf(txtCpf.getText());
                obj.setEmail(txtEmail.getText());
            	obj.setTelefone(txtTelefone.getText());
            	obj.setCelular(txtCelular.getText());
           		obj.setCep(txtCep.getText());
            	obj.setEndereco(txtEndereco.getText());
            	obj.setNumero(Integer.valueOf(txtNumero.getText()));
            	obj.setComplemento(txtComplemento.getText());
           		obj.setBairro(txtBairro.getText());
           		obj.setCidade(txtCidade.getText());
           		obj.setEstado(cbUf.getSelectedItem().toString());
           		obj.setId(Integer.valueOf(txtCodigo.getText()));
           		
           		ClientesDao dao = new ClientesDao();
           		dao.Editar(obj);
           		
           		//LIMPA OS CAMPOS APÓS SALVAR
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_dados_pessoais);
        	}
        });
        
        btnEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnEdit18px.png"));
        btnEditar.setBackground(Color.LIGHT_GRAY);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnEditar);
        
        
        //AÇÃO DO BOTÃO EXCLUIR CLIENTE
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Clientes obj = new Clientes();
        		obj.setId(Integer.valueOf(txtCodigo.getText()));
        		ClientesDao dao = new ClientesDao();
        		dao.Excluir(obj);
        		
        		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_dados_pessoais);
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
