package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        @Enumerated(EnumType.STRING)
        private Idioma idioma;
        private Integer downloads;
        @ManyToOne
        private Autor autor;

        public Livro(){}

        public Livro(DadosLivro dadosLivro) {
                this.titulo = dadosLivro.titulo();
                this.downloads = dadosLivro.downloads();
                if (dadosLivro.idioma() != null && !dadosLivro.idioma().isEmpty()) {
                        this.idioma = Idioma.fromString(dadosLivro.idioma().get(0));
                }
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public Autor getAutor() {
                return autor;
        }

        public void setAutor(Autor autor) {
                this.autor = autor;
        }

        public Idioma getIdioma() {
                return idioma;
        }

        public void setIdioma(Idioma idioma) {
                this.idioma = idioma;
        }

        public Integer getDownloads() {
                return downloads;
        }

        public void setDownloads(Integer downloads) {
                this.downloads = downloads;
        }

        @Override
        public String toString() {
                return "\n---------- L I V R O -----------"+
                        "\nTitulo: " +titulo +
                        "\nAutor: " + autor.getNome() +
                        "\nIdioma: " + idioma +
                        "\nNúmero de downloads: " +downloads+
                        "\n-----------------------------------\n";
        }
}
