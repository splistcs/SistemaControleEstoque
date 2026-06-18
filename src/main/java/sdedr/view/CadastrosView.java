package sdedr.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import sdedr.dao.IngredienteDao;
import sdedr.dao.ProdutoDao;
import sdedr.dao.ReceitaDao;
import sdedr.dao.UnidadeDao;
import sdedr.dao.UserDao;
import sdedr.model.Produto;
import sdedr.model.Receita;
import sdedr.model.Unidade;
import sdedr.model.User;
import sdedr.view.Cadastros.CadastroIngredienteView;
import sdedr.view.Cadastros.CadastroProdutoView;
import sdedr.view.Cadastros.CadastroReceitaView;
import sdedr.view.Cadastros.CadastroUnidadeView;
import sdedr.view.Cadastros.CadastroUserView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CadastrosView extends Application {

    final boolean[] aguardandoConfirmacao = {false};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastros");

        Pane painelEsquerdo = new Pane();
        painelEsquerdo.setPrefSize(250, 450);
        painelEsquerdo.setStyle(DefineView.estiloTela);

        Button UsuariosButton = new Button("Cadastrar Usuários");
        UsuariosButton.setLayoutX(40.00);
        UsuariosButton.setLayoutY(53.00);
        UsuariosButton.setPrefWidth(164.00);
        UsuariosButton.setPrefHeight(47.00);
        UsuariosButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        UsuariosButton.setStyle(DefineView.estiloBotaoBase);
        UsuariosButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { UsuariosButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        UsuariosButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { UsuariosButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        painelEsquerdo.getChildren().add(UsuariosButton);

        Button ProdutosButton = new Button("Cadastrar Produtos");
        ProdutosButton.setLayoutX(40.00);
        ProdutosButton.setLayoutY(112.00);
        ProdutosButton.setPrefWidth(164.00);
        ProdutosButton.setPrefHeight(47.00);
        ProdutosButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        ProdutosButton.setStyle(DefineView.estiloBotaoBase);
        ProdutosButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { ProdutosButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        ProdutosButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { ProdutosButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        painelEsquerdo.getChildren().add(ProdutosButton);

        Button UnidadesButton = new Button("Cadastrar Unidades");
        UnidadesButton.setLayoutX(40.00);
        UnidadesButton.setLayoutY(167.00);
        UnidadesButton.setPrefWidth(164.00);
        UnidadesButton.setPrefHeight(47.00);
        UnidadesButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        UnidadesButton.setStyle(DefineView.estiloBotaoBase);
        UnidadesButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { UnidadesButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        UnidadesButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { UnidadesButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        painelEsquerdo.getChildren().add(UnidadesButton);

        Button ReceitasButton = new Button("Cadastrar Receitas");
        ReceitasButton.setLayoutX(40.00);
        ReceitasButton.setLayoutY(223.00);
        ReceitasButton.setPrefWidth(164.00);
        ReceitasButton.setPrefHeight(47.00);
        ReceitasButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        ReceitasButton.setStyle(DefineView.estiloBotaoBase);
        ReceitasButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { ReceitasButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        ReceitasButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { ReceitasButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        painelEsquerdo.getChildren().add(ReceitasButton);

        Button IngredientesButton = new Button("Cadastrar Ingredientes");
        IngredientesButton.setLayoutX(40.00);
        IngredientesButton.setLayoutY(279.00);
        IngredientesButton.setPrefWidth(164.00);
        IngredientesButton.setPrefHeight(47.00);
        IngredientesButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        IngredientesButton.setStyle(DefineView.estiloBotaoBase);
        IngredientesButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { IngredientesButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        IngredientesButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { IngredientesButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        painelEsquerdo.getChildren().add(IngredientesButton);


       TableView<Unidade> unidadesTable = new TableView<>();

            TableColumn<Unidade, Long> id_unidade = new TableColumn<>("id");
            id_unidade.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Unidade, String> nome_unidade = new TableColumn<>("nome");
            nome_unidade.setCellValueFactory(new PropertyValueFactory<>("nome"));
            TableColumn<Unidade, String> descricao_unidade = new TableColumn<>("descrição");
            descricao_unidade.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            unidadesTable.getColumns().setAll(id_unidade, nome_unidade, descricao_unidade);
            unidadesTable.setEditable(false);
            unidadesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableView<User> userTable = new TableView<>();

            TableColumn<User, Long> id_usuario = new TableColumn<>("id");
            id_usuario.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<User, String> nome_usuario = new TableColumn<>("nome");
            nome_usuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
            TableColumn<User, String> tipoCadastro_usuario = new TableColumn<>("permissão");
            tipoCadastro_usuario.setCellValueFactory(new PropertyValueFactory<>("tipoCadastro"));
            userTable.getColumns().setAll(id_usuario, nome_usuario, tipoCadastro_usuario);
            userTable.setEditable(false);
            userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableView<Produto> produtosTable = new TableView<>();

            TableColumn<Produto, Long> id_produto = new TableColumn<>("id");
            id_produto.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Produto, String> nome_produto = new TableColumn<>("nome");
            nome_produto.setCellValueFactory(new PropertyValueFactory<>("nome"));
            TableColumn<Produto, BigDecimal> precoAtual_produto = new TableColumn<>("preço atual");
            precoAtual_produto.setCellValueFactory(new PropertyValueFactory<>("precoAtual"));
            TableColumn<Produto, BigDecimal> quantidadeEstoque_produto = new TableColumn<>("quantidade");
            quantidadeEstoque_produto.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
            TableColumn<Produto, BigDecimal> estoqueMinimo_produto = new TableColumn<>("estoque mínimo");
            estoqueMinimo_produto.setCellValueFactory(new PropertyValueFactory<>("estoqueMinimo"));
            TableColumn<Produto, String> permiteFracionamento_produto = new TableColumn<>("permite fracionamento");
            permiteFracionamento_produto.setCellValueFactory(new PropertyValueFactory<>("permiteFracionamento"));
            TableColumn<Produto, String> ativo = new TableColumn<>("Ativo?");
            ativo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
            produtosTable.getColumns().setAll(id_produto, nome_produto, precoAtual_produto, quantidadeEstoque_produto, estoqueMinimo_produto, permiteFracionamento_produto, ativo);
            produtosTable.setEditable(false);
            produtosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableView<Receita> receitasTable = new TableView<>();

            TableColumn<Receita, Long> id_receita = new TableColumn<>("id");
            id_receita.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Receita, BigDecimal> preco_receita = new TableColumn<>("Preço");
            preco_receita.setCellValueFactory(new PropertyValueFactory<>("preco"));
            TableColumn<Receita, String> nome_receita = new TableColumn<>("nome");
            nome_receita.setCellValueFactory(new PropertyValueFactory<>("nome"));
            TableColumn<Receita, String> cardapio_receita = new TableColumn<>("cardapio");
            cardapio_receita.setCellValueFactory(new PropertyValueFactory<>("cardapio"));
            receitasTable.getColumns().setAll(id_receita, preco_receita, nome_receita, cardapio_receita);
            receitasTable.setEditable(false);
            receitasTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        StackPane containerTabelas = new StackPane(userTable, unidadesTable, produtosTable, receitasTable);
        VBox.setVgrow(containerTabelas, Priority.ALWAYS); 

        Button CadastrarButton = new Button("Cadastrar");
        CadastrarButton.setPrefWidth(105.81);
        CadastrarButton.setPrefHeight(30.00);
        CadastrarButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        CadastrarButton.setStyle(DefineView.estiloBotaoBase);
        CadastrarButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { CadastrarButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        CadastrarButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { CadastrarButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });

        Button RemoverButton = new Button("Remover");
        RemoverButton.setPrefWidth(105.81);
        RemoverButton.setPrefHeight(30.00);
        RemoverButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        RemoverButton.setStyle(DefineView.estiloBotaoBase);
        RemoverButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { RemoverButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        RemoverButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { RemoverButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });

        HBox barraAcoes = new HBox(10, RemoverButton, CadastrarButton);
        barraAcoes.setAlignment(Pos.CENTER_RIGHT);

        VBox painelDireitoConteudo = new VBox(15, containerTabelas, barraAcoes);
        painelDireitoConteudo.setPadding(new Insets(53, 40, 40, 10)); 
        HBox.setHgrow(painelDireitoConteudo, Priority.ALWAYS);

        HBox layoutGeral = new HBox(painelEsquerdo, painelDireitoConteudo);
        layoutGeral.setStyle(DefineView.estiloTela);

        userTable.setVisible(false);
        unidadesTable.setVisible(false);
        produtosTable.setVisible(false);
        receitasTable.setVisible(false);

        Scene scene = new Scene(layoutGeral, 953, 480);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();


        UsuariosButton.setOnAction(event -> {
            userTable.setVisible(true);
            unidadesTable.setVisible(false);
            produtosTable.setVisible(false);
            receitasTable.setVisible(false);
            aguardandoConfirmacao[0] = false;
            RemoverButton.setText("Remover");
            RemoverButton.setStyle(DefineView.estiloBotaoBase);



            UserDao userdao = new UserDao();
            ArrayList<User> lista = new ArrayList<>();
            userdao.retornarTudo(lista);
            

            ObservableList<User> userList = FXCollections.observableArrayList(lista);

            userTable.setItems(userList);
        });

        UnidadesButton.setOnAction(event -> {
            userTable.setVisible(false);
            unidadesTable.setVisible(true);
            produtosTable.setVisible(false);
            receitasTable.setVisible(false);
            aguardandoConfirmacao[0] = false;
            RemoverButton.setText("Remover");
            RemoverButton.setStyle(DefineView.estiloBotaoBase);


            UnidadeDao unidadeDao = new UnidadeDao();
            ArrayList<Unidade> lista = new ArrayList<>();
            unidadeDao.retornarTudo(lista);

            ObservableList<Unidade> unidadeList = FXCollections.observableArrayList(lista);

            unidadesTable.setItems(unidadeList);
        });

        ProdutosButton.setOnAction(event -> {
            userTable.setVisible(false);
            unidadesTable.setVisible(false);
            produtosTable.setVisible(true);
            receitasTable.setVisible(false);
            aguardandoConfirmacao[0] = false;
            RemoverButton.setText("Remover");
            RemoverButton.setStyle(DefineView.estiloBotaoBase);


            ProdutoDao produtoDao = new ProdutoDao();
            ArrayList<Produto> lista = new ArrayList<>();
            produtoDao.retornarTudo(lista);

            ObservableList<Produto> unidadeList = FXCollections.observableArrayList(lista);

            produtosTable.setItems(unidadeList);
        });

        ReceitasButton.setOnAction(event -> {
            userTable.setVisible(false);
            unidadesTable.setVisible(false);
            produtosTable.setVisible(false);
            receitasTable.setVisible(true);
            aguardandoConfirmacao[0] = false;
            RemoverButton.setText("Remover");
            RemoverButton.setStyle(DefineView.estiloBotaoBase);

            ReceitaDao userdao = new ReceitaDao();
            ArrayList<Receita> lista = new ArrayList<>();
            userdao.retornarTudo(lista);

            ObservableList<Receita> receitasList = FXCollections.observableArrayList(lista);

            receitasTable.setItems(receitasList);
        });


        RemoverButton.setOnAction(event -> {
            Object itemSelecionado = null;
            TableView<?> tabelaAtiva = null;

            if (userTable.isVisible()) {
                itemSelecionado = userTable.getSelectionModel().getSelectedItem();
                tabelaAtiva = userTable;
            } else if (unidadesTable.isVisible()) {
                itemSelecionado = unidadesTable.getSelectionModel().getSelectedItem();
                tabelaAtiva = unidadesTable;
            } else if (produtosTable.isVisible()) {
                itemSelecionado = produtosTable.getSelectionModel().getSelectedItem();
                tabelaAtiva = produtosTable;
            } else if (receitasTable.isVisible()) {
                itemSelecionado = receitasTable.getSelectionModel().getSelectedItem();
                tabelaAtiva = receitasTable;
            }

            if (itemSelecionado == null) {
                aguardandoConfirmacao[0] = false;
                RemoverButton.setText("Remover");
                RemoverButton.setStyle(DefineView.estiloBotaoBase);
                return;
            }

            if (!aguardandoConfirmacao[0]) {
                aguardandoConfirmacao[0] = true;
                RemoverButton.setText("Confirmar?");
                RemoverButton.setStyle(DefineView.estiloBotaoPerigo);
            
            } else {

                tabelaAtiva.getItems().remove(itemSelecionado);

                if (userTable.isVisible()) {
                    UserDao userDao = new UserDao();
                    User user = (User) itemSelecionado;

                    userDao.remover(user.getId());
                    
                } else if (unidadesTable.isVisible()) {
                    UnidadeDao unidadeDao = new UnidadeDao();
                    Unidade unidade = (Unidade) itemSelecionado;

                    unidadeDao.remover(unidade.getId());
                } else if (produtosTable.isVisible()) {
                    ProdutoDao produtoDao = new ProdutoDao();
                    Produto produto = (Produto) itemSelecionado;

                    produtoDao.remover(produto.getId());
                } else if (receitasTable.isVisible()) {
                    ReceitaDao receitaDao = new ReceitaDao();
                    Receita receita = (Receita) itemSelecionado;
                    IngredienteDao ingredienteDao = new IngredienteDao();

                    ingredienteDao.remover(receita.getId());

                    receitaDao.remover(receita.getId());
                }

                tabelaAtiva.refresh();

                aguardandoConfirmacao[0] = false;
                RemoverButton.setText("Remover");
                RemoverButton.setStyle(DefineView.estiloBotaoBase);
            }
        });

        IngredientesButton.setOnAction(event ->{
            CadastroIngredienteView cadastroIngredienteView = new CadastroIngredienteView();
            cadastroIngredienteView.start(new Stage());
        });

        CadastrarButton.setOnAction(event -> {
            TableView<?> tabelaAtiva = null;

            if (userTable.isVisible()) {
                tabelaAtiva = userTable;
            } else if (unidadesTable.isVisible()) {
                tabelaAtiva = unidadesTable;
            } else if (produtosTable.isVisible()) {
                tabelaAtiva = produtosTable;
            } else if (receitasTable.isVisible()) {
                tabelaAtiva = receitasTable;
            }

            if (tabelaAtiva == userTable) {
                CadastroUserView cadastroUserView = new CadastroUserView();
                cadastroUserView.start(new Stage());
            } else if (tabelaAtiva == unidadesTable) {
                CadastroUnidadeView cadastroUnidadeView = new CadastroUnidadeView();
                cadastroUnidadeView.start(new Stage());
            } else if (tabelaAtiva == produtosTable) {
                CadastroProdutoView cadastroProdutoView = new CadastroProdutoView();
                cadastroProdutoView.start(new Stage());
            } else if (tabelaAtiva == receitasTable) {
                CadastroReceitaView cadastroReceitaView = new CadastroReceitaView();
                cadastroReceitaView.start(new Stage());
            }
        });
    }
}