package sdedr.model;

import java.math.BigDecimal;

public class Ingrediente {
	
	private BigDecimal quantidadeProduto;
	private Produto produto;
	private Receita receita;
	
	public Ingrediente() { }
	
	public Ingrediente(BigDecimal quantidade, Produto produto, Receita receita) {
		this.quantidadeProduto = quantidade;
		this.produto = produto;
		this.receita = receita;
	}

	public BigDecimal getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}
}
