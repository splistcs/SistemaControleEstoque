package sdedr.ctrl;

import java.util.ArrayList;
import java.math.BigDecimal;

import sdedr.model.Produto;
import sdedr.model.Receita;
import sdedr.model.Enum.TipoMovimentacao;
import sdedr.dao.ProdutoDao;
import sdedr.dao.ReceitaDao;

public class ProdutoCtrl {

  private ArrayList<Receita> receitas = new ArrayList<>();

  public boolean prepararReceitas() {
    ReceitaDao receitaDao = new ReceitaDao();
    return receitaDao.retornarTudo(receitas);
  }

  public ArrayList<Receita> getReceitas() {
    return this.receitas;
  }

  public ArrayList<Produto> getInventario() {
    ArrayList<Produto> inventario = new ArrayList<>();
    ProdutoDao produtoDao = new ProdutoDao();
    if (produtoDao.retornarTudo(inventario)) {
      return inventario;
    } else {
      return null;
    }
  }

  private ArrayList<Produto> produtos = new ArrayList<>();

  public boolean prepararProdutos() {
    this.produtos.clear();
    ProdutoDao produtoDao = new ProdutoDao();
    return produtoDao.retornarTudo(produtos);
  }

  public ArrayList<Produto> getProdutos() {
    return this.produtos;
  }

  public boolean cadastrarProduto(Produto produto) {
    ProdutoDao produtoDao = new ProdutoDao();
    return produtoDao.inserir(produto);
  }

  public boolean removerProduto(Long id) {
    ProdutoDao produtoDao = new ProdutoDao();
    return produtoDao.remover(id);
  }

  public boolean atualizarQuantidade(Produto produto, BigDecimal quantidade, TipoMovimentacao tipo) {
    ProdutoDao produtoDao = new ProdutoDao();
    return produtoDao.atualizarQuantidade(produto, quantidade, tipo);
  }
}
