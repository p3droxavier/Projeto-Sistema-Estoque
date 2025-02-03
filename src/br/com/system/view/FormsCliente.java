//Anot Project
//Pega o que foi escrito nas caixas pelo nome de cada ex(txtNome)
//obj.setNome(txtNome.getText()); (linha414)
//OBS, a ordem precissa ser identica a do banco

package br.com.system.view;

import br.com.system.dao.ClientesDao;
import br.com.system.model.Clientes;
import br.com.system.utilitarios.Utilitarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;


public class FormsCliente {
    private JFrame frame;
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

    private JTable table;
    private JTable tabelaClientes;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    FormsCliente window = new FormsCliente();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormsCliente() {
        initialize();
    }

    private void initialize() {
        // Create the main frame only once
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 875, 492);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        frame.getContentPane().add(panel, BorderLayout.WEST);
        panel.setPreferredSize(new Dimension(320, 60));
        panel.setLayout(null);

        // Title label
        JLabel lblNewLabel = new JLabel("Cadastro de Clientes");
        lblNewLabel.setBounds(0, 0, 320, 420);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblNewLabel);

        // Create the JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 11));
        tabbedPane.setBackground(Color.GRAY);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        
        //Painel 1
        JPanel painel_dados_pessoais = new JPanel();
        painel_dados_pessoais.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Dados Pessoais", null, painel_dados_pessoais, null);
        tabbedPane.setBackgroundAt(0, Color.WHITE);
        tabbedPane.setEnabledAt(0, true);
        
        JLabel lblCodigo = new JLabel("Codigo : ");
        lblCodigo.setBounds(10, 39, 56, 14);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        txtCodigo.setBounds(66, 36, 108, 20);
        txtCodigo.setColumns(10);
        
        JLabel lblNome = new JLabel("Nome : ");
        lblNome.setBounds(10, 68, 46, 14);
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
        txtNome.setBounds(65, 67, 338, 20);
        txtNome.setToolTipText("");
        txtNome.setFont(new Font("Arial", Font.PLAIN, 11));
        txtNome.setColumns(10);
        
        JLabel lblEmail = new JLabel("E-mail : ");
        lblEmail.setBounds(10, 99, 46, 14);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        
        txtEmail = new JTextField();
        txtEmail.setBounds(65, 96, 338, 20);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        txtEmail.setColumns(10);
        
        
        // Campo de celular
        JLabel lblCelular = new JLabel("Celular :");
        lblCelular.setBounds(10, 160, 46, 14);
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
        lblCep.setBounds(10, 220, 46, 14);
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
		lblBairro.setBounds(10, 250, 56, 14);
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
		lblComplemento.setBounds(10, 278, 97, 14);
		lblComplemento.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(106, 275, 297, 20);
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
		lblRg.setBounds(10, 344, 34, 14);
		lblRg.setFont(new Font("Arial", Font.BOLD, 12));
	
		txtRg = new JFormattedTextField();
		txtRg.setBounds(66, 341, 90, 20);
		
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
		lblCpf.setBounds(171, 344, 34, 14);
		lblCpf.setFont(new Font("Arial", Font.BOLD, 12));
		
	    txtCpf = new JFormattedTextField();
		txtCpf.setBounds(213, 341, 95, 20);
        
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
		
		JLabel lblNewLabel_3_3 = new JLabel("Informações Residênciais ");
		lblNewLabel_3_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(10, 132, 185, 14);
		painel_dados_pessoais.add(lblNewLabel_3_3);
		
		

        JPanel painel_guias = new JPanel();
        painel_guias.setBackground(Color.WHITE);
        tabbedPane.addTab("Consultar de Clientes", null, painel_guias, null);
        painel_guias.setLayout(null);
        
        JLabel lblNomeConsultaCliente = new JLabel("Nome : ");
        lblNomeConsultaCliente.setFont(new Font("Arial", Font.BOLD, 12));
        lblNomeConsultaCliente.setBounds(9, 13, 46, 14);
        painel_guias.add(lblNomeConsultaCliente);
        
        txtNomeConsultaCliente = new JTextField();
        txtNomeConsultaCliente.setToolTipText("");
        txtNomeConsultaCliente.setFont(new Font("Arial", Font.PLAIN, 11));
        txtNomeConsultaCliente.setColumns(10);
        txtNomeConsultaCliente.setBounds(65, 11, 326, 20);
        painel_guias.add(txtNomeConsultaCliente);
        
        JButton btnPesquisaCliente = new JButton("pesquisar");
        btnPesquisaCliente.setFont(new Font("Arial", Font.BOLD, 11));
        btnPesquisaCliente.setBackground(Color.WHITE);
        btnPesquisaCliente.setBounds(436, 9, 88, 23);
        painel_guias.add(btnPesquisaCliente);
        
        
        
        // Criando a tabela
        tabelaClientes = new JTable();

        // Criando o modelo para a tabela (table_1)
        DefaultTableModel modelTable1 = new DefaultTableModel(
            new Object[][] {}, //inicia sem celulas
            new String[] {  // Cabeçalhos das colunas
                "ID", "Nome", "E-mail", "Celular", "Telefone", "CEP", "Endereço", "Número", "Bairro", "Cidade", "Complemento", "UF", "RG", "CPF"
                }
        );


        tabelaClientes.setModel(modelTable1);

        // Ajuste das larguras das colunas para torná-las legíveis
        TableColumnModel columnModel = tabelaClientes.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(30);  // ID
     	columnModel.getColumn(1).setPreferredWidth(150); // Nome
     	columnModel.getColumn(2).setPreferredWidth(150); // E-mail
     	columnModel.getColumn(3).setPreferredWidth(85); // Celular
     	columnModel.getColumn(4).setPreferredWidth(85); // Telefone
     	columnModel.getColumn(5).setPreferredWidth(60);  // CEP
     	columnModel.getColumn(6).setPreferredWidth(150); // Endereço
     	columnModel.getColumn(7).setPreferredWidth(50);  // Número
     	columnModel.getColumn(8).setPreferredWidth(100); // Bairro
     	columnModel.getColumn(9).setPreferredWidth(100); // Cidade
     	columnModel.getColumn(10).setPreferredWidth(100); // Complemento
     	columnModel.getColumn(11).setPreferredWidth(100); // UF
     	columnModel.getColumn(12).setPreferredWidth(80); // RG
     	columnModel.getColumn(13).setPreferredWidth(75); // CPF

    

     	// Ajustar o comportamento de redimensionamento da tabela
     	tabelaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  // Desliga o redimensionamento automático
     	
     	// Criando o JScrollPane para a tabela
     	JScrollPane scrollConsultaClientes = new JScrollPane(tabelaClientes);

     	// Definindo o tamanho do JScrollPane
     	scrollConsultaClientes.setBounds(10, 50, 514, 300);  // Ajuste conforme necessário para a largura total de 466px

     	// Adicionando o JScrollPane ao painel (panel_2, por exemplo)
     	painel_guias.add(scrollConsultaClientes);

    
     
     	//Campo de botões, FOOTER
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel_3.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(panel_3, BorderLayout.SOUTH);
        
        
        //{INICIO DOS BOTÕES}
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
        	//INSTANCIA E ADICIONA O 'LIMPATELA'
        	public void actionPerformed(ActionEvent e) {
        		Utilitarios util = new Utilitarios();
        		//PEGA O METODO QUE ESTA DENTRO DO UTILITARIO
        		util.LimpaTela(painel_dados_pessoais);
        	}
        });
        btnNovo.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnNew18px.png"));
        btnNovo.setBackground(Color.LIGHT_GRAY);
        btnNovo.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnNovo);
        
        
        //AÇÃO DO BOTÃO SALVAR CLIENTE
        //INSTANCIANDO A CLASSE MODELO DE CLIENTES
        JButton btnSalvar = new JButton("Salvar"); //Salvar
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
            	obj.setNumero(Integer.valueOf(txtNumero.getText()));//Resolvendo incompatibilidade de tipo
            	obj.setComplemento(txtComplemento.getText());
           		obj.setBairro(txtBairro.getText());
           		obj.setCidade(txtCidade.getText());
           		obj.setEstado(cbUf.getSelectedItem().toString());//Pelo fato de ser 'ComboBox' muda a forma de obter ele
           		
           		//AQUI PODE ADICIONAR A VERIFICAÇÃO SE ESTA DEIXANDO ALGUMCAMPO VAZIO
           		
           		ClientesDao dao = new ClientesDao();
           		dao.Salvar(obj); //Salvar
           		
           		//LIMPA OS CAMPOS APÓS SALVAR
           		Utilitarios util = new Utilitarios();
        		util.LimpaTela(painel_dados_pessoais);
           		
        	}
        });
        
        btnSalvar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnSave18px.png"));
        btnSalvar.setBackground(Color.LIGHT_GRAY);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnSalvar);
        
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnEdit18px.png"));
        btnEditar.setBackground(Color.LIGHT_GRAY);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnDelete18px.png"));
        btnExcluir.setBackground(Color.LIGHT_GRAY);
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnExcluir);
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconBtnImprimir18px.png"));
        btnImprimir.setBackground(Color.LIGHT_GRAY);
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
        panel_3.add(btnImprimir);
        //{FIM DOS BOTÕES}

    }
}
