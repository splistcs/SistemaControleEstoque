package sdedr.view;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import sdedr.ctrl.IngredienteCtrl;
import sdedr.ctrl.ReceitaCtrl;
import sdedr.model.Ingrediente;
import sdedr.model.Receita;
import sdedr.model.Enum.Cardapio;

public class CardapioView {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cardápio");

        Pane pane = new Pane();
        pane.setPrefSize(903, 452);
        pane.setStyle(DefineView.estiloTela);

        Label titulo = new Label("Cardápio do dia");
        titulo.setLayoutX(55.00);
        titulo.setLayoutY(16.00);
        titulo.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 18.00));
        titulo.setStyle(DefineView.estiloTexto);
        pane.getChildren().add(titulo);

        Label labelDia = new Label("Dia:");
        labelDia.setLayoutX(55.00);
        labelDia.setLayoutY(58.00);
        labelDia.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        labelDia.setStyle(DefineView.estiloTexto);
        pane.getChildren().add(labelDia);

        ComboBox<Cardapio> comboDia = new ComboBox<>();
        comboDia.getItems().addAll(
                Cardapio.SEGUNDA, Cardapio.TERCA, Cardapio.QUARTA,
                Cardapio.QUINTA, Cardapio.SEXTA, Cardapio.SABADO, Cardapio.DOMINGO);
        comboDia.setLayoutX(100.00);
        comboDia.setLayoutY(54.00);
        comboDia.setPrefWidth(180.00);
        comboDia.setStyle(DefineView.estiloBotaoBase);
        pane.getChildren().add(comboDia);

        TextArea areaTexto = new TextArea("");
        areaTexto.setEditable(false);
        areaTexto.setLayoutX(55.00);
        areaTexto.setLayoutY(95.00);
        areaTexto.setPrefWidth(793.00);
        areaTexto.setPrefHeight(312.00);
        areaTexto.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BlexMonoNerdFont-Regular.ttf"), 13.00));
        areaTexto.setStyle(DefineView.estiloEntrada);
        pane.getChildren().add(areaTexto);

        Scene scene = new Scene(pane, 903, 452);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);

        primaryStage.widthProperty().addListener((obs, velho, novo) -> {
            areaTexto.setPrefWidth(novo.doubleValue() - 110);
        });
        primaryStage.heightProperty().addListener((obs, velho, novo) -> {
            areaTexto.setPrefHeight(novo.doubleValue() - 140);
        });

        comboDia.setOnAction(e -> mostrarCardapio(comboDia.getValue(), areaTexto));

        comboDia.setValue(cardapioDeHoje());
        mostrarCardapio(comboDia.getValue(), areaTexto);

        primaryStage.show();
    }

    private void mostrarCardapio(Cardapio dia, TextArea areaTexto) {
        areaTexto.clear();
        if (dia == null) {
            return;
        }

        ReceitaCtrl receitaCtrl = new ReceitaCtrl();
        receitaCtrl.prepararReceitas();
        ArrayList<Receita> todas = receitaCtrl.getReceitas();

        areaTexto.appendText("--- CARDÁPIO: " + dia + " ---\n\n");

        BigDecimal total = BigDecimal.ZERO;
        boolean achouAlguma = false;

        for (Receita r : todas) {
            if (!entraNoCardapioDoDia(r.getCardapio(), dia)) {
                continue;
            }
            achouAlguma = true;
            areaTexto.appendText("Receita: " + r.getNome() + " | Preço: R$ " + r.getPreco() + "\n");

            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
            IngredienteCtrl ingredienteCtrl = new IngredienteCtrl();
            ingredienteCtrl.retornarIngredientesReceita(r, ingredientes);

            if (ingredientes.isEmpty()) {
                areaTexto.appendText("  (Sem ingredientes cadastrados)\n");
            } else {
                areaTexto.appendText("  Ingredientes:\n");
                for (Ingrediente i : ingredientes) {
                    String unidade = (i.getProduto().getUnidade() != null)
                            ? " " + i.getProduto().getUnidade().getNome() : "";
                    areaTexto.appendText("    - " + i.getProduto().getNome() + ": "
                            + i.getQuantidadeProduto() + unidade + "\n");
                }
            }

            if (r.getPreco() != null) {
                total = total.add(r.getPreco());
            }
            areaTexto.appendText("\n");
        }

        if (!achouAlguma) {
            areaTexto.appendText("(Nenhuma receita para este dia)\n");
        } else {
            areaTexto.appendText("Total do cardápio: R$ " + total + "\n");
        }
    }

    private boolean entraNoCardapioDoDia(Cardapio marcador, Cardapio dia) {
        return marcador == dia
                || marcador == Cardapio.FIXO
                || marcador == Cardapio.ESPECIAL
                || marcador == Cardapio.TMP;
    }

    private Cardapio cardapioDeHoje() {
        DayOfWeek d = LocalDate.now().getDayOfWeek();
        switch (d) {
            case MONDAY:    return Cardapio.SEGUNDA;
            case TUESDAY:   return Cardapio.TERCA;
            case WEDNESDAY: return Cardapio.QUARTA;
            case THURSDAY:  return Cardapio.QUINTA;
            case FRIDAY:    return Cardapio.SEXTA;
            case SATURDAY:  return Cardapio.SABADO;
            case SUNDAY:    return Cardapio.DOMINGO;
            default:        return Cardapio.SEGUNDA;
        }
    }
}