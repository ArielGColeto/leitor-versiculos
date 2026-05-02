package com.igreja.app.ui.components;

import com.igreja.app.service.BibleService;
import com.igreja.app.ui.TelaApresentacao;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

public class ModernSearchPane extends StackPane {

    public ModernSearchPane(BibleService servico) {

        setAlignment(Pos.CENTER);

        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.setMaxWidth(500);

        Label titulo = new Label("Versículos em Apresentação");
        titulo.getStyleClass().add("title");

        Label subtitulo = new Label("Busque e gere slides");
        subtitulo.getStyleClass().add("subtitle");

        VBox card = new VBox(12);
        card.getStyleClass().add("card");

        // 🔥 VERSÕES
        ToggleGroup grupo = new ToggleGroup();
        HBox versoes = new HBox(10);

        String[] lista = {"NVI", "ACF", "NAA"};

        for (String v : lista) {
            ToggleButton btn = new ToggleButton(v);
            btn.setToggleGroup(grupo);
            btn.getStyleClass().add("pill");
            versoes.getChildren().add(btn);
        }

        ((ToggleButton) versoes.getChildren().get(0)).setSelected(true);

        // 🔍 LIVRO
        TextField campoLivro = new TextField();
        campoLivro.setPromptText("Livro");

        ContextMenu sugestoes = new ContextMenu();

        campoLivro.textProperty().addListener((obs, o, n) -> {

            if (n == null || n.isEmpty()) {
                sugestoes.hide();
                return;
            }

            List<String> livros = servico.getLivros().stream()
                    .filter(l -> l.toLowerCase().contains(n.toLowerCase()))
                    .limit(5)
                    .toList();

            sugestoes.getItems().clear();

            for (String l : livros) {
                MenuItem item = new MenuItem(l);

                item.setOnAction(e -> {
                    campoLivro.setText(l);
                    sugestoes.hide();
                });

                sugestoes.getItems().add(item);
            }

            Bounds b = campoLivro.localToScreen(campoLivro.getBoundsInLocal());
            sugestoes.show(campoLivro, b.getMinX(), b.getMaxY());
        });

        // 📖 CAMPOS
        TextField cap = new TextField();
        cap.setPromptText("Capítulo");

        TextField ver = new TextField();
        ver.setPromptText("Versículo");

        // 🔘 BOTÃO
        Button gerar = new Button("Gerar Apresentação");
        gerar.getStyleClass().add("btn-primary");
        gerar.setMaxWidth(Double.MAX_VALUE);

        VBox resultado = new VBox(10);
        resultado.getStyleClass().add("result-card");

        gerar.setOnAction(e -> {

            try {
                String versao = ((ToggleButton) grupo.getSelectedToggle())
                        .getText().toLowerCase();

                servico.carregarVersao(versao);

                String livro = campoLivro.getText();
                int c = Integer.parseInt(cap.getText());
                int v = Integer.parseInt(ver.getText());

                String texto = servico.getVersiculo(livro, c, v);
                String ref = livro + " " + c + ":" + v;

                Label refLabel = new Label(ref);
                refLabel.getStyleClass().add("ref");

                Label textoLabel = new Label(texto);
                textoLabel.setWrapText(true);

                Button apresentar = new Button("Apresentar");
                apresentar.getStyleClass().add("btn-secondary");

                apresentar.setOnAction(ev ->
                        new TelaApresentacao(ref, texto)
                );

                resultado.getChildren().setAll(
                        refLabel,
                        textoLabel,
                        apresentar
                );

            } catch (Exception ex) {
                resultado.getChildren().setAll(
                        new Label("Erro ao buscar versículo")
                );
            }
        });

        card.getChildren().addAll(
                new Label("Tradução"),
                versoes,
                campoLivro,
                cap,
                ver,
                gerar
        );

        container.getChildren().addAll(
                titulo,
                subtitulo,
                card,
                resultado
        );

        getChildren().add(container);
    }
}