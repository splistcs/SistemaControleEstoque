package sdedr.view;

import sdedr.App;
import sdedr.ctrl.CtrlCtrl;
import sdedr.model.Produto;
import sdedr.model.MovimentacaoEstoqueDeProduto;
import sdedr.model.Enum.TipoMovimentacao;
import sdedr.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.util.StringConverter;

public class MovimentacaoEstoqueView extends GridPane {

  public MovimentacaoEstoqueView (App loop, CtrlCtrl aux) {
    this.setAlignment(Pos.TOP_CENTER);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(20, 25, 25, 25));
    this.setStyle(DefineView.estiloTela);

    Text tituloText = new Text("SDEDR");
    tituloText.setStyle("-fx-font-size: 20px; " + DefineView.estiloTexto);
    this.add(tituloText, 0, 0, 2, 1);

    Label usuarioLabel = new Label("Usuário:");
    this.add(usuarioLabel, 0, 1);
    Text usuarioText = new Text(aux.userCtrl.retornarNomeOuCadastro(true));
    this.add(usuarioText, 1, 1);

    Label contaLabel = new Label("Conta:");
    this.add(contaLabel, 2, 1);
    Text contaText = new Text(aux.userCtrl.retornarNomeOuCadastro(false));
    this.add(contaText, 3, 1);

    Button sairButton = new Button("Sair");
    HBox sairHBox = new HBox(sairButton);
    sairHBox.setAlignment(Pos.BOTTOM_RIGHT);
    sairButton.setStyle(DefineView.estiloBotao);
    sairButton.setOnMousePressed(e -> sairButton.setStyle(DefineView.estiloBotaoAtivo));
    sairButton.setOnMouseReleased(e -> sairButton.setStyle(DefineView.estiloBotao));
    this.add(sairHBox, 4, 0);

    sairButton.setOnAction(event -> {
      loop.vezDeLoginView();
    });

    Button inserirButton = new Button("Movimentar");
    HBox inserirHBox = new HBox(inserirButton);
    inserirHBox.setAlignment(Pos.BOTTOM_RIGHT);
    inserirButton.setStyle(DefineView.estiloBotao);
    inserirButton.setOnMousePressed(e -> inserirButton.setStyle(DefineView.estiloBotaoAtivo));
    inserirButton.setOnMouseReleased(e -> inserirButton.setStyle(DefineView.estiloBotao));
    this.add(inserirHBox, 4, 1);



    TableView<MovimentacaoEstoqueDeProduto> movTableView = tabelaMovimentacaoEstoqueDeProduto();        
    Text subTituloText = new Text("Histórico:");
    this.add(subTituloText, 0, 2, 2, 1);
    if (aux.movimentacaoCtrl.prepararMovimentacoes(aux.userCtrl.retornarId())) {
      movTableView.getItems().addAll(aux.movimentacaoCtrl.getMovimentacaoEstoqueDeProdutos());
    }
    this.add(movTableView, 0, 3, 4, 1);

    inserirButton.setOnAction(event -> {
      Stage principalStage = (Stage) this.getScene().getWindow();
      
      formularioInsercao(principalStage, aux);
      
      movTableView.getItems().clear();
      
      if (aux.movimentacaoCtrl.prepararMovimentacoes(aux.userCtrl.retornarId())) {
        movTableView.getItems().clear();
        movTableView.getItems().addAll(aux.movimentacaoCtrl.getMovimentacaoEstoqueDeProdutos());
      }
    });
  }

  private void formularioInsercao(Stage principalStage, CtrlCtrl aux) {
    Stage secundarioStage = new Stage();
    secundarioStage.initModality(Modality.WINDOW_MODAL);
    secundarioStage.initOwner(principalStage);
    secundarioStage.setTitle("Movimentar Estoque");
    secundarioStage.setResizable(false);

    GridPane formularioGrid = new GridPane();
    formularioGrid.setAlignment(Pos.CENTER);
    formularioGrid.setHgap(10);
    formularioGrid.setVgap(10);
    formularioGrid.setPadding(new Insets(15));

    ComboBox<Produto> produtoComboBox = new ComboBox<>();
    produtoComboBox.setPromptText("Selecione o Produto");
    produtoComboBox.setMaxWidth(Double.MAX_VALUE);

    produtoComboBox.setConverter(new StringConverter<Produto>() {
      @Override
      public String toString(Produto produto) {
        return produto.getNome();
      }

      @Override
      public Produto fromString(String string) {
        return null;
      }
    });    

    ComboBox<TipoMovimentacao> tipoComboBox = new ComboBox<>();
    tipoComboBox.setPromptText("Tipo de Movimentação");
    tipoComboBox.setMaxWidth(Double.MAX_VALUE);
    tipoComboBox.getItems().add(TipoMovimentacao.ENTRADA);
    tipoComboBox.getItems().add(TipoMovimentacao.SAIDA);
    tipoComboBox.getItems().add(TipoMovimentacao.AJUSTE);
    tipoComboBox.getItems().add(TipoMovimentacao.PERDA);

    if(aux.produtoCtrl.prepararProdutos()) {
        produtoComboBox.getItems().setAll(aux.produtoCtrl.getProdutos());
    }
  
    TextField quantField = new TextField();
    quantField.setPromptText("Quantidade");    

    TextField precoField = new TextField();
    precoField.setPromptText("Valor Unitário");

    TextField obsField = new TextField();
    obsField.setPromptText("Notas");

    DatePicker datePicker = new DatePicker();

    datePicker.setOnAction(e -> {
      LocalDate date = datePicker.getValue();
    });

    formularioGrid.add(new Label("Produto:"), 0, 0);
    formularioGrid.add(produtoComboBox, 1, 0);    
    formularioGrid.add(new Label("Tipo:"), 0, 1);
    formularioGrid.add(tipoComboBox, 1, 1);
    formularioGrid.add(new Label("Quantidade:"), 0, 2);
    formularioGrid.add(quantField, 1, 2);
    formularioGrid.add(new Label("Preço Unit.:"), 0, 3);
    formularioGrid.add(precoField, 1, 3);
    formularioGrid.add(new Label("Validade:"), 0, 4);
    formularioGrid.add(datePicker, 1, 4);
    formularioGrid.add(new Label("Observação:"), 0, 5);
    formularioGrid.add(obsField, 1, 5);
    formularioGrid.setColumnSpan(obsField, 2 );

    Button inserirButton = new Button("Movimentar");
    inserirButton.setStyle(DefineView.estiloBotao);
    inserirButton.setOnMousePressed(e -> inserirButton.setStyle(DefineView.estiloBotaoAtivo));
    inserirButton.setOnMouseReleased(e -> inserirButton.setStyle(DefineView.estiloBotao));
    formularioGrid.add(inserirButton, 1, 6); 

    inserirButton.setOnAction(e -> {
      Produto e0 = produtoComboBox.getSelectionModel().getSelectedItem();
      TipoMovimentacao e1 = tipoComboBox.getSelectionModel().getSelectedItem();
      BigDecimal e2 = new BigDecimal(quantField.getText());
      BigDecimal e3 = new BigDecimal(precoField.getText());
      LocalDate e4 = datePicker.getValue();
      String e5 = obsField.getText();

      MovimentacaoEstoqueDeProduto entradaTmp = new MovimentacaoEstoqueDeProduto();
      entradaTmp.setDataHora(LocalDateTime.now());
      entradaTmp.setTipoMovimentacao(e1);
      entradaTmp.setQuantidade(e2);
      entradaTmp.setPrecoUnitario(e3);
      entradaTmp.setValidadeLote(e4);
      entradaTmp.setObservacao(e5);
      entradaTmp.setProduto(e0);
      User userTmp = new User();
      userTmp.setId(Long.valueOf(aux.userCtrl.retornarId()));
      entradaTmp.setUser(userTmp);

      if(e0 != null && e1 != null) {
        Stage terceiraStage = new Stage();
        terceiraStage.initModality(Modality.WINDOW_MODAL);
        terceiraStage.initOwner(principalStage);
        terceiraStage.setTitle("Confirmação");

        GridPane senhaGridPane = new GridPane();
        senhaGridPane.setAlignment(Pos.CENTER);
        senhaGridPane.setHgap(10);
        senhaGridPane.setVgap(10);
        senhaGridPane.setPadding(new Insets(15));

        Label senhaLabel = new Label("Senha:");
        senhaGridPane.add(senhaLabel, 0, 0);
        PasswordField senhaPasswordField = new PasswordField();
        senhaGridPane.add(senhaPasswordField, 1, 0);
        
        Button logarButton = new Button("Confirmar");
        logarButton.setStyle(DefineView.estiloBotao);
        logarButton.setOnMousePressed(evt -> logarButton.setStyle(DefineView.estiloBotaoAtivo));
        logarButton.setOnMouseReleased(evt -> logarButton.setStyle(DefineView.estiloBotao));
        HBox grupoHBox = new HBox(10);
        grupoHBox.setAlignment(Pos.BOTTOM_RIGHT);
        grupoHBox.getChildren().add(logarButton);
        senhaGridPane.add(grupoHBox, 1, 1);
        
        logarButton.setOnAction(event -> {
            String senha = senhaPasswordField.getText();

            if(senha.isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR, "senha vazia");
              alert.showAndWait();
              return;
            } else {
              if(aux.userCtrl.confirmarLogin(aux.userCtrl.retornarNomeOuCadastro(true), senha)) {
                  if(!aux.movimentacaoCtrl.movimentarEstoque(entradaTmp)) {
                  Alert alert2 = new Alert(null);
                  alert2.showAndWait();
                }

                terceiraStage.close();
                secundarioStage.close();
              } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Senha incorreta.");
                alert.showAndWait();
              }
            }
        });

        Scene cena = new Scene(senhaGridPane, 300, 150);
        terceiraStage.setScene(cena);
        terceiraStage.showAndWait();

      } else { 
        Alert alert = new Alert(Alert.AlertType.WARNING, "ERRO:  FALHA AO CADASTRAR, CONFIRA SE OS DADOS ESTÃO CORRETOS!");
        alert.showAndWait();
        secundarioStage.close();
      }
    });

    Scene scene = new Scene(formularioGrid, 720, 320);
    secundarioStage.setScene(scene);
    secundarioStage.showAndWait();
  }

  private static TableView<MovimentacaoEstoqueDeProduto> tabelaMovimentacaoEstoqueDeProduto() {
    TableView<MovimentacaoEstoqueDeProduto> movimentacaoEstoqueDeProdutoTableView = new TableView<>();
    movimentacaoEstoqueDeProdutoTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<MovimentacaoEstoqueDeProduto, Long> idColuna = new TableColumn<>("id");
    TableColumn<MovimentacaoEstoqueDeProduto, LocalDateTime> dataColuna = new TableColumn<>("data");
    TableColumn<MovimentacaoEstoqueDeProduto, BigDecimal> quantidadeColuna = new TableColumn<>("quant.");
    TableColumn<MovimentacaoEstoqueDeProduto, BigDecimal> precoUnitarioColuna = new TableColumn<>("preço unit.");
    TableColumn<MovimentacaoEstoqueDeProduto, LocalDate> validadeColuna = new TableColumn<>("validade");
    TableColumn<MovimentacaoEstoqueDeProduto, String> obsColuna = new TableColumn<>("obs.");
    TableColumn<MovimentacaoEstoqueDeProduto, TipoMovimentacao> tipoColuna = new TableColumn<>("tipo");

    TableColumn<MovimentacaoEstoqueDeProduto, String> produtoColuna = new TableColumn<>("nome");
	
    idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
    dataColuna.setCellValueFactory(new PropertyValueFactory<>("dataHora"));
    quantidadeColuna.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    precoUnitarioColuna.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
    validadeColuna.setCellValueFactory(new PropertyValueFactory<>("validadeLote"));
    obsColuna.setCellValueFactory(new PropertyValueFactory<>("observacao"));
    tipoColuna.setCellValueFactory(new PropertyValueFactory<>("tipoMovimentacao"));

    produtoColuna.setCellValueFactory(cellData -> {
      MovimentacaoEstoqueDeProduto movimentacaoEstoqueDeProduto = cellData.getValue();
      if (movimentacaoEstoqueDeProduto != null && movimentacaoEstoqueDeProduto.getProduto().getNome() != null) {
        return new SimpleStringProperty(movimentacaoEstoqueDeProduto.getProduto().getNome());
      } else {
        return new SimpleStringProperty("");
      }
    });

    movimentacaoEstoqueDeProdutoTableView.getColumns().addAll(idColuna, produtoColuna, quantidadeColuna, dataColuna, 
        precoUnitarioColuna, tipoColuna, validadeColuna, obsColuna);

    return movimentacaoEstoqueDeProdutoTableView;
  } 
}
