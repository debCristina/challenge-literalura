package com.alura.literalura.service;

import com.alura.literalura.model.DadosAutor;
import com.alura.literalura.model.DadosLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        T resultado = null;
        try {
            JsonNode node = mapper.readTree(json);
            if(classe == DadosLivro.class) {
                JsonNode livroNode = node.get("results").get(0);
                resultado = mapper.treeToValue(livroNode, classe);
            } else if (classe == DadosAutor.class) {
                JsonNode autorNode = node.get("results").get(0).get("authors").get(0);
                resultado = mapper.treeToValue(autorNode, classe);;
            } else {
                resultado = mapper.readValue(json, classe);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return resultado;
    }
}

