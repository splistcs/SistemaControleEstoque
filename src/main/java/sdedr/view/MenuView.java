package sdedr.view;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sdedr.App;
import sdedr.ctrl.IngredienteCtrl;
import sdedr.ctrl.MovimentacaoCtrl;
import sdedr.ctrl.ProdutoCtrl;
import sdedr.ctrl.ReceitaCtrl;
import sdedr.model.Ingrediente;
import sdedr.model.MovimentacaoEstoqueDeProduto;
import sdedr.model.Produto;
import sdedr.model.User;
import sdedr.model.Enum.TipoCadastro;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class MenuView{
    private String nomeUsuario;
    private User user;            
    private Stage principalStage = new Stage(); 

    public MenuView(String nomeUsuario, User user) {
        this.nomeUsuario = nomeUsuario;
        this.user = user;
    }

    public void start(Stage primaryStage, App Loop) {
        primaryStage.setTitle("Sistema de Controle de Estoque Restaurante");

        Pane pane = new Pane();
        pane.setPrefSize(903, 452);
        pane.setStyle(DefineView.estiloTela);

        Button sairButton = new Button("Sair");
        sairButton.setLayoutX(55.00);
        sairButton.setLayoutY( 335.00);
        sairButton.setStyle(DefineView.estiloBotao);
        sairButton.setOnMousePressed(e -> sairButton.setStyle(DefineView.estiloBotaoAtivo));
        sairButton.setOnMouseReleased(e -> sairButton.setStyle(DefineView.estiloBotao));
        pane.getChildren().add(sairButton);

        sairButton.setOnAction(event -> {
            LoginView loginView = new LoginView(Loop);
            Scene loginTela = new Scene(loginView, 500, 450);

            principalStage.setScene(loginTela);
            principalStage.show();

            Stage menuJanela = (Stage) sairButton.getScene().getWindow();
            menuJanela.close();
        });

        Button CadastrosButton = new Button("Cadastros");
        CadastrosButton.setLayoutX(55.00);
        CadastrosButton.setLayoutY(53.00);
        CadastrosButton.setPrefWidth(164.00);
        CadastrosButton.setPrefHeight(47.00);
        CadastrosButton.setDisable(false);
        CadastrosButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrosButton.setStyle(DefineView.estiloBotaoBase);
        CadastrosButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { CadastrosButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        CadastrosButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { CadastrosButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(CadastrosButton);

        Button ReceitasButton = new Button("Mostrar Receitas");
        ReceitasButton.setLayoutX(55.00);
        ReceitasButton.setLayoutY(110.00);
        ReceitasButton.setPrefWidth(164.00);
        ReceitasButton.setPrefHeight(47.00);
        ReceitasButton.setDisable(false);
        ReceitasButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        ReceitasButton.setStyle(DefineView.estiloBotaoBase);
        ReceitasButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { ReceitasButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        ReceitasButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { ReceitasButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(ReceitasButton);

        Button InventarioButton = new Button("Gerar Inventário");
        InventarioButton.setLayoutX(55.00);
        InventarioButton.setLayoutY(167.00);
        InventarioButton.setPrefWidth(164.00);
        InventarioButton.setPrefHeight(47.00);
        InventarioButton.setDisable(false);
        InventarioButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        InventarioButton.setStyle(DefineView.estiloBotaoBase);
        InventarioButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { InventarioButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        InventarioButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { InventarioButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(InventarioButton);

        Button RelatorioButton = new Button("Gerar Relatório");
        RelatorioButton.setLayoutX(55.00);
        RelatorioButton.setLayoutY(224.00);
        RelatorioButton.setPrefWidth(164.00);
        RelatorioButton.setPrefHeight(47.00);
        RelatorioButton.setDisable(false);
        RelatorioButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        RelatorioButton.setStyle(DefineView.estiloBotaoBase);
        RelatorioButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { RelatorioButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        RelatorioButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { RelatorioButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(RelatorioButton);

        Button CardapiosButton = new Button("Mostrar Cardápios");
        CardapiosButton.setLayoutX(55.00);
        CardapiosButton.setLayoutY(281.00);
        CardapiosButton.setPrefWidth(164.00);
        CardapiosButton.setPrefHeight(47.00);
        CardapiosButton.setDisable(false);
        CardapiosButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CardapiosButton.setStyle(DefineView.estiloBotaoBase);
        CardapiosButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { CardapiosButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        CardapiosButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { CardapiosButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(CardapiosButton);

        TableView<Produto> inventarioTable = new TableView<>();
        inventarioTable.setLayoutX(257.00);
        inventarioTable.setLayoutY(55.00);
        inventarioTable.setPrefWidth(605.00);
        inventarioTable.setPrefHeight(363.00);
        inventarioTable.setVisible(false);
        inventarioTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Produto, Long> produtoIdCol = new TableColumn<>("Código");
        TableColumn<Produto, String> produtoNomeCol = new TableColumn<>("Produto");
        TableColumn<Produto, String> produtoQtdCol = new TableColumn<>("Quantidade");
        TableColumn<Produto, String> produtoUnidadeCol = new TableColumn<>("Unidade");
        TableColumn<Produto, String> produtoFracionavelCol = new TableColumn<>("Fracionável");

        produtoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        produtoNomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        produtoQtdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantidadeEstoque().toString()));
        produtoUnidadeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnidade() != null ? cellData.getValue().getUnidade().getNome() : ""));
        produtoFracionavelCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isPermiteFracionamento() ? "Sim" : "Não"));

        inventarioTable.getColumns().addAll(produtoIdCol, produtoNomeCol, produtoQtdCol, produtoUnidadeCol, produtoFracionavelCol);
        pane.getChildren().add(inventarioTable);

        TextArea AreaTexto = new TextArea();
        AreaTexto.setLayoutX(257.00);
        AreaTexto.setLayoutY(55.00);
        AreaTexto.setPrefWidth(605.00);
        AreaTexto.setPrefHeight(363.00);
        AreaTexto.setVisible(true);
        AreaTexto.setEditable(false);
        AreaTexto.setStyle(DefineView.estiloEntrada);
        pane.getChildren().add(AreaTexto);

        Label element7 = new Label("Olá, " + nomeUsuario);
        element7.setLayoutX(57.4375);
        element7.setLayoutY(15.796875);
        element7.setPrefWidth(105.8125);
        element7.setPrefHeight(18);
        element7.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        element7.setStyle(DefineView.estiloTexto);
        pane.getChildren().add(element7);

        Scene scene = new Scene(pane, 903, 452);
        primaryStage.setScene(scene);

        primaryStage.setResizable(true);

        primaryStage.widthProperty().addListener((obs, velho, novo) -> {
            AreaTexto.setPrefWidth(novo.doubleValue() - 298);
            inventarioTable.setPrefWidth(novo.doubleValue() - 298);
        });

        primaryStage.heightProperty().addListener((obs, velho, novo) -> {
            AreaTexto.setPrefHeight(novo.doubleValue() - 120);
            inventarioTable.setPrefHeight(novo.doubleValue() - 120);
        });

        AreaTexto.setPrefWidth(primaryStage.getWidth() - 298);
        AreaTexto.setPrefHeight(primaryStage.getHeight() - 120);
        inventarioTable.setPrefWidth(primaryStage.getWidth() - 298);
        inventarioTable.setPrefHeight(primaryStage.getHeight() - 120);

        primaryStage.show();

        InventarioButton.setOnAction(event -> {
            inventarioTable.getItems().clear();
            inventarioTable.setVisible(true);
            AreaTexto.setVisible(false);

            ProdutoCtrl produtoCtrl = new ProdutoCtrl();
            ArrayList<Produto> listaProdutos = produtoCtrl.getInventario();

            if (listaProdutos != null) {
                inventarioTable.getItems().addAll(listaProdutos);
            }
        });

        RelatorioButton.setOnAction(event -> {
            inventarioTable.setVisible(false);
            inventarioTable.getItems().clear();
            AreaTexto.setVisible(true);
            if (user.getTipoCadastro() == TipoCadastro.ADMIN || user.getTipoCadastro() == TipoCadastro.ALMOXARIFADO) {
                AreaTexto.setText("--- RELATÓRIO DE MOVIMENTAÇÕES ---\n\n");

                MovimentacaoCtrl movimentacaoCtrl = new MovimentacaoCtrl();
                ArrayList<MovimentacaoEstoqueDeProduto> listaMovimentacoes = movimentacaoCtrl.getRelatorio();

                for (MovimentacaoEstoqueDeProduto m : listaMovimentacoes) {
                    String linha;
                    if (m.getProduto().getUnidade() != null) {
                        linha = "Codigo: " + m.getId() + " | Produto: " + m.getProduto().getNome() + " | Tipo: " + m.getTipoMovimentacao() + " | OBS.: " + m.getObservacao() + " | Qtd: " + m.getQuantidade() + " " + m.getProduto().getUnidade().getNome() + " | Usuario: " + m.getUserName() + " | Data: " + m.getDataHora() + " | Valor unitário (R$): " + m.getPrecoUnitario() + " | Valor Total (R$): " + m.getPrecoTotal() + "\n\n";  
                    }
                    else{
                        linha = "Codigo: " + m.getId() + " | Produto: " + m.getProduto().getNome() + " | Tipo: " + m.getTipoMovimentacao() + " | OBS.: " + m.getObservacao() + " | Qtd: " + m.getQuantidade() + " | Usuario: " + m.getUserName() + " | Data: " + m.getDataHora() + " | Valor unitário (R$): " + m.getPrecoUnitario() + " | Valor Total (R$): " + m.getPrecoTotal() + "\n\n";
                    }
                    AreaTexto.appendText(linha);
                }
            }
            else {
                AreaTexto.setVisible(true);
                AreaTexto.setText("Acesso negado. Você não tem permissão para acessar o relatório.");
            }
        });
        

        CardapiosButton.setOnAction(event -> {
            inventarioTable.setVisible(false);
            inventarioTable.getItems().clear();
            AreaTexto.setVisible(true);
            if (user.getTipoCadastro() == TipoCadastro.ADMIN || user.getTipoCadastro() == TipoCadastro.CHEF){
                CardapioView cardapioView = new CardapioView();
                cardapioView.start(new Stage());
            } else {
                AreaTexto.setText("Acesso negado. Você não tem permissão para acessar aos cardápios.");
            }
        });

        ReceitasButton.setOnAction(event -> {
            inventarioTable.setVisible(false);
            inventarioTable.getItems().clear();
            AreaTexto.setVisible(true);
            if(user.getTipoCadastro() == TipoCadastro.ADMIN) {
                AreaTexto.setText("--- RECEITAS CADASTRADAS ---\n\n");
                ReceitaCtrl receitaCtrl = new ReceitaCtrl();
                receitaCtrl.prepararReceitas();
                ArrayList<sdedr.model.Receita> listaReceitas = receitaCtrl.getReceitas();

                for (sdedr.model.Receita r : listaReceitas) {
                    String linha = "Codigo: " + r.getId() + " | Receita: " + r.getNome() + " | Preço: R$ " + r.getPreco() + "\n";
                    AreaTexto.appendText(linha);

                    ArrayList<Ingrediente> ingredientes = new ArrayList<>();
                    IngredienteCtrl ingredienteCtrl = new IngredienteCtrl();
                    ingredienteCtrl.retornarIngredientesReceita(r, ingredientes);

                    if (ingredientes.isEmpty() || ingredientes == null) {
                        AreaTexto.appendText("  (Sem ingredientes cadastrados)\n");
                    } else {
                        

                        AreaTexto.appendText("     Ingredientes:\n");
                    }
                    for (Ingrediente i : ingredientes) {
                        String linhaIngrediente;
                        if (i.getProduto().getUnidade() != null) {
                            linhaIngrediente = "    - " + i.getProduto().getNome() + ": " + i.getQuantidadeProduto() + " " + i.getProduto().getUnidade().getNome() + "\n";
                        } else {
                            linhaIngrediente = "    - " + i.getProduto().getNome() + ": " + i.getQuantidadeProduto() + "\n";
                        }
                        AreaTexto.appendText(linhaIngrediente);
                    }
                    AreaTexto.appendText("\n");
                }
            }
            else if (user.getTipoCadastro() == TipoCadastro.CHEF){
                ReceitasView receitasView = new ReceitasView(user);
                receitasView.start(new Stage());         
            }else{
                AreaTexto.setText("Acesso negado. Você não tem permissão para acessar as receitas.");
            }
        });

        CadastrosButton.setOnAction(event -> {
            inventarioTable.setVisible(false);
            inventarioTable.getItems().clear();
            AreaTexto.setVisible(true);
            if (user.getTipoCadastro() == TipoCadastro.ADMIN){
                CadastrosView cadastrosView = new CadastrosView();
                cadastrosView.start(new Stage());
            }
            else{
                AreaTexto.setText("Acesso negado. Você não tem permissão para acessar o menu de cadastros.");
            }
        });
        
    }
}


