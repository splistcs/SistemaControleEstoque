package sdedr.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

public class DecoView extends VBox {

  public DecoView(GridPane semDecoracaoView) {
    this.setAlignment(Pos.TOP_CENTER);
    this.setStyle(DefineView.estiloTela);
    /* Pensei em uma linha meio Informix para decoração */
    Region linhaRoxo = new Region();
    linhaRoxo.setPrefHeight(12);
    linhaRoxo.setStyle("-fx-background-color: #191970;");
    /* Linha de contorno */
    Region linhaCinzaTopo = new Region();
    linhaCinzaTopo.setPrefHeight(2);
    linhaCinzaTopo.setStyle("-fx-background-color: #808080;");

    Region linhaCinzaBase = new Region();
    linhaCinzaBase.setPrefHeight(2);
    linhaCinzaBase.setStyle("-fx-background-color: #808080;");

    VBox.setVgrow(semDecoracaoView, Priority.ALWAYS);

    this.getChildren().addAll(linhaRoxo, linhaCinzaTopo, semDecoracaoView, linhaCinzaBase);
  }
}
