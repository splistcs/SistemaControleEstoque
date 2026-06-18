package sdedr.model;

import sdedr.model.Enum.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovimentacaoEstoqueDeProduto {
	private Long id;
	private LocalDateTime dataHora;
	private BigDecimal quantidade;
	private BigDecimal precoUnitario;
	private LocalDate validadeLote;
	private String observacao;
	
	private TipoMovimentacao tipoMovimentacao;
	private Produto produto;
	private User user;
	
	public MovimentacaoEstoqueDeProduto() { }

	public MovimentacaoEstoqueDeProduto(Long id, LocalDateTime dataHora, BigDecimal quantidade,
			BigDecimal precoUnitario, LocalDate validadeLote, String observacao, TipoMovimentacao tipoMovimentacao,
			Produto produto, User user) {
		this.id = id;
		this.dataHora = dataHora;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.validadeLote = validadeLote;
		this.observacao = observacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.produto = produto;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getPrecoTotal() {
		return this.precoUnitario.multiply(this.quantidade);
	}

	public LocalDate getValidadeLote() {
		return validadeLote;
	}

	public void setValidadeLote(LocalDate validadeLote) {
		this.validadeLote = validadeLote;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return this.user.getNome();
	}
}
