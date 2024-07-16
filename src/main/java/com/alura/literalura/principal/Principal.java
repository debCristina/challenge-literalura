package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner= new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor= new ConverteDados();
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;
    private final String URL = "https://gutendex.com/books?search=";

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }
    public void exibeMenu () {
        System.out.println("SEJA BEM-VINDO(A) AO LITERALURA!");
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \nPor favor, scolha uma das opções abaixo:
                    
                    1 - Buscar livro pelo titulo
                    2 - Listar liivros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros de um determinado idioma
                    6 - Listar top 10 livros mais baixados
                    7 - Buscar autor por nome
                    8 - Buscar autor por ano de nascimento
                    9 - Buscar autor por ano de falecimento
                    
                    0 - Sair              
                    """;

            System.out.println(menu);
            System.out.printf("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    buscarLivrosWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosPeriodo();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    listarTop10Livros();
                    break;
                case 7:
                    buscarAutorPorNome();
                    break;
                case 8:
                    buscarAutorPorNascimento();
                    break;
                case 9:
                    buscarAutorPorFalecimento();
                    break;
                case 10:
                    buscarLivroMaiorDownload();
                    break;
                default:
                    System.out.println("Opção inválida! Tente noamente");
                    break;
            }
        }
    }

    private void buscarLivrosWeb() {
       System.out.println("Digite o nome do livro: ");
       var livroBuscado = scanner.nextLine();
       var dados = consumo.obterDados(URL + livroBuscado.replace(" ", "+"));
       salvarDados(dados);
   }

    public void salvarDados(String dados) {
        try {
            Livro livros = new Livro(conversor.obterDados(dados, DadosLivro.class));
            Autor autor = new Autor(conversor.obterDados(dados, DadosAutor.class));
            Livro livroExistente = livroRepository.findByTitulo(livros.getTitulo());
            Autor autorExistente = autorRepository.findByNome(autor.getNome());

            if (livroExistente != null) {
                System.out.println("O livro já está cadastrado: " + livroExistente);
                return;
            }

            if (autorExistente == null) {
                autorRepository.save(autor);
            } else {
                autor = autorExistente;
            }

            livros.setAutor(autor);
            livroRepository.save(livros);

            System.out.println(livros);

        }catch (Exception e) {
            System.out.println("Erro ao salvar dados " +e.getMessage());
        }
    }

    private void listarLivros() {
        var livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        var autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPeriodo() {
        System.out.println("Digite um ano para busca: ");
        var anoBusca = scanner.nextLine();
        if (anoBusca.length() < 4 || anoBusca.length() > 4){
            System.out.println("Ano inválido. Deseja tentar novamente? [S/N]");
            var opcaoContinuar = scanner.nextLine();
            if (opcaoContinuar.equalsIgnoreCase("s")) {
                listarAutoresVivosPeriodo();
            } else {
                return;
            }
        }else {
            List<Autor> autores = autorRepository.autoresVivosNoPeriodo(anoBusca);
            System.out.println("Autores vivos no período: "+anoBusca);
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                            Digite o idioma para busca: 
                            pt - português
                            en - inglês
                            fr - francês
                            es - espanhol
                            """);
        var idiomaBusca = scanner.nextLine();
        try {
            Idioma idioma = Idioma.fromString(idiomaBusca);
            List<Livro> livrosPorIdioma = livroRepository.findByIdioma(idioma);
            System.out.println("Resultados para idioma: " +idiomaBusca);
            livrosPorIdioma.forEach(System.out::println);
        }catch (IllegalArgumentException e) {
            System.out.println("Idioma inválido. Deseja tentar novamente?[S/N]");
            var opcaoTentar = scanner.nextLine();
            if (opcaoTentar.equalsIgnoreCase("s")){
                listarLivrosPorIdioma();
            }else {
                return;
            }
        }
    }

    private void listarTop10Livros() {
        List<Livro> topLivros = livroRepository.findTop10ByOrderByDownloadsDesc();
        System.out.println("Top 10 livros mais baixados: ");
        topLivros.forEach(l -> System.out.println(l.getTitulo() + " -downloads: " +l.getDownloads()));
    }

    private void buscarAutorPorNome() {
        System.out.println("Informe o nome do autor: ");
        var autorBusca = scanner.nextLine();
        List<Autor> autoresEncontrados = autorRepository.autorPorTrechoNome(autorBusca);
        System.out.println("Autores encontrados: ");
        autoresEncontrados.forEach(System.out::println);
    }

    private void buscarAutorPorNascimento() {
        System.out.println("Informe o ano para busca: ");
        var anoBuscaNascimento = scanner.nextLine();
        if (anoBuscaNascimento.length() < 4 || anoBuscaNascimento.length() > 4){
            System.out.println("Ano inválido. Deseja tentar novamente? [S/N]");
            var opcaoContinuar = scanner.nextLine();
            if (opcaoContinuar.equalsIgnoreCase("s")) {
                listarAutoresVivosPeriodo();
            } else {
                return;
            }
        }else {
            List<Autor> autoresAnoFalecimento = autorRepository.findByAnoNascimento(anoBuscaNascimento);
            System.out.println("Autores falecidos em " + anoBuscaNascimento);
            autoresAnoFalecimento.forEach(System.out::println);
        }
    }

    private void buscarAutorPorFalecimento() {
        System.out.println("Informe o ano para busca: ");
        var anoBuscaFalecimento = scanner.nextLine();
        if (anoBuscaFalecimento.length() < 4 || anoBuscaFalecimento.length() > 4){
            System.out.println("Ano inválido. Deseja tentar novamente? [S/N]");
            var opcaoContinuar = scanner.nextLine();
            if (opcaoContinuar.equalsIgnoreCase("s")) {
                listarAutoresVivosPeriodo();
            } else {
                return;
            }
        }else {
            List<Autor> autoresAnoFalecimento = autorRepository.findByAnoFalecimento(anoBuscaFalecimento);
            System.out.println("Autores falecidos em " + anoBuscaFalecimento);
            autoresAnoFalecimento.forEach(System.out::println);
        }
    }

    private void buscarEstatísticasDeDownload() {
        List<Livro> livros = livroRepository.findAll();
        DoubleSummaryStatistics stats = livros.stream()
                .mapToDouble(Livro::getDownloads)
                .summaryStatistics();

        System.out.println("Estatísticas de downloads de livros registrados:");
        System.out.println("Total de downloads: " + stats.getSum());
        System.out.println("Média de downloads: " + stats.getAverage());
        System.out.println("Máximo de downloads: " + stats.getMax());
        System.out.println("Mínimo de downloads: " + stats.getMin());
        System.out.println("Contagem de livros: " + stats.getCount());
    }
}
