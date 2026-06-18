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
import sdedr.dao.IngredienteDao;
import sdedr.dao.ProdutoDao;
import sdedr.dao.ReceitaDao;
import sdedr.model.Ingrediente;
import sdedr.model.Produto;
import sdedr.model.Receita;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CadastroIngredienteView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Ingredientes");

        Pane pane = new Pane();
        pane.setPrefSize(899, 450);
        pane.setStyle("-fx-background-color: #eeeeee;");

        Label CadastrarIngredienteTXT = new Label("Cadastro de Ingrediente");
        CadastrarIngredienteTXT.setLayoutX(24.5625);
        CadastrarIngredienteTXT.setLayoutY(19.359375);
        CadastrarIngredienteTXT.setPrefWidth(167);
        CadastrarIngredienteTXT.setPrefHeight(19);
        CadastrarIngredienteTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrarIngredienteTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(CadastrarIngredienteTXT);

        Label element6 = new Label("escolha a receita");
        element6.setLayoutX(104);
        element6.setLayoutY(76);
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

        ComboBox<Receita> receitaCmb = new ComboBox<>();
        receitaCmb.setPromptText("Selecione a Receita");

        ReceitaDao receitaDao = new ReceitaDao();
        ArrayList<Receita> listaReceitas = new ArrayList<>();
        
        receitaDao.retornarTudo(listaReceitas);
        ObservableList<Receita> opcoesReceita = FXCollections.observableArrayList(listaReceitas);
        
        receitaCmb.setItems(opcoesReceita);
        
        receitaCmb.setLayoutX(101.00);
        receitaCmb.setLayoutY(108.00);
        pane.getChildren().add(receitaCmb);


        Label escolhaprodutoTXT = new Label("escolha o produto");
        escolhaprodutoTXT.setLayoutX(300.5625);
        escolhaprodutoTXT.setLayoutY(75.359375);
        escolhaprodutoTXT.setPrefWidth(129);
        escolhaprodutoTXT.setPrefHeight(15);
        escolhaprodutoTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        escolhaprodutoTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(escolhaprodutoTXT);

        ComboBox<Produto> produtoCmb = new ComboBox<>();
        produtoCmb.setPromptText("Selecione o Produto");

        ProdutoDao produtoDao = new ProdutoDao();
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        
        produtoDao.retornarTudo(listaProdutos);
        ObservableList<Produto> opcoesProduto = FXCollections.observableArrayList(listaProdutos);
        
        produtoCmb.setItems(opcoesProduto);
        
        produtoCmb.setLayoutX(300.56);
        produtoCmb.setLayoutY(109.11);
        pane.getChildren().add(produtoCmb);

        Label quantidadeTXT = new Label("insira a quantidade do produto");
        quantidadeTXT.setLayoutX(97.5625);
        quantidadeTXT.setLayoutY(195.359375);
        quantidadeTXT.setPrefWidth(209);
        quantidadeTXT.setPrefHeight(19);
        quantidadeTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        quantidadeTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(quantidadeTXT);

        TextField quantidadeInput = new TextField("");
        quantidadeInput.setLayoutX(102.56);
        quantidadeInput.setLayoutY(232.11);
        quantidadeInput.setPrefWidth(105.81);
        quantidadeInput.setPrefHeight(24.00);
        quantidadeInput.setPromptText("quantidade");
        quantidadeInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        quantidadeInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(quantidadeInput);

        Label confirmadoTXT = new Label("cadastrado com sucesso!");
        confirmadoTXT.setLayoutX(713);
        confirmadoTXT.setLayoutY(341);
        confirmadoTXT.setPrefWidth(206);
        confirmadoTXT.setPrefHeight(18);
        confirmadoTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        confirmadoTXT.setStyle("-fx-text-fill: #1b1b1b;");
        confirmadoTXT.setVisible(false);
        pane.getChildren().add(confirmadoTXT);


        Scene scene = new Scene(pane, 899, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ConfirmarButton.setOnAction(event -> {
            Receita receitaSelecionada = receitaCmb.getSelectionModel().getSelectedItem();
            Produto produtoSelecionado = produtoCmb.getSelectionModel().getSelectedItem();

            if (receitaSelecionada != null && produtoSelecionado != null){
                IngredienteDao ingredienteDao = new IngredienteDao();
                Ingrediente ingrediente = new Ingrediente();

                ingrediente.setProduto(produtoSelecionado);
                ingrediente.setReceita(receitaSelecionada);
                ingrediente.setQuantidadeProduto(BigDecimal.valueOf(Double.parseDouble(quantidadeInput.getText())));

            if(ingredienteDao.inserir(ingrediente)){
                    confirmadoTXT.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                    pause.setOnFinished(e -> confirmadoTXT.setVisible(false));
                    pause.play();
                }
            } else {
                System.out.println("Erro, produto ou receita invalidos");
            } 

        });
    }
}