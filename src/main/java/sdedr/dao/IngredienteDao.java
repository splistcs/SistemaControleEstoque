package sdedr.dao;

import sdedr.dao.dbcon.AcessoSQLite;
import sdedr.model.Ingrediente;
import sdedr.model.Produto;
import sdedr.model.Receita;
import sdedr.model.Unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredienteDao {

  /* Como economizamos espaço no db, optei por fazer um função que traduz do DB --> model */
  private void formatarIngrediente(Ingrediente resultado, ResultSet saida) throws SQLException {
    ProdutoDao produtoDao = new ProdutoDao();
    Produto produto = new Produto();
    produtoDao.retornar(saida.getLong("id_p"), produto);

    ReceitaDao receitaDao = new ReceitaDao();
    Receita receita = new Receita();
    receitaDao.retornar(saida.getInt("id_r"), receita);

    resultado.setReceita(receita);
    resultado.setProduto(produto);
    resultado.setQuantidadeProduto(saida.getBigDecimal("quantidade"));
  }
  
  public boolean retornar(int id, Ingrediente resultado) {
    String sql = "SELECT * FROM ingrediente";
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

      if (saida.next()) {
        formatarIngrediente(resultado, saida);
        
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

  public boolean inserir(Ingrediente ingrediente) {
    Connection con = null;
    PreparedStatement cmd;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      String sql = "INSERT INTO ingrediente (id_p, id_r, quantidade) VALUES (?, ?, ?)";
      cmd = con.prepareStatement(sql);
      cmd.setInt(1, Math.toIntExact(ingrediente.getProduto().getId()));
      cmd.setInt(2, Math.toIntExact(ingrediente.getReceita().getId()));
      cmd.setBigDecimal(3, ingrediente.getQuantidadeProduto());
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

  public boolean remover(Long id){
    String sql = "DELETE FROM ingrediente "
               + "WHERE id_r = ?";
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


  public boolean retornarIngredientesReceita(Receita receita, ArrayList<Ingrediente> ingredientesReceita) {
    String sql = "SELECT * FROM ingrediente WHERE id_r = ?";
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
      cmd.setInt(1, Math.toIntExact(receita.getId()));
      saida = cmd.executeQuery();

      while (saida.next()) {
        Ingrediente ingrediente = new Ingrediente();
        formatarIngrediente(ingrediente, saida);
        ingredientesReceita.add(ingrediente);
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

      for (Ingrediente ingrediente : ingredientesReceita) {
        cmd = con.prepareStatement(sql);
        cmd.setInt(1, Math.toIntExact(ingrediente.getProduto().getId()));
        saida = cmd.executeQuery();

        if (saida.next()) {
          UnidadeDao unidadeDao = new UnidadeDao();
          Unidade unidade = new Unidade();

          ingrediente.getProduto().setUnidade(unidade);
          unidadeDao.retornar(saida.getInt("id_u"), ingrediente.getProduto().getUnidade());
        }
      }
    } catch (SQLException deuRuim) {
        System.out.println("ERRO" + deuRuim.getMessage());
        return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
      return !ingredientesReceita.isEmpty();
    }  
}
