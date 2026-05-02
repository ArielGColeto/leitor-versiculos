package com.igreja.app.service;

import com.igreja.app.repository.BibleRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Camada de lógica.
 */
public class BibleService {

    private JSONArray biblia;

    // Mapeia nome → abbrev
    private static final Map<String, String> mapa = Map.of(
            "Gênesis", "gn",
            "Êxodo", "ex",
            "Salmos", "sl",
            "João", "jo",
            "Mateus", "mt"
    );

    public void carregarVersao(String versao) {
        biblia = new BibleRepository().loadBible(versao);
    }

    public List<String> getLivros() {
        return new ArrayList<>(mapa.keySet());
    }

    public String getVersiculo(String livro, int cap, int ver) {

        String abrev = mapa.get(livro);

        if (abrev == null) return "Livro inválido";

        for (int i = 0; i < biblia.length(); i++) {

            JSONObject obj = biblia.getJSONObject(i);

            if (obj.getString("abbrev").equals(abrev)) {

                JSONArray caps = obj.getJSONArray("chapters");
                JSONArray versiculos = caps.getJSONArray(cap - 1);

                return versiculos.getString(ver - 1);
            }
        }

        return "Não encontrado";
    }
}