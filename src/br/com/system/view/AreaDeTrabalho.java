package br.com.system.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AreaDeTrabalho extends JFrame {
	
    // INICIALIZADOR DE VERSÃO SERIALIZADA 
    private static final long serialVersionUID = 1L;
    
    

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //MAXIMIZA A JANELA AO SER ATIVADA.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);// CENTRALIZA A JANELA NA TELA
        setResizable(false);// IMPEDE REDIMENCIONAMENTO DA JANELA

        
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Menu de Clientes
        JMenu mnNewMenu = new JMenu("Clientes");
        menuBar.add(mnNewMenu);
        JMenuItem mntmNewMenuItem = new JMenuItem("Formulário de Clientes");
        mnNewMenu.add(mntmNewMenuItem);

        
        // Menu de Funcionários
        JMenu mnNewMenu_1 = new JMenu("Funcionários");
        menuBar.add(mnNewMenu_1);
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Formulário de Funcionários");
        mnNewMenu_1.add(mntmNewMenuItem_1);

        
        // Menu de Fornecedores
        JMenu mnNewMenu_2 = new JMenu("Fornecedores");
        menuBar.add(mnNewMenu_2);
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Formulário de Fornecedores");
        mnNewMenu_2.add(mntmNewMenuItem_2);

        
        // Menu de Produtos
        JMenu mnNewMenu_3 = new JMenu("Produtos");
        menuBar.add(mnNewMenu_3);
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Controle de Estoque");
        mnNewMenu_3.add(mntmNewMenuItem_3);
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Consulta de Produtos");
        mnNewMenu_3.add(mntmNewMenuItem_4);

        
        // Menu de Vendas
        JMenu mnNewMenu_4 = new JMenu("Vendas");
        menuBar.add(mnNewMenu_4);
        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir PDV");
        mnNewMenu_4.add(mntmNewMenuItem_5);
        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Posição do Dia");
        mnNewMenu_4.add(mntmNewMenuItem_6);
        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Histórico de Vendas");
        mnNewMenu_4.add(mntmNewMenuItem_7);

        
        // Menu de Configurações
        JMenu mnNewMenu_5 = new JMenu("Configurações");
        menuBar.add(mnNewMenu_5);
        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Trocar de Usuário");
        mnNewMenu_5.add(mntmNewMenuItem_8);

        
        // Menu Sair
        JMenu mnNewMenu_6 = new JMenu("Sair");
        menuBar.add(mnNewMenu_6);
        JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sair do Sistema");
        mnNewMenu_6.add(mntmNewMenuItem_9);
    }
}
