package sdedr.view.Cadastros;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sdedr.dao.UserDao;
import sdedr.model.User;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class CadastroUserView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Usuário");

        Pane pane = new Pane();
        pane.setPrefSize(899, 450);
        pane.setStyle("-fx-background-color: #eeeeee;");

        Label CadastrarUserTXT = new Label("Cadastro de Usuário");
        CadastrarUserTXT.setLayoutX(24.5625);
        CadastrarUserTXT.setLayoutY(19.359375);
        CadastrarUserTXT.setPrefWidth(141);
        CadastrarUserTXT.setPrefHeight(19);
        CadastrarUserTXT.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrarUserTXT.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(CadastrarUserTXT);

        TextField nomeInput = new TextField("");
        nomeInput.setLayoutX(226.00);
        nomeInput.setLayoutY(169.00);
        nomeInput.setPrefWidth(332.00);
        nomeInput.setPrefHeight(24.00);
        nomeInput.setPromptText("Nome do novo usuário");
        nomeInput.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        nomeInput.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(nomeInput);

        ComboBox<String> permissaoCmb = new ComboBox<>();

        ObservableList<String> opcoes = FXCollections.observableArrayList(
            "ADMIN",
            "CHEF",
            "ALMOXARIFADO"
        );

        permissaoCmb.setItems(opcoes);

        permissaoCmb.setPromptText("Selecione a permissão");
        permissaoCmb.setLayoutX(226.00);
        permissaoCmb.setLayoutY(242.00);
        
        // Substituído: Adicionando o ComboBox diretamente ao 'pane' principal
        pane.getChildren().add(permissaoCmb);


        PasswordField senha = new PasswordField();
        senha.setText("");
        senha.setLayoutX(225.00);
        senha.setLayoutY(205.00);
        senha.setPrefWidth(333.00);
        senha.setPrefHeight(24.00);
        senha.setPromptText("Senha do novo usuário");
        senha.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Lato.ttf"), 13.00));
        senha.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(senha);

        Label element6 = new Label("Insira as informações do cadastro");
        element6.setLayoutX(220);
        element6.setLayoutY(127.359375);
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
            int selecaoPermissao = permissaoCmb.getSelectionModel().getSelectedIndex() + 1;
            
            User user = new User();
            user.setNome(nomeInput.getText());
            user.setSenha(senha.getText());
            user.setTipoCadastro(selecaoPermissao);

            UserDao userDao = new UserDao();

            if(userDao.inserir(user)){
                confirmadoTXT.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> confirmadoTXT.setVisible(false));
                pause.play();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}