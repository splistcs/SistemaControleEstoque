package sdedr.model;

import sdedr.model.Enum.TipoCadastro;


public class User {
	
	private Long id;
	private String nome;
	private String senha;
	
	private TipoCadastro tipoCadastro;
	
	public User() { }
	
	public User(Long id, String nome, String senha, TipoCadastro tipo) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.tipoCadastro = tipo;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoCadastro getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(TipoCadastro tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public void setTipoCadastro(int index){
		this.tipoCadastro = TipoCadastro.tipoCadastroInt(index);
	}
}
