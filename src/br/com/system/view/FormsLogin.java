package br.com.system.view;

import br.com.system.dao.FuncionariosDao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;




public class FormsLogin extends javax.swing.JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JButton btnEntrar;
	
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FormsLogin window = new FormsLogin();
                    
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FormsLogin() {
    	initialize();
    }
    
    
    private void initialize() {
    	setTitle("Login");
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(0, 0, 0));
    	getContentPane().add(panel, BorderLayout.WEST);
    	panel.setPreferredSize(new Dimension(320, 60));
    	panel.setLayout(null);
    	setBounds(100, 100, 660, 390);
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    	
    	JLabel lblNewLabel = new JLabel("Autentificação de Usuário");
    	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
    	lblNewLabel.setForeground(new Color(255, 255, 255));
    	lblNewLabel.setBounds(0, 0, 320, 351);
    	panel.add(lblNewLabel);
    	
    	JPanel panel_area_credencial = new JPanel();
    	getContentPane().add(panel_area_credencial, BorderLayout.CENTER);
    	panel_area_credencial.setLayout(null);
    	
    	JLabel lblEmail = new JLabel("E-mail :");
    	lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
    	lblEmail.setBounds(30, 104, 46, 14);
    	panel_area_credencial.add(lblEmail);
    	
    	txtEmail = new JTextField();
    	txtEmail.setBounds(79, 101, 222, 20);
    	panel_area_credencial.add(txtEmail);
    	txtEmail.setColumns(10);
    	
    	JLabel lblSenha = new JLabel("Senha :");
    	lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
    	lblSenha.setBounds(30, 139, 46, 14);
    	panel_area_credencial.add(lblSenha);
    	
    	JLabel lblNewLabel_3 = new JLabel("Coloque suas Credenciais ");
    	lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
    	lblNewLabel_3.setBounds(0, 39, 324, 14);
    	panel_area_credencial.add(lblNewLabel_3);
    	
    	txtSenha = new JPasswordField();
    	txtSenha.setBounds(79, 136, 222, 20);
    	panel_area_credencial.add(txtSenha);
    	
    	btnEntrar = new JButton("Entrar");
    	btnEntrar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
					String email;
					email = txtEmail.getText();
					
					//OBTENDO OS VALORES DE SENHA
					char[] senhaArray = txtSenha.getPassword();
					String senha = new String(senhaArray);
					
					FuncionariosDao dao = new FuncionariosDao();
					dao.efetuarLogin(email, senha);
					
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "ERROR: FL Up" + err);
				}
    		}
    	});
    	btnEntrar.setForeground(new Color(0, 102, 0));
    	btnEntrar.setOpaque(false);
    	btnEntrar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonLoginUserLogin24px.png"));
    	btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
    	btnEntrar.setBounds(30, 195, 271, 35);
    	panel_area_credencial.add(btnEntrar);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setForeground(new Color(204, 51, 0));
    	btnCancelar.setIcon(new ImageIcon("C:\\Users\\Usuario\\git\\repository\\Sistema_Estoque\\src\\br\\com\\system\\img\\iconfinder_iconButtonLoginUserCancel24px.png"));
    	btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    	btnCancelar.setBounds(30, 239, 271, 35);
    	panel_area_credencial.add(btnCancelar);
    	
    	
    	
    }
}
