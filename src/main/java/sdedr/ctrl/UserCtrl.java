package sdedr.ctrl;

import java.util.ArrayList;

import sdedr.model.User;
import sdedr.model.Enum.TipoCadastro;
import sdedr.dao.UserDao;

public class UserCtrl {

  private User usuarioAtual = new User();
  private ArrayList<User> usuarios = new ArrayList<>();

  public UserCtrl() { }

  public boolean primeiroUso() {
    UserDao userDao = new UserDao();
    return userDao.primeiroLogin();
  }

  public boolean cadastrarPrimeiroLogin(String nome, String senha) {
    User userNovo = new User();
    userNovo.setNome(nome);
    userNovo.setSenha(senha);
    userNovo.setTipoCadastro(TipoCadastro.tipoCadastroInt(1));
    UserDao userDao = new UserDao();
    return userDao.inserir(userNovo);
  }

  public boolean prepararUsuarios() {
    UserDao userDao = new UserDao();
    this.usuarios.clear();
    return userDao.retornarTudo(this.usuarios);
  }

  public ArrayList<User> getUsuarios() {
    return this.usuarios;
  }

  public boolean cadastrarUsuario(User user) {
    UserDao userDao = new UserDao();
    return userDao.inserir(user);
  }

  public boolean removerUsuario(Long id) {
    UserDao userDao = new UserDao();
    return userDao.remover(id);
  }

  public boolean confirmarLogin(String nome, String senha) {
    UserDao userDao = new UserDao();
    return userDao.logar(nome, senha, usuarioAtual);
  }

  public boolean retornarUsuario(String nome, User resultado) {
    UserDao userDao = new UserDao();
    return userDao.retornar(nome, resultado);
  }  

  /* utilizado em View MovimentacaoEstoqueDeProduto */
  public boolean confirmarPermissao(int acesso) {
    return this.usuarioAtual.getTipoCadastro().equals(TipoCadastro.tipoCadastroInt(acesso));
  }

  public String retornarNomeOuCadastro(boolean nome) {
    return nome ? this.usuarioAtual.getNome() : this.usuarioAtual.getTipoCadastro().name();
  }  

  public int retornarId() {
    return this.usuarioAtual.getId().intValue();
  }

}
