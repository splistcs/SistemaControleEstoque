package sdedr.ctrl;

import java.util.ArrayList;

import sdedr.model.Receita;
import sdedr.dao.ReceitaDao;

public class ReceitaCtrl {

  private ArrayList<Receita> receitas = new ArrayList<>();

  public boolean prepararReceitas() {
    ReceitaDao receitaDao = new ReceitaDao();
    return receitaDao.retornarTudo(receitas);
  }

  public ArrayList<Receita> getReceitas() {
    return this.receitas;
  }

  public boolean cadastrarReceita(Receita receita) {
    ReceitaDao receitaDao = new ReceitaDao();
    return receitaDao.inserir(receita);
  }

  public boolean removerReceita(Long id) {
    ReceitaDao receitaDao = new ReceitaDao();
    return receitaDao.remover(id);
  }
}
