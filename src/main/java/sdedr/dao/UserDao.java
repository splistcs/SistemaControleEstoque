package sdedr.dao;

import sdedr.ctrl.PasswordEncrypt;
import sdedr.dao.dbcon.AcessoSQLite;
import sdedr.model.User;
import sdedr.model.Enum.TipoCadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
  private void formatarUser(User resultado, ResultSet saida) throws SQLException {
    resultado.setId(Long.valueOf(saida.getInt("id")));
    resultado.setNome(saida.getString("nome"));
    resultado.setSenha(saida.getString("senha"));
    resultado.setTipoCadastro(TipoCadastro.tipoCadastroInt(saida.getInt("tipoCadastro")));
  }

  public boolean primeiroLogin() {
    String sql = "SELECT * FROM user "
               + "WHERE tipoCadastro = 1";
    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      cmd = con.prepareStatement(sql);
      saida = cmd.executeQuery();
      return saida.next();

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean logar(String nome, String senha, User resultado) {
    String sql = "SELECT * FROM user "
               + "WHERE nome = ?";
    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;
    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }

      con.setAutoCommit(false);
      cmd = con.prepareStatement(sql);
      cmd.setString(1, nome);
      saida = cmd.executeQuery();

      if (saida.next()) {
        PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
        if (passwordEncrypt.checkPassword(senha, saida.getString("senha")) || senha.equals(saida.getString("senha"))) {
          formatarUser(resultado, saida);
          return true;
        }
      }
      return false;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoSQLite.desconectar(con);
    }
  }

  public boolean remover(Long id){
    String sql = "DELETE FROM user "
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


  public boolean retornarTudo(ArrayList<User> resultado) {
    String sql = "SELECT * FROM user";
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
        User novoAux = new User();
		    formatarUser(novoAux, saida);
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

  public boolean retornar(int id, User resultado) {
    String sql = "SELECT * FROM user WHERE id = ?";
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
        formatarUser(resultado, saida);
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

  

  public boolean retornar(String nome, User resultado) {
    String sql = "SELECT * FROM user WHERE nome = ?";
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
      cmd.setString(1, nome);
      saida = cmd.executeQuery();

      if (saida.next()) {
        formatarUser(resultado, saida);
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

  public boolean inserir(User user) {
    String sql = "INSERT INTO user (nome, senha, tipoCadastro) VALUES (?, ?, ?);";
    Connection con = null;
    PreparedStatement cmd;

    try {
      con = AcessoSQLite.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setString(1, user.getNome());

      String senhaCriptografada = new PasswordEncrypt().encryptPassword(user.getSenha());

      cmd.setString(2, senhaCriptografada);
      cmd.setInt(3, user.getTipoCadastro().ordinal());

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
