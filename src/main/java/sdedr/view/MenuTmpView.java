package sdedr.view;

import sdedr.App;
import sdedr.ctrl.CtrlCtrl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MenuTmpView extends GridPane {

  public MenuTmpView (App loop, CtrlCtrl aux) {
    this.setAlignment(Pos.CENTER);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(25, 25, 25, 25));

    Text tituloText = new Text("SDEDR");
    this.add(tituloText, 0, 0, 2, 1);

    Label usuarioLabel = new Label("Usuário:");
    this.add(usuarioLabel, 0, 1);
    Text usuarioText = new Text(aux.userCtrl.retornarNomeOuCadastro(true));
    this.add(usuarioText, 1, 1);

    Label contaLabel = new Label("Conta:");
    this.add(contaLabel, 0, 2);
    Text contaText = new Text(aux.userCtrl.retornarNomeOuCadastro(false));
    this.add(contaText, 1, 2);

    if(aux.userCtrl.confirmarPermissao(1)) {
      Button manterProdutoButton = new Button("Manter Produto");
      this.add(manterProdutoButton, 1, 3);

      manterProdutoButton.setOnAction(event -> {
        loop.vezDeManterGeralView(1, aux);
      });

      Button manterReceitaButton = new Button("Manter Receita");
      this.add(manterReceitaButton, 1, 4);

      manterReceitaButton.setOnAction(event -> {
        loop.vezDeManterGeralView(2, aux);
      });
    }    

    Button sairButton = new Button("Sair");
    HBox sairHBox = new HBox(sairButton);
    sairHBox.setAlignment(Pos.BOTTOM_RIGHT);
    this.add(sairHBox, 4, 0);
    sairButton.setOnAction(event -> {
      loop.vezDeLoginView();
    });
  }
}
