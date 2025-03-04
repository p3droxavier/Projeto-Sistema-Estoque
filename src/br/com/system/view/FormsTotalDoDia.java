package br.com.system.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextField;

public class FormsTotalDoDia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtData;
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FormsTotalDoDia window = new FormsTotalDoDia();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormsTotalDoDia() {
		initialize();
	}
	
	
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Total do dia");
		setBounds(100, 100, 520, 320);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
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
		
	      JPanel grid_posicao_dia = new JPanel();
	      TitledBorder border = new TitledBorder(null, "Posição do dia", TitledBorder.LEADING, TitledBorder.TOP, null, null);
	      border.setTitleFont(new Font("Arial", Font.BOLD, 14));  
	      grid_posicao_dia.setBorder(border);
	      grid_posicao_dia.setBackground(new Color(255, 255, 255));
	      grid_posicao_dia.setBounds(189, 28, 315, 104);
	      contentPane.add(grid_posicao_dia);
	      grid_posicao_dia.setLayout(null);
	      
	      JLabel lblData = new JLabel("Data :");
	      lblData.setFont(new Font("Arial", Font.BOLD, 12));
	      lblData.setBounds(10, 31, 42, 14);
	      grid_posicao_dia.add(lblData);
	      
	      JFormattedTextField txtDataInicio = new JFormattedTextField();
			txtDataInicio.setBounds(419, 43, 128, 20);
			try {
				MaskFormatter mask = new MaskFormatter("##/##/20##");
				mask.setValidCharacters("0123456789");
				txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			grid_posicao_dia.add(txtDataInicio);
	      
	      JButton btnPesquisar = new JButton("Pesquisar");
	      btnPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
	      btnPesquisar.setBounds(197, 27, 99, 23);
	      grid_posicao_dia.add(btnPesquisar);
	      
	      txtData = new JTextField();
	      txtData.setFont(new Font("Arial", Font.BOLD, 12));
	      txtData.setBounds(54, 28, 86, 20);
	      grid_posicao_dia.add(txtData);
	      txtData.setColumns(10);
	      
	      txtTotal = new JTextField();
	      txtTotal.setBounds(244, 157, 208, 41);
	      contentPane.add(txtTotal);
	      txtTotal.setColumns(10);
	}
}
