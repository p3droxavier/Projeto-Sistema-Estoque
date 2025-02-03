//ALGUMAS SETTAGENS DO PROJETO FICARA AQUI 
//LIMPAS TELA APOS SALVAR O CLIENTE

package br.com.system.utilitarios;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Utilitarios {
	public void LimpaTela(JPanel container) { //RECEBE UM PANEL
		Component conponents[] = container.getComponents(); //OBTEM TODOS OS COMPONENTES DO PAINEL
		for(Component component : conponents) {
			//INTERA SOBRE CADA COMPONENTE E VERIFICA SE É UMA INSTÂNCIA DE JTEXTfILD(CAMPO DE TEXTO)
			if(component instanceof JTextField) {
				((JTextField)component).setText(null);
				//LIMPA O CONTEUDO DOS CAMPOS DE TEXTO ENCONTRADOS, DEFININDO-OS COMO 'NULL'
			}
		}
	}
}
