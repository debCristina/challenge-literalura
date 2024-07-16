<h1 align="center">LiterAlura</h1>
<p align="center">
<img alt="Static Badge" src="https://img.shields.io/badge/Maven-3.6.3-blue">
<img alt="Static Badge" src="https://img.shields.io/badge/Spring%20Boot-2.4.5-green">
<img alt="Static Badge" src="https://img.shields.io/badge/PostgreSQL-13-blue">
<img alt="Static Badge" src="https://img.shields.io/badge/Status-Conclu%C3%ADdo-green">
</p>

# Descrição
Literalura é uma aplicação por linha de comando para gerenciamento de livros e autores. A aplicação consome a API Gutendex para obtenção de dados e utiliza o banco de dados Postgre para a inserção, busca e exibição de dados de livros e autores, além de oferecer funcionalidades para verificação de estatísticas de downloads dos livros salvos no banco de dados.

# Funcionalidades
1. **Buscar livro pelo título**: Permite ao usuário pesquisar livros pelo título.
2. **Listar livros registrados**: Exibe uma lista completa de todos os livros no banco de dados.
3. **Listar autores registrados**: Exibe uma lista completa de todos os autores no banco de dados.
4. **Listar autores vivos em um determinado ano**: Encontra autores que estavam vivos em um peróodo específico.
5. **Listar livros de um determinado idioma**: Filtra os livros disponíveis por idioma (PT, EN, FR, ES).
6. **Listar top 10 livros mais baixados**: Exibe os 10 livros mais baixados.
7. **Buscar autor por nome**: Pesquisa autores salvos no banco de dados pelo nome.
8. **Buscar autor por ano de nascimento**: Encontra os autores nascidos em um ano específico.
9. **Buscar autor por ano de falecimento**: Encontra os autores que faleceram em um ano específico.
10. **Visualizar estatísticas de downloads dos livros**: Disponibiliza as estatísticas detalhadas dos downloads dos livros, incluindo total, média, máximo, mínimo e contagem de livros no banco de dados.

# Tecnologias utilizadas
- Intelij IDEA Community Edition 2024.1
- Java 17
- Maven
- Banco de dados PostgreSQL
- Jackson Databind
- Spring Boot
- Spring Data JPA (Java Persistence API)
- API [Gutendex](https://gutendex.com/)

# Como utilizar
Antes de utilizar o projeto certifique-se de estar utilizando as seguintes tecnologias:
- Java 17
- Spring boot
- Maven
- Spring Data JPA
- Jackson Databind
- Banco de dados PostgreSQL

1. Clone este repositório
   
 ```
bash
   git clone https://github.com/debCristina/literalura.git
```
2. Configure seu PostgreSQL criando um banco de dados para a aplicação
   
3.  No arquivo `src/main/resources/aplication.properties` configure com suas informações

   ```
   properties
      spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_LITERALURA_NAME}
      spring.datasource.username=${DB_USER}
      spring.datasource.password=${DB_PASSWORD}
      spring.datasource.driver-class-name=org.postgresql.Driver
      hibernate.dialect=org.hibernate.dialect.HSQLDialect

   ```
   
- Configure as variáveis de ambiente ${DB_HOST}, ${DB_LITERALURA_NAME}, ${DB_USER} e ${DB_PASSWORD} pelos valores específicos do seu ambiente de desenvolvimento.
- Abra o projeto em sua IDE java preferida.
- Execute a aplicação e aproveite as funcionalidades disponíveis.

# Minha experiência
Desenvolver este projeto foi um desafio muito enriquecedor, onde pude aplicar e aprimorar significativamente minhas habilidades em Java. Esta jornada me proporcionou uma excelente oportunidade para explorar profundamente o desenvolvimento utilizando Spring Framework, além de me familiarizar com conceitos avançados de integração de banco de dados e APIs externas como a Gutendex. Concluir esta etapa me deixa muito satisfeita, e estou motivada a continuar explorando os recursos disponíveis para continuar aprimorando minhas habilidades como desenvolvedora.

# Contribuíções
Contribuíções são sempre bem-vindas para enriquecer ainda mais o projeto.

# Envolvidos
Este foi um projeto  desenvolvido como parte da especialização em back-end pelo programa ONE (ORACLE NEXT EDUCATION)  parceria entre Oracle e Alura Latam.
<p align="center">
    <img src="https://github.com/user-attachments/assets/6d793c82-35e7-4116-a768-cee846791281">
    <img src="https://github.com/user-attachments/assets/b03c2893-4bba-4862-9223-a19940a8c47e">
</p>




# Autor
Este projeto foi desenvolvido por Débora Ferreira
