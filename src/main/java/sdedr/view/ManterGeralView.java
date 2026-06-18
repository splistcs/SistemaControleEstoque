package sdedr.view;

import sdedr.App;
import sdedr.model.*;
import sdedr.model.Enum.*;
import sdedr.ctrl.*;

import java.math.BigDecimal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class ManterGeralView extends GridPane{

  public ManterGeralView(App loop, int tipo, CtrlCtrl aux) {
    TableView<Produto> produtoTableView = null;
    TableView<Receita> receitaTableView = null;
    Text tituloText = null;
    this.setAlignment(Pos.CENTER);
    this.setHgap(10);
    this.setVgap(10);
    this.setPadding(new Insets(25, 25, 25, 25));
    switch (tipo) {
      case 1:
        tituloText = new Text("Produtos:");
        this.add(tituloText, 0, 0, 2, 1);
        ProdutoCtrl produtoCtrl = new ProdutoCtrl();
        produtoTableView = (TableView<Produto>) switchTabela(tipo);
        if (produtoCtrl.prepararProdutos()) {
          produtoTableView.getItems().addAll(produtoCtrl.getProdutos());
        }
        this.add(produtoTableView, 0, 1);
        break;
      case 2:
        tituloText = new Text("Receitas:");
        this.add(tituloText, 0, 0, 2, 1);
        ReceitaCtrl receitaCtrl = new ReceitaCtrl();
        receitaTableView = (TableView<Receita>) switchTabela(tipo);
        if (receitaCtrl.prepararReceitas()) {
          receitaTableView.getItems().addAll(receitaCtrl.getReceitas());
        }
        this.add(receitaTableView, 0, 1);
        break;
      default:
        break;
    }

    Button sairButton = new Button("Sair");
    HBox sairHBox = new HBox(sairButton);
    sairHBox.setAlignment(Pos.BOTTOM_RIGHT);
    this.add(sairHBox, 0, 2);
    sairButton.setOnAction(event -> {
      loop.vezMenuTmpView(aux);
    });
  }

  public static TableView<?> switchTabela (int tipo) {
    switch (tipo) {
      case 1:
        return tabelaProdutoUnidade();
      case 2:
        return tabelaReceita();
      default:
        return null;
    }
  }
  private static TableView<Produto> tabelaProdutoUnidade() {
    TableView<Produto> produtoTableView = new TableView<>();
    produtoTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<Produto, Long> idColuna = new TableColumn<>("id");
    TableColumn<Produto, String> nomeColuna = new TableColumn<>("nome");
    TableColumn<Produto, BigDecimal> precoAtualColuna = new TableColumn<>("preço");
    TableColumn<Produto, BigDecimal> quantidadeEstoqueColuna = new TableColumn<>("quant.");
    TableColumn<Produto, BigDecimal> estoqueMinimoColuna = new TableColumn<>("min.");
    TableColumn<Produto, Boolean> permiteFracionamentoColuna = new TableColumn<>("fracionar");
    TableColumn<Produto, Boolean> isAtivoColuna = new TableColumn<>("ativo");

    TableColumn<Produto, String> unidadeColuna = new TableColumn<>("unidade");

    idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
    precoAtualColuna.setCellValueFactory(new PropertyValueFactory<>("precoAtual"));
    quantidadeEstoqueColuna.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
    estoqueMinimoColuna.setCellValueFactory(new PropertyValueFactory<>("estoqueMinimo"));
    permiteFracionamentoColuna.setCellValueFactory(new PropertyValueFactory<>("permiteFracionamento"));
    isAtivoColuna.setCellValueFactory(new PropertyValueFactory<>("ativo"));

    unidadeColuna.setCellValueFactory(cellData -> {
      Produto produto = cellData.getValue();
      if (produto != null && produto.getUnidade() != null) {
        return new SimpleStringProperty(produto.getUnidade().getNome());
      } else {
        return new SimpleStringProperty("");
      }
    });

    produtoTableView.getColumns().addAll(idColuna, nomeColuna, precoAtualColuna, quantidadeEstoqueColuna, 
        estoqueMinimoColuna, permiteFracionamentoColuna, isAtivoColuna, unidadeColuna);

    return produtoTableView;
  }  

  private static TableView<Receita> tabelaReceita() {
    TableView<Receita> receitaTableView = new TableView<>();
    receitaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<Receita, Long> idColuna = new TableColumn<>("id");
    TableColumn<Receita, String> nomeColuna = new TableColumn<>("nome");
    TableColumn<Receita, BigDecimal> precoColuna = new TableColumn<>("preço");
    TableColumn<Receita, Cardapio> cardapioColuna = new TableColumn<>("cardápio");

    idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
    precoColuna.setCellValueFactory(new PropertyValueFactory<>("preco"));
    cardapioColuna.setCellValueFactory(new PropertyValueFactory<>("cardapio"));

    receitaTableView.getColumns().addAll(idColuna, nomeColuna, precoColuna, cardapioColuna);

    return receitaTableView;
  }
}
