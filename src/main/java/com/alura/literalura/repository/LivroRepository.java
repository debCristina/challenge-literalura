package com.alura.literalura.repository;

import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByTitulo(String livroTitulo);

    List<Livro> findByIdioma(Idioma idioma);

    List<Livro> findTop10ByOrderByDownloadsDesc();
}
