package sdedr.dao;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sdedr.model.Produto;
import sdedr.model.Unidade;
import sdedr.model.Enum.TipoCadastro;
import sdedr.model.Enum.TipoMovimentacao;
import sdedr.dao.dbcon.AcessoSQLite;


public class ProdutoDao {

  private void formatarProduto(Produto resultado, ResultSet saida) throws SQLException {
    resultado.setId(Long.valueOf(saida.getInt("id")));
    resultado.setNome(saida.getString("nome"));
    resultado.setPrecoAtual(saida.getBigDecimal("precoAtual"));
    resultado.setQuantidadeEstoque(saida.getBigDecimal("quantidadeEstoque"));
    resultado.setEstoqueMinimo(saida.getBigDecimal("estoqueMinimo"));

    if(saida.getInt("permiteFracionamento") == 1) {
      resultado.setPermiteFracionamento(true);
    } else {
      resultado.setPermiteFracionamento(false);
    }

    if (saida.getInt("ativo") == 1) {
      resultado.setAtivo(true);
    } else {
      resultado.setAtivo(false);
    }
  }

  public boolean retornarTudo(ArrayList<Produto> resultado) {
    String sql = "SELECT p.*, u.id AS u_id, u.nome AS u_nome, u.descricao AS u_desc "
               + "FROM produto AS p LEFT JOIN tipoUnidade AS t ON (p.id = t.id_p) "
                                 + "LEFT JOIN unidade AS u ON (t.id_u = u.id)";
    Connection con = null;
    PreparedStatement cmd;
    ResultSet saida;
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }

      cmd = con.prepareStatement(sql);
      saida = cmd.executeQuery();

      while (saida.next()) {
        Produto novoAux = new Produto();
        formatarProduto(novoAux, saida);

        if(saida.getString("u_nome") != null) {
          UnidadeDao unidadeDao = new UnidadeDao();
          Unidade unidade = new Unidade();

          unidadeDao.retornar(saida.getInt("u_id"), unidade);
          novoAux.setUnidade(unidade);
        }

        resultado.add(novoAux);
      }
      
      return resultado.isEmpty() ? false : true;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO [retornarTudo]:" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

    public boolean remover(Long id){
    String sql = "DELETE FROM produto "
               + "WHERE id = ?";
    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }

      con.setAutoCommit(false);
      cmd = con.prepareStatement(sql);
      cmd.setLong(1, id);

      cmd.executeUpdate();
      con.commit();

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }

    sql = "DELETE FROM tipoUnidade "
               + "WHERE id_p = ?";

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }

      con.setAutoCommit(false);
      cmd = con.prepareStatement(sql);
      cmd.setLong(1, id);

      cmd.executeUpdate();
      con.commit();

      return true;

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean retornar(Long id, Produto resultado) {
    String sql = "SELECT * FROM produto WHERE id = ?";
    Connection con = null;
    PreparedStatement cmd;
    ResultSet saida;
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setLong(1, id);
      saida = cmd.executeQuery();

      if (saida.next()) {
        formatarProduto(resultado, saida);
        
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }

    sql = "SELECT * FROM tipoUnidade WHERE id_p = ?";
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, Math.toIntExact(resultado.getId()));
      saida = cmd.executeQuery();

      if (saida.next()) {
        UnidadeDao unidadeDao = new UnidadeDao();
        Unidade unidade = new Unidade();

        resultado.setUnidade(unidade);
        unidadeDao.retornar(saida.getInt("id_u"), resultado.getUnidade());
      }
      
      return true;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean inserir(Produto produto) {
    String sql = "INSERT INTO produto (nome, precoAtual, quantidadeEstoque, estoqueMinimo, permiteFracionamento, ativo) VALUES (?, ?, ?, ?, ?, ?);";
    Connection con = null;
    PreparedStatement cmd;
    Long p_id;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setString(1, produto.getNome());
      cmd.setBigDecimal(2, produto.getPrecoAtual());
      cmd.setBigDecimal(3, produto.getQuantidadeEstoque());
      cmd.setBigDecimal(4, produto.getEstoqueMinimo());
      cmd.setInt(5, produto.isPermiteFracionamento() ? 1 : 0);
      cmd.setInt(6, produto.isAtivo() ? 1 : 0);

      cmd.executeUpdate();
      con.commit();
      
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
    sql = "SELECT id FROM produto WHERE nome = (?);";

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setString(1, produto.getNome());
      ResultSet saida = cmd.executeQuery();
      p_id = (Long.valueOf(saida.getInt("id")));

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }

    sql = "INSERT INTO tipoUnidade (id_p, id_u) VALUES (?, ?);";
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setLong(1, p_id);
      cmd.setLong(2, produto.getUnidade().getId());

      cmd.executeUpdate();
      con.commit();

      return true;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean atualizarQuantidade(Produto produto, BigDecimal quantidade_a_mudar, TipoMovimentacao movimentacao){
    Produto tmpProduto = new Produto();
    ProdutoDao produtoDao = new ProdutoDao();
    produtoDao.retornar((produto.getId()), tmpProduto);

    boolean isValid = (quantidade_a_mudar.compareTo(tmpProduto.getQuantidadeEstoque()) <= 0 && (movimentacao == TipoMovimentacao.SAIDA || movimentacao == TipoMovimentacao.PERDA))
       || (quantidade_a_mudar.compareTo(BigDecimal.ZERO) >= 0 && movimentacao == TipoMovimentacao.ENTRADA)
       || movimentacao == TipoMovimentacao.AJUSTE;

    if(isValid){
      String sql = "UPDATE produto SET quantidadeEstoque = ? "
                + "WHERE id = ?";
      Connection con = null;
      PreparedStatement cmd = null;

      try {
        con = AcessoSQLite.conectar();
        if (con == null) {
          return false;
        }

        con.setAutoCommit(false);
        cmd = con.prepareStatement(sql);
        if(movimentacao == TipoMovimentacao.SAIDA || movimentacao == TipoMovimentacao.PERDA){
          cmd.setBigDecimal(1, (tmpProduto.getQuantidadeEstoque().subtract(quantidade_a_mudar)));
        } else{
          cmd.setBigDecimal(1, (tmpProduto.getQuantidadeEstoque().add(quantidade_a_mudar)));
        }
        cmd.setLong(2, tmpProduto.getId());

        cmd.executeUpdate();
        con.commit();

        return true;
      } catch (SQLException deuRuim) {
        System.out.println("ERRO" + deuRuim.getMessage());
        return false;
      } finally {
        AcessoSQLite.desconectar(con);
      }
    }
    else {
      return false;
    }
  }
}
