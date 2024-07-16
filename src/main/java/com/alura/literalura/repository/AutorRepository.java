package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome(String nome);

    @Query("""
            SELECT a 
            FROM Autor a
            WHERE (anoFalecimento > :anoBusca OR a.anoFalecimento IS NULL)
            AND :anoBusca >= anoNascimento
            """)
    List<Autor> autoresVivosNoPeriodo(String anoBusca);

    @Query("""
            SELECT a 
            FROM Autor a
            WHERE a.nome ILIKE %:autorBusca%
            """)
    List<Autor> autorPorTrechoNome(String autorBusca);

    List<Autor> findByAnoFalecimento(String anoBuscaFalecimento);

    List<Autor> findByAnoNascimento(String anoBuscaNascimento);
}
