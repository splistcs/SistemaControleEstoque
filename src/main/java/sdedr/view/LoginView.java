package sdedr.view;

import sdedr.App;
import sdedr.ctrl.CtrlCtrl;
import sdedr.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends GridPane {

  public LoginView(App loop) {
    CtrlCtrl ctrlCtrl = new CtrlCtrl();
    this.setAlignment(Pos.CENTER);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(25, 25, 25, 25));

    Font blexFont = Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 20);

    Text tituloText = new Text("SDEDR - Olá, faça login:");
    tituloText.setFont(blexFont);
    this.add(tituloText, 0, 0, 2, 1);

    Label usuarioLabel = new Label("Usuário:");
    this.add(usuarioLabel, 0, 1);

    TextField usuarioTextField = new TextField();
    this.add(usuarioTextField, 1, 1);

    Label senhaLabel = new Label("Senha:");
    this.add(senhaLabel, 0, 2);

    PasswordField senhaPasswordField = new PasswordField();
    this.add(senhaPasswordField, 1, 2);

    if (ctrlCtrl.userCtrl.primeiroUso()) {
      Button logarButton = new Button("Logar");     
      logarButton.setStyle(DefineView.estiloBotao);
      logarButton.setOnMousePressed(e -> logarButton.setStyle(DefineView.estiloBotaoAtivo));
      logarButton.setOnMouseReleased(e -> logarButton.setStyle(DefineView.estiloBotao));
      HBox grupoHBox = new HBox(10);
      grupoHBox.setAlignment(Pos.BOTTOM_RIGHT);
      grupoHBox.getChildren().add(logarButton);
      this.add(grupoHBox, 1, 4);

      final Text respostaDeLogarText = new Text();
      this.add(respostaDeLogarText, 1, 6);

      logarButton.setOnAction(event -> {
        String usuario = usuarioTextField.getText();
        String senha = senhaPasswordField.getText();

        if(usuario.isEmpty() || senha.isEmpty()) {
          respostaDeLogarText.setText("Por favor preencha todos os campos.");
        } else {
          if(ctrlCtrl.userCtrl.confirmarLogin(usuario, senha)) {
            respostaDeLogarText.setText("Ok.");
            if (ctrlCtrl.userCtrl.confirmarPermissao(3)) {
              loop.vezDeMovimentacaoEstoqueView(ctrlCtrl);
            } else {
            Stage stageMenu = new Stage();
            User user = new User();
            ctrlCtrl.userCtrl.retornarUsuario(usuario, user);

            MenuView menu = new MenuView(usuario, user);
          
            menu.start(stageMenu, loop);

            Stage janelaLogin = (Stage) logarButton.getScene().getWindow();
            janelaLogin.close(); 
            }
          } else {
            respostaDeLogarText.setText("Valores errados.");
          }
        }
      });
    } else {
      tituloText.setText("SDEDR - Primeiro uso\nEsse será o primeiro ADMIN user,\nenquanto não criar outros salve bem a senha.\n Se não for a sua primeira vez vendo\n essa tela, leia o ReadMe.txt");
      Button cadastrarButton = new Button("Salvar");
      HBox grupoHBox = new HBox(10);
      grupoHBox.setAlignment(Pos.BOTTOM_RIGHT);
      grupoHBox.getChildren().add(cadastrarButton);
      this.add(grupoHBox, 1, 4);

      final Text respostaDeCadastrarText = new Text();
      this.add(respostaDeCadastrarText, 1, 6);

      cadastrarButton.setOnAction(event -> {
        String usuario = usuarioTextField.getText();
        String senha = senhaPasswordField.getText();

        if(usuario.isEmpty() || senha.isEmpty()) {
          respostaDeCadastrarText.setText("Por favor preencha todos os campos.");
        } else {
          if(ctrlCtrl.userCtrl.cadastrarPrimeiroLogin(usuario, senha)) {
            respostaDeCadastrarText.setText("Ok. Reinicie o app para logar novamente!");
          } else {
            respostaDeCadastrarText.setText("Tente outra vez.");
          }
        }
      });
    }
  }
}
