package sdedr.dao.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoSQLite {  

   private static final String DATABASE = "db/estoque";
   private static final String URL = "jdbc:sqlite:"+ DATABASE +".db";

  public static Connection conectar() {
    try {
      return DriverManager.getConnection(URL);
    } catch ( SQLException erro) {
      System.err.println("ERRO" + erro.getMessage());
      return null;
    }
  }

  public static void desconectar(Connection con) {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException erro) {
      System.err.println("ERRO" + erro.getMessage());
    }
  }
}
