package br.com.system.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.system.dao.VendasDao;

public class FormsTotalDoDia extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTotal;
	public JFormattedTextField txtData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					FormsTotalDoDia window = new FormsTotalDoDia();
//					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormsTotalDoDia(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initialize();
	}
	
	
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Total do dia");
		setBounds(100, 100, 520, 320);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 190, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total do dia");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 190, 281);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
	     JPanel grid_box01 = new JPanel();
	      

	     grid_box01.setBackground(new Color(255, 255, 255));
	     grid_box01.setBounds(189, 0, 315, 152);
	     contentPane.add(grid_box01);
	     grid_box01.setLayout(null);
	      
	     JLabel lblData = new JLabel("Data :");
	     lblData.setFont(new Font("Arial", Font.BOLD, 12));
	     lblData.setBounds(31, 54, 42, 14);
	     grid_box01.add(lblData);
	     
	     JFormattedTextField txtData = new JFormattedTextField();
	      txtData.setBounds(69, 51, 86, 20);
	      try {
			MaskFormatter mask = new MaskFormatter("##/##/20##");
			mask.setValidCharacters("0123456789");
			txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
	      grid_box01.add(txtData);
	      
	      
	     JButton btnPesquisar = new JButton("Pesquisar");
	     btnPesquisar.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {

				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //SETANDO A FORMATAÇÃO DA DATA
		      	LocalDate buscaData = LocalDate.parse(txtData.getText(), formato); //CONVERTE O TEXTO OBTIDO PARA UMA DATA NO TIPO 'LOCALDATE' 
		      	
		      	double total_do_dia;
		      	VendasDao daoV = new VendasDao();
		      	total_do_dia = daoV.TotalDoDia(buscaData);
		      	txtTotal.setText(String.valueOf(total_do_dia));	
	      		
	    	 }
	      });
	      btnPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
	      btnPesquisar.setBounds(190, 50, 99, 23);
	      grid_box01.add(btnPesquisar);
	      
	      JLabel lblNewLabel_1 = new JLabel("Posição do dia ");
	      lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
	      lblNewLabel_1.setBounds(10, 25, 119, 14);
	      grid_box01.add(lblNewLabel_1);
	      
	      JLabel lblNewLabel_2 = new JLabel("Coloque aqui exatamente a data em que ");
	      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel_2.setForeground(SystemColor.controlShadow);
	      lblNewLabel_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
	      lblNewLabel_2.setBounds(0, 84, 315, 14);
	      grid_box01.add(lblNewLabel_2);
	      
	      JLabel lblNewLabel_2_1 = new JLabel("deseja veriricar o caixa!");
	      lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel_2_1.setForeground(SystemColor.controlShadow);
	      lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
	      lblNewLabel_2_1.setBounds(0, 98, 315, 14);
	      grid_box01.add(lblNewLabel_2_1);
	      
	   
	      
	      JPanel grid_box02 = new JPanel();
	      grid_box02.setBounds(189, 157, 315, 124);
	      contentPane.add(grid_box02);
	      grid_box02.setLayout(null);
	      
	      txtTotal = new JTextField();
	      txtTotal.setEnabled(false);
	      txtTotal.setFont(new Font("Arial", Font.BOLD, 16));
	      txtTotal.setBounds(143, 47, 82, 41);

	      // Removendo as bordas do JTextField
	      txtTotal.setBorder(BorderFactory.createEmptyBorder());

	      // Adicionando ao painel
	      grid_box02.add(txtTotal);
	      txtTotal.setColumns(10);
	      
	      JLabel lblTotalDoDia = new JLabel("Total do dia");
	      lblTotalDoDia.setFont(new Font("Arial", Font.BOLD, 14));
	      lblTotalDoDia.setBounds(10, 22, 119, 14);
	      grid_box02.add(lblTotalDoDia);
	      
	      JLabel lblNewLabel_3 = new JLabel("R$ :");
	      lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
	      lblNewLabel_3.setBounds(97, 60, 46, 14);
	      grid_box02.add(lblNewLabel_3);
	}
}
