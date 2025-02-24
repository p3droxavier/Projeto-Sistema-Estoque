//ANOT
/*
 * private Vendas vendas; || private Produtos produtos;
 * MODELANDO O RELACIONAMENTO DAS TABELAS
 * */

package br.com.system.model;

public class ItensVendas {
	
	private int id;
	private Vendas vendas;
	private Produtos produtos;
	private int qtd;
	private double sibtotal;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Vendas getVendas() {
		return vendas;
	}
	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}
	
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	public double getSibtotal() {
		return sibtotal;
	}
	public void setSibtotal(double sibtotal) {
		this.sibtotal = sibtotal;
	}
	

}
