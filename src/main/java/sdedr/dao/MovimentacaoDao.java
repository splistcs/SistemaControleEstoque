package sdedr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sdedr.dao.dbcon.AcessoSQLite;

import sdedr.model.Produto;
import sdedr.model.MovimentacaoEstoqueDeProduto;
import sdedr.model.Enum.TipoMovimentacao;
import sdedr.model.User;

public class MovimentacaoDao {

  private void formatarMovimentacao(MovimentacaoEstoqueDeProduto resultado, ResultSet saida) throws SQLException {
    resultado.setId(Long.valueOf(saida.getInt("id")));

    ProdutoDao produtoDao = new ProdutoDao();
    Produto produto = new Produto();
    produtoDao.retornar(saida.getLong("id_p"), produto);
    resultado.setProduto(produto);

    UserDao userDao = new UserDao();
    User user = new User();
    userDao.retornar(saida.getInt("id_u"), user);
    resultado.setUser(user);

    resultado.setTipoMovimentacao(TipoMovimentacao.tipoMovimentacaoInt(saida.getInt("tipoMovimentacao")));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    resultado.setDataHora(LocalDateTime.parse(saida.getString("dataMovimentacao"), formatter));

    resultado.setQuantidade(saida.getBigDecimal("quantidade"));
    resultado.setPrecoUnitario(saida.getBigDecimal("precoUnitario"));
    resultado.setValidadeLote(LocalDate.parse(saida.getString("dataValidade")));
    resultado.setObservacao(saida.getString("observacao"));
  }  

  private void formatarMovimentacaoSemOutraDao(MovimentacaoEstoqueDeProduto resultado, ResultSet saida) throws SQLException {
    resultado.setId(Long.valueOf(saida.getInt("id")));
    resultado.setTipoMovimentacao(TipoMovimentacao.tipoMovimentacaoInt(saida.getInt("tipoMovimentacao")));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    resultado.setDataHora(LocalDateTime.parse(saida.getString("dataMovimentacao"), formatter));
    resultado.setQuantidade(saida.getBigDecimal("quantidade"));
    resultado.setPrecoUnitario(saida.getBigDecimal("precoUnitario"));
    resultado.setValidadeLote(LocalDate.parse(saida.getString("dataValidade")));
    resultado.setObservacao(saida.getString("observacao"));
  }

  public boolean retornarTudoPorId(ArrayList<MovimentacaoEstoqueDeProduto> resultado, int idUser) {
    String sql = "SELECT m.*, p.nome AS p_nome "
               + "FROM movimentacao AS m LEFT JOIN user AS us       ON (m.id_u = us.id) "
               +                        "LEFT JOIN produto AS p     ON (m.id_p = p.id) "
               +                        "LEFT JOIN tipoUnidade AS t ON (p.id = t.id_p) "
               +                        "LEFT JOIN unidade AS u     ON (t.id_u = u.id) "
               + "WHERE (us.id = ?) ";
    Connection con = null;
    PreparedStatement cmd;
    ResultSet saida;
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, idUser);
      saida = cmd.executeQuery();

      while (saida.next()) {
        MovimentacaoEstoqueDeProduto novoAux = new MovimentacaoEstoqueDeProduto();
        formatarMovimentacaoSemOutraDao(novoAux, saida);

        if(saida.getString("p_nome") != null) {
          Produto produtoTmp = new Produto();
          produtoTmp.setNome(saida.getString("p_nome"));
          novoAux.setProduto(produtoTmp);
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

   public boolean retornar(int id, MovimentacaoEstoqueDeProduto resultado) {
    String sql = "SELECT * FROM movimentacao WHERE id = ?";
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
      cmd.setInt(1, id);
      saida = cmd.executeQuery();

      if (saida.next()) {
        formatarMovimentacao(resultado, saida);
        
        return true;
      }
      
      return false;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean retornarTudo(ArrayList<MovimentacaoEstoqueDeProduto> resultado) {
    String sql = "SELECT * FROM movimentacao";
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
      saida = cmd.executeQuery();

      while (saida.next()) {
        MovimentacaoEstoqueDeProduto novaMovimentacao = new MovimentacaoEstoqueDeProduto();
        formatarMovimentacao(novaMovimentacao, saida);
        resultado.add(novaMovimentacao);
      }
      
      return resultado.isEmpty() ? false : true;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean inserir(MovimentacaoEstoqueDeProduto movimentacao) {
    String sql = "INSERT INTO movimentacao (id_p, id_u, tipoMovimentacao, dataMovimentacao, quantidade, precoUnitario, dataValidade, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    Connection con = null;
    PreparedStatement cmd;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, movimentacao.getProduto().getId().intValue());
      cmd.setInt(2, movimentacao.getUser().getId().intValue());
      cmd.setInt(3, movimentacao.getTipoMovimentacao().ordinal());
      cmd.setString(4, movimentacao.getDataHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      cmd.setBigDecimal(5, movimentacao.getQuantidade());
      cmd.setBigDecimal(6, movimentacao.getPrecoUnitario());
      cmd.setString(7, movimentacao.getValidadeLote().toString());
      cmd.setString(8, movimentacao.getObservacao());

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
}
