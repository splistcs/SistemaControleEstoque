package sdedr.view.Cadastros;

import java.math.BigDecimal;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sdedr.dao.ReceitaDao;
import sdedr.model.Receita;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
public class CadastroReceitaView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Receitas");

        Pane pane = new Pane();
        pane.setPrefSize(899, 450);
        pane.setStyle("-fx-background-color: #eeeeee;");

        Label CadastrarReceitaTXT = new Label("Cadastro de Receita");
        CadastrarReceitaTXT.setLayoutX(24.5625);
        CadastrarReceitaTXT.setLayoutY(19.359375);
        CadastrarReceitaTXT.setPrefWidth(150);
        CadastrarReceitaTXT.setPrefHeight(19);
        CadastrarReceitaTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrarReceitaTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(CadastrarReceitaTXT);

        TextField nomeInput = new TextField("");
        nomeInput.setLayoutX(78.00);
        nomeInput.setLayoutY(96.00);
        nomeInput.setPrefWidth(224.00);
        nomeInput.setPrefHeight(24.00);
        nomeInput.setPromptText("Nome da receita");
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

        TextField precoReceitaInput = new TextField("");
        precoReceitaInput.setLayoutX(77.56);
        precoReceitaInput.setLayoutY(131.11);
        precoReceitaInput.setPrefWidth(224.00);
        precoReceitaInput.setPrefHeight(24.00);
        precoReceitaInput.setPromptText("Preço da receita");
        precoReceitaInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        precoReceitaInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(precoReceitaInput);

        Label cardapioTXT = new Label("A qual cardápio essa receita vai pertencer?");
        cardapioTXT.setLayoutX(55);
        cardapioTXT.setLayoutY(189);
        cardapioTXT.setPrefWidth(300);
        cardapioTXT.setPrefHeight(19);
        cardapioTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        cardapioTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(cardapioTXT);

        Label confirmadoTXT = new Label("cadastrado com sucesso!");
        confirmadoTXT.setLayoutX(713);
        confirmadoTXT.setLayoutY(341);
        confirmadoTXT.setPrefWidth(206);
        confirmadoTXT.setPrefHeight(18);
        confirmadoTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        confirmadoTXT.setStyle("-fx-text-fill: #1b1b1b;");
        confirmadoTXT.setVisible(false);
        pane.getChildren().add(confirmadoTXT);

        ComboBox<String> cardapioCmb = new ComboBox<>();

        ObservableList<String> opcoes = FXCollections.observableArrayList(
            "DOMINGO",
            "SEGUNDA",
            "TERCA",
            "QUARTA",
            "QUINTA",
            "SEXTA",
            "SABADO",
            "FIXO",
            "ESPECIAL",
            "TMP"
        );

        cardapioCmb.setItems(opcoes);

        cardapioCmb.setPromptText("Selecione o cardápio");
        cardapioCmb.setLayoutX(80.00);
        cardapioCmb.setLayoutY(219.00);
        
        // Substituído: Adicionando o ComboBox diretamente ao 'pane' principal
        pane.getChildren().add(cardapioCmb);

        Scene scene = new Scene(pane, 899, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ConfirmarButton.setOnAction(event -> {
            int selecaoCardapio = cardapioCmb.getSelectionModel().getSelectedIndex() + 1;
            
            Receita receita = new Receita();
            receita.setCardapio(selecaoCardapio);
            receita.setNome(nomeInput.getText());
            receita.setPreco(BigDecimal.valueOf(Double.parseDouble(precoReceitaInput.getText())));
            receita.setIngredientes(null);

            ReceitaDao receitaDao = new ReceitaDao();

            if(receitaDao.inserir(receita)){
                confirmadoTXT.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> confirmadoTXT.setVisible(false));
                pause.play();
            }
        });
    }
}