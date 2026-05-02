package com.igreja.app.ui;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

/**
 * Tela fullscreen para projetar o versículo.
 */
public class TelaApresentacao {

    public TelaApresentacao(String referencia, String versiculo) {

        Stage palco = new Stage();

        // 🔥 pega todas as telas conectadas
        var telas = Screen.getScreens();

        // 🔥 se tiver mais de uma, usa a segunda (projetor)
        Screen telaAlvo = telas.size() > 1 ? telas.get(1) : telas.get(0);

        // 🔥 pega resolução da tela escolhida
        Rectangle2D bounds = telaAlvo.getVisualBounds();

        // 🔥 posiciona a janela na tela correta
        palco.setX(bounds.getMinX());
        palco.setY(bounds.getMinY());
        palco.setWidth(bounds.getWidth());
        palco.setHeight(bounds.getHeight());


        // Remove barra da janela
        palco.initStyle(StageStyle.UNDECORATED);

        Label ref = new Label(referencia.toUpperCase());
        ref.setStyle("-fx-text-fill: #f59e0b; -fx-font-size: 18px;");

        Label texto = new Label("\"" + versiculo + "\"");
        texto.setWrapText(true);
        texto.setStyle("-fx-text-fill: white; -fx-font-size: 36px;");



        VBox caixa = new VBox(30, ref, texto);
        caixa.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(caixa);
        root.setStyle("-fx-background-color: #0f172a;");

        Scene cena = new Scene(root);

        // Evento teclado
        cena.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ESCAPE")) {
                palco.close();
            }
        });

        palco.setFullScreenExitHint("");

        palco.setScene(cena);
        palco.setFullScreen(true);
        palco.show();
    }
}