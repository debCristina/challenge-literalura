package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DadosAutor(@JsonAlias("name") String nome,
                         @JsonAlias("birth_year") String anoNascimento,
                         @JsonAlias("death_year") String anoFalecimento)
{
}
