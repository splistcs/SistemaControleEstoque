package sdedr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sdedr.model.Unidade;
import sdedr.dao.dbcon.AcessoSQLite;

public class UnidadeDao {

    private void formatarUnidade(Unidade resultado, ResultSet saida) throws SQLException{
        resultado.setId(Long.valueOf(saida.getInt("id")));
        resultado.setNome(saida.getString("nome"));
        resultado.setDescricao(saida.getString("descricao"));
    }

 public boolean retornar(int id, Unidade resultado) {
    String sql = "SELECT * FROM unidade WHERE id = ?";
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
        formatarUnidade(resultado, saida);
        
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

   public boolean retornarTudo(ArrayList<Unidade> resultado) {
    String sql = "SELECT * FROM unidade";
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
        Unidade novoAux = new Unidade();
		    formatarUnidade(novoAux, saida);
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
    String sql = "DELETE FROM unidade "
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
               + "WHERE id_u = ?";

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

 
   public boolean inserir(Unidade unidade) {
    String sql = "INSERT INTO unidade (nome, descricao) VALUES (?, ?);";
    Connection con = null;
    PreparedStatement cmd;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setString(1, unidade.getNome());
      cmd.setString(2, unidade.getDescricao());

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
