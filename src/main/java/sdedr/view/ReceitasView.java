package sdedr.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import sdedr.ctrl.IngredienteCtrl;
import sdedr.dao.MovimentacaoDao;
import sdedr.dao.ProdutoDao;
import sdedr.dao.ReceitaDao;
import sdedr.model.Ingrediente;
import sdedr.model.MovimentacaoEstoqueDeProduto;
import sdedr.model.Produto;
import sdedr.model.Receita;
import sdedr.model.User;
import sdedr.model.Enum.TipoMovimentacao;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReceitasView extends Application {
    
    private User user;
    
    public ReceitasView(User user) {
        this.user = user;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover Receitas do Estoque");

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

        ReceitaDao receitaDao = new ReceitaDao();
        ArrayList<Receita> lista = new ArrayList<>();
        receitaDao.retornarTudo(lista);
        ObservableList<Receita> receitasList = FXCollections.observableArrayList(lista);
        receitasTable.setItems(receitasList);

        VBox.setVgrow(receitasTable, Priority.ALWAYS);

        Button RemoverEstoqueButton = new Button("Remover do Estoque");
        RemoverEstoqueButton.setPrefWidth(180.00);
        RemoverEstoqueButton.setPrefHeight(30.00);
        RemoverEstoqueButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 14.00));
        RemoverEstoqueButton.setStyle(DefineView.estiloBotaoBase);
        RemoverEstoqueButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { RemoverEstoqueButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        RemoverEstoqueButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { RemoverEstoqueButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });

        HBox barraAcoes = new HBox(RemoverEstoqueButton);
        barraAcoes.setAlignment(Pos.CENTER_RIGHT);

        VBox layoutGeral = new VBox(15, receitasTable, barraAcoes);
        layoutGeral.setPadding(new Insets(40, 40, 40, 40)); 
        layoutGeral.setStyle(DefineView.estiloTela);

        Scene scene = new Scene(layoutGeral, 800, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        final boolean[] aguardandoConfirmacao = {false};

        RemoverEstoqueButton.setOnAction(event -> {
            Receita selecionada = receitasTable.getSelectionModel().getSelectedItem();


            if (selecionada == null) {
                aguardandoConfirmacao[0] = false;
                RemoverEstoqueButton.setText("Remover do Estoque");
                RemoverEstoqueButton.setStyle(DefineView.estiloBotaoBase);
                return;
            }

            if (!aguardandoConfirmacao[0]) {
                aguardandoConfirmacao[0] = true;
                RemoverEstoqueButton.setText("Confirmar Remoção?");
                RemoverEstoqueButton.setStyle(DefineView.estiloBotaoPerigo);
            
            } else {
                ArrayList<Ingrediente> ingredientes = new ArrayList<>();
                IngredienteCtrl ingredienteCtrl = new IngredienteCtrl();
                ingredienteCtrl.retornarIngredientesReceita(selecionada, ingredientes);

                ProdutoDao produtoDao = new ProdutoDao();
                MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
                MovimentacaoEstoqueDeProduto movimentacaoEstoqueDeProduto = new MovimentacaoEstoqueDeProduto();

                for (Ingrediente i : ingredientes) {
                    Produto produto = i.getProduto();

                    movimentacaoEstoqueDeProduto.setDataHora(LocalDateTime.now());
                    movimentacaoEstoqueDeProduto.setUser(user);
                    movimentacaoEstoqueDeProduto.setObservacao("Remoção Automática do Estoque para a receita: " + selecionada.getNome());
                    movimentacaoEstoqueDeProduto.setQuantidade(i.getQuantidadeProduto());
                    movimentacaoEstoqueDeProduto.setProduto(i.getProduto());
                    movimentacaoEstoqueDeProduto.setTipoMovimentacao(TipoMovimentacao.SAIDA);
                    movimentacaoEstoqueDeProduto.setValidadeLote(LocalDate.now());
                    movimentacaoEstoqueDeProduto.setPrecoUnitario(BigDecimal.valueOf(0.00));

                    movimentacaoDao.inserir(movimentacaoEstoqueDeProduto);
                    BigDecimal quantidadeGasta = i.getQuantidadeProduto();

                    produtoDao.atualizarQuantidade(produto, quantidadeGasta, movimentacaoEstoqueDeProduto.getTipoMovimentacao()); 
                }

                aguardandoConfirmacao[0] = false;
                RemoverEstoqueButton.setText("Remover do Estoque");
                RemoverEstoqueButton.setStyle(DefineView.estiloBotaoBase);}
        });
    }
}