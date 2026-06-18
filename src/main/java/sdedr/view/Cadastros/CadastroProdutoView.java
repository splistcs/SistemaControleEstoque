package sdedr.view.Cadastros;

import java.math.BigDecimal;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sdedr.dao.ProdutoDao;
import sdedr.dao.UnidadeDao;
import sdedr.model.Produto;
import sdedr.model.Unidade;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CadastroProdutoView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Produtos");

        Pane pane = new Pane();
        pane.setPrefSize(899, 450);
        pane.setStyle("-fx-background-color: #eeeeee;");

        Label CadastrarProdutoTXT = new Label("Cadastro de Produto");
        CadastrarProdutoTXT.setLayoutX(24.5625);
        CadastrarProdutoTXT.setLayoutY(19.359375);
        CadastrarProdutoTXT.setPrefWidth(150);
        CadastrarProdutoTXT.setPrefHeight(19);
        CadastrarProdutoTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrarProdutoTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(CadastrarProdutoTXT);

        TextField nomeInput = new TextField("");
        nomeInput.setLayoutX(78.00);
        nomeInput.setLayoutY(96.00);
        nomeInput.setPrefWidth(224.00);
        nomeInput.setPrefHeight(24.00);
        nomeInput.setPromptText("Nome do novo produto");
        nomeInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        nomeInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(nomeInput);

        Label element6 = new Label("Insira as informações do cadastro");
        element6.setLayoutX(56);
        element6.setLayoutY(55);
        element6.setPrefWidth(235);
        element6.setPrefHeight(17);
        element6.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        element6.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(element6);

        Button ConfirmarButton = new Button("Confirmar Cadastro");
        ConfirmarButton.setLayoutX(775.00);
        ConfirmarButton.setLayoutY(365.61);
        ConfirmarButton.setPrefWidth(106.00);
        ConfirmarButton.setPrefHeight(69.00);
        ConfirmarButton.setDisable(false);
        ConfirmarButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        ConfirmarButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        ConfirmarButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { ConfirmarButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        ConfirmarButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { ConfirmarButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(ConfirmarButton);

        TextField precoAtualInput = new TextField("");
        precoAtualInput.setLayoutX(77.56);
        precoAtualInput.setLayoutY(131.11);
        precoAtualInput.setPrefWidth(224.00);
        precoAtualInput.setPrefHeight(24.00);
        precoAtualInput.setPromptText("Preço Atual do novo produto");
        precoAtualInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        precoAtualInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(precoAtualInput);

        TextField estoqueMinimoInput = new TextField("");
        estoqueMinimoInput.setLayoutX(77.56);
        estoqueMinimoInput.setLayoutY(162.11);
        estoqueMinimoInput.setPrefWidth(224.00);
        estoqueMinimoInput.setPrefHeight(24.00);
        estoqueMinimoInput.setPromptText("Estoque mínimo do produto");
        estoqueMinimoInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        estoqueMinimoInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(estoqueMinimoInput);

        Label permiteFracTXT = new Label("Permite fracionamento?");
        permiteFracTXT.setLayoutX(58);
        permiteFracTXT.setLayoutY(203.359375);
        permiteFracTXT.setPrefWidth(187);
        permiteFracTXT.setPrefHeight(21);
        permiteFracTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        permiteFracTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(permiteFracTXT);

        ComboBox<String> permiteFracionamentoCmb = new ComboBox<>();


        ObservableList<String> opcoes = FXCollections.observableArrayList(
            "NÃO PERMITE",
            "PERMITE"
        );

        permiteFracionamentoCmb.setItems(opcoes);

        permiteFracionamentoCmb.setPromptText("Selecione a permissão");
        permiteFracionamentoCmb.setLayoutX(80.00);
        permiteFracionamentoCmb.setLayoutY(226.00);

        pane.getChildren().add(permiteFracionamentoCmb);


        Label UnidadeTXT = new Label("Unidade");
        UnidadeTXT.setLayoutX(58.5625);
        UnidadeTXT.setLayoutY(272.359375);
        UnidadeTXT.setPrefWidth(105.8125);
        UnidadeTXT.setPrefHeight(18);
        UnidadeTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        UnidadeTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(UnidadeTXT);

        Label confirmadoTXT = new Label("cadastrado com sucesso!");
        confirmadoTXT.setLayoutX(713);
        confirmadoTXT.setLayoutY(341);
        confirmadoTXT.setPrefWidth(206);
        confirmadoTXT.setPrefHeight(18);
        confirmadoTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        confirmadoTXT.setStyle("-fx-text-fill: #1b1b1b;");
        confirmadoTXT.setVisible(false);
        pane.getChildren().add(confirmadoTXT);


        ComboBox<Unidade> unidadeCmb = new ComboBox<>();
        unidadeCmb.setPromptText("Selecione a Unidade");

        UnidadeDao unidadeDao = new UnidadeDao();
        ArrayList<Unidade> listaUnidades = new ArrayList<>();
        
        unidadeDao.retornarTudo(listaUnidades);
        ObservableList<Unidade> opcoesUnidade = FXCollections.observableArrayList(listaUnidades);
        
        unidadeCmb.setItems(opcoesUnidade);
        
        unidadeCmb.setLayoutX(80.00);
        unidadeCmb.setLayoutY(300.00);
        pane.getChildren().add(unidadeCmb); 

        Scene scene = new Scene(pane, 899, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ConfirmarButton.setOnAction(event -> {
            int selecaoPermiteFracionamento = permiteFracionamentoCmb.getSelectionModel().getSelectedIndex();
            Unidade unidadeSelecionada = unidadeCmb.getSelectionModel().getSelectedItem();

            if (unidadeSelecionada != null) {
                
                ProdutoDao produtoDao = new ProdutoDao();
                Produto produto = new Produto();

                produto.setNome(nomeInput.getText());
                produto.setEstoqueMinimo(BigDecimal.valueOf(Double.parseDouble(estoqueMinimoInput.getText())));
                produto.setQuantidadeEstoque(BigDecimal.valueOf(0));
                produto.setAtivo(true);
                produto.setUnidade(unidadeSelecionada);
                produto.setPermiteFracionamento(selecaoPermiteFracionamento);
                produto.setPrecoAtual(BigDecimal.valueOf(Double.parseDouble(precoAtualInput.getText())));

                if(produtoDao.inserir(produto)){
                    confirmadoTXT.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                    pause.setOnFinished(e -> confirmadoTXT.setVisible(false));
                    pause.play();
                }
            } else {
                System.out.println("Unidade Inválida");
            }     
        });
    }
}