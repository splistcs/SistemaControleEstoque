package sdedr.model;



public class Unidade {
	
	private Long id;
	private String nome;
	private String descricao;
	
	public Unidade(){ }

	@Override
    public String toString() {
        return this.getNome();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String desc) {
		this.descricao = desc;
	}
}
