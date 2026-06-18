package sdedr.model;

import sdedr.model.Enum.Cardapio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receita {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private Cardapio cardapio;
	
	private List<Ingrediente> ingredientes = new ArrayList<>();
	
	public Receita() { }
	
	public Receita(Long id, String nome, BigDecimal preco, Cardapio cardapio) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.cardapio = cardapio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public void setCardapio(int index){
		this.cardapio = Cardapio.tipoCardapioInt(index);
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public ArrayList<Ingrediente> getIngredientes(Ingrediente ingrediente) {
		return new ArrayList<>(this.ingredientes);
	}


	public void adicionarIngrediente(Ingrediente ingrediente) {
		if (ingrediente != null && !this.ingredientes.contains(ingrediente)) {
			this.ingredientes.add(ingrediente);
			ingrediente.setReceita(this);
		}
	}
	
	@Override
    public String toString() {
        return this.getNome(); 
    }
}
