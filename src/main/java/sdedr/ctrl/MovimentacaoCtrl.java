package sdedr.ctrl;

import java.util.ArrayList;

import sdedr.dao.MovimentacaoDao;
import sdedr.dao.ProdutoDao;
import sdedr.model.MovimentacaoEstoqueDeProduto;

public class MovimentacaoCtrl {

  private ArrayList<MovimentacaoEstoqueDeProduto> movimentacaoEstoqueDeProdutos = new ArrayList<>();

  public boolean prepararMovimentacoes(int id) {
    MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    this.movimentacaoEstoqueDeProdutos.clear();
    return movimentacaoDao.retornarTudoPorId(movimentacaoEstoqueDeProdutos, id);
  }

  public ArrayList<MovimentacaoEstoqueDeProduto> getMovimentacaoEstoqueDeProdutos() {
    return this.movimentacaoEstoqueDeProdutos;
  }

  public boolean movimentarEstoque(MovimentacaoEstoqueDeProduto entrada) {
    ProdutoDao produtoDao = new ProdutoDao();
    if (entrada.getProduto() != null) {
      produtoDao.atualizarQuantidade(entrada.getProduto(), entrada.getQuantidade(), entrada.getTipoMovimentacao());
    }
    MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    return movimentacaoDao.inserir(entrada);
  }

  public ArrayList<MovimentacaoEstoqueDeProduto> getRelatorio(){
    ArrayList<MovimentacaoEstoqueDeProduto> relatorio = new ArrayList<>();
    MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    if (movimentacaoDao.retornarTudo(relatorio)) {
      return relatorio;
    } else {
      return new ArrayList<>();
    }
  }

  public boolean registrarMovimentacao(MovimentacaoEstoqueDeProduto movimentacao) {
    MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    return movimentacaoDao.inserir(movimentacao);
  }
}
