package sdedr.dao;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sdedr.model.Enum.Cardapio;
import sdedr.model.Receita;
import sdedr.dao.dbcon.AcessoSQLite;

public class ReceitaDao {

  private void formatarReceita(ResultSet saida, Receita resultado) throws SQLException {
    resultado.setId(Long.valueOf(saida.getInt("id")));
    resultado.setPreco(saida.getBigDecimal("preco"));
    resultado.setNome(saida.getString("nome"));
    resultado.setCardapio(Cardapio.tipoCardapioInt(saida.getInt("cardapio")));
  }

  public boolean retornarTudo(ArrayList<Receita> resultado) {
    String sql = "SELECT * FROM receita";
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
        Receita novoAux = new Receita();
		    formatarReceita(saida, novoAux);
        resultado.add(novoAux);
      }

      return resultado.isEmpty() ? false : true;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO [retornarTudo]: " + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean remover(Long id){
    String sql = "DELETE FROM receita "
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

      return true;

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean retornar(int id, Receita resultado) {
    String sql = "SELECT * FROM receita WHERE id = ?";
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
        formatarReceita(saida, resultado);
        return true;
      }

      return false;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO [retornar]: " + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean inserir(Receita receita) {
    String sql = "INSERT INTO receita (preco, nome, cardapio) VALUES (?, ?, ?);";
    Connection con = null;
    PreparedStatement cmd;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setBigDecimal(1, receita.getPreco());
      cmd.setString(2, receita.getNome());
      cmd.setInt(3, receita.getCardapio().ordinal());

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
