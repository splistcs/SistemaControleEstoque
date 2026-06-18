package sdedr.ctrl;

import sdedr.ctrl.*;

/* Class CtrlCtrl
 * (obj.)
 * > Manter Ctrl por longo prazo          [ok!]
 * > Facilitar referência e arg nas views [?] 
 */

public class CtrlCtrl {
  public final UserCtrl userCtrl;
  public final MovimentacaoCtrl movimentacaoCtrl;
  public final ProdutoCtrl produtoCtrl;
  public final ReceitaCtrl receitaCtrl;
  public final UnidadeCtrl unidadeCtrl;
  public final IngredienteCtrl ingredienteCtrl;

  public CtrlCtrl() {
    this.userCtrl = new UserCtrl();
    this.movimentacaoCtrl = new MovimentacaoCtrl();
    this.produtoCtrl = new ProdutoCtrl();
    this.receitaCtrl = new ReceitaCtrl();
    this.unidadeCtrl = new UnidadeCtrl();
    this.ingredienteCtrl = new IngredienteCtrl();
  }
}
