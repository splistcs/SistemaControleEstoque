package sdedr.ctrl;

import java.util.ArrayList;

import sdedr.dao.UnidadeDao;
import sdedr.model.Unidade;

public class UnidadeCtrl {

  private ArrayList<Unidade> unidades = new ArrayList<>();

  public UnidadeCtrl() {
  }

  public boolean prepararUnidades() {
    UnidadeDao unidadeDao = new UnidadeDao();
    this.unidades.clear();
    return unidadeDao.retornarTudo(this.unidades);
  }

  public ArrayList<Unidade> getUnidades() {
    return this.unidades;
  }

  public boolean cadastrarUnidade(Unidade unidade) {
    UnidadeDao unidadeDao = new UnidadeDao();
    return unidadeDao.inserir(unidade);
  }

  public boolean removerUnidade(Long id) {
    UnidadeDao unidadeDao = new UnidadeDao();
    return unidadeDao.remover(id);
  }
}
