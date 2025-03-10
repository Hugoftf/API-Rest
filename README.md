![imagem Local](imagem_readme/artigo_programacao-orientada-a-objetos-com-java_18449.png)


# API Restfull

  - [Tecnologias Usadas](#Tecnologias-Usadas)
  - [Sobre](#Sobre)
  - [Inicio](#Inicio)

 
## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/)
 / [Postman](https://www.postman.com/)

## Sobre 

Programa para exemplificar a construção de uma API Restful em um projeto já mapeado com JPA. Utilizando o Docker para rodar um banco de dados PostgreSQL em conjunto com console PgAdmin4, Aplicativo Postman foi utilizado para testar nossas requições HTTP da API.


O projeto trata de uma simulação de Autor e Livros, os dois objetos são tratados como tabelas em banco de dados.

## Inicio

No inicio do projeto eu clonei um projeto já pronto do meu [git hub](https://github.com/Hugoftf/Spring-DataJPA) sobre JPA, para implementar a camada de controller e service e desenvolver a API Restful.

Primeiro precisei adicionar a dependencia starter web no meu arquivo xml do programa para o gerenciador spring possa reconhecer as nossas aplicações da API.

![imagem local](/imagem_readme/dependency_starter_web.png)


Para começar a implementar a Api, primeiro criamos a classe AutorController no pacote controller, em seguida a classe AutorService no pacote service, AutorController é a classe que recebe a anotação @RestController (Quando você utiliza @RestController em uma classe, ela indica que a classe será um controlador (controller) responsável por responder a requisições HTTP e, além disso, os métodos dentro dessa classe retornam dados (normalmente em formato JSON ou XML) ao invés de uma página de visualização.), e também a anotação @RequestMapping (A anotação @RequestMapping no Spring Framework é usada para mapear requisições HTTP para métodos específicos de um controlador. Ela pode ser aplicada a uma classe ou a métodos individuais e permite que você defina como as requisições serão tratadas, especificando a URL, o tipo de requisição (GET, POST, PUT, DELETE etc.)), em seguida faremos uma injeção de dependencia da classe AutorService:


![imagem local](/imagem_readme/Controller/AutorController/classe_autor_controller.png)


Por sua vez a classe AutorService recebe a anotação @Service (A camada Service em uma aplicação, especialmente em uma API, é responsável pela lógica de negócios. Ela atua como uma camada intermediária entre a camada de Controller (responsável pelas requisições HTTP) e a camada de Repository/DAO (responsável pela persistência de dados, como bancos de dados)), ela é reponsavel pela lógica, e passa para o controller tudo mastigado. A classe tem uma injeção de dependencia do AutorRepository para realizar as ações nos métodos.

![imagem local](/imagem_readme/service/autor_service/classe_autorservice.png)


Na classe Autor, vamos adicionar algumas campos para ser coluna no banco de dados (Como no nosso arquivo applicaiton.yml tem a propriedade ddl auto para update, todo campo que eu mapeo com JPA irá atualizar no banco de dados).

![imagem local](/imagem_readme/app_yamal_config.png)


Atualizando a classe Autor:


![imagem local](imagem_readme/Classe_Autor/novos_campos_classe_autor.png)


A anotação @CreatedDate vai servi para que quando um novo autor for criado a coluna irá informar a data e hora que foi criado.

A anotação @LastModifiedDate como o proprio nome já diz, quando o campo for atualizado ele indicará a ultima data de atualização.


## Mapeando a API


### DTO

Para começar criaremos o record AutorDTO, ele vai servi para simular alguns campos da classe Autor.


![imgagem local](imagem_readme/Controller/autorDTO/record_autordto.png)


E criamos o método mapeandoParaAutor para transformar campos de AutorDTO em Autor, setando os campos obrigatórios de autor:


![imgagem local](imagem_readme/Controller/autorDTO/mapeando_para_autor.png)


### Criando CRUD e Testando Estrutura do Programa


Criando um metodo post básico na classe Controller para testar requisições e a estrutura do projeto:


![imgagem local](imagem_readme/Controller/AutorController/metodo_salvar.png)


O retorno no Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/post_metodo_salvar1.png)


No Banco de Dados:


![imgagem local](/imagem_readme/postgresSQL_resultado/resultado_do_post_1.png)


#### GET


Agora com o método Get:


![imgagem local](/imagem_readme/Controller/AutorController/metodo_obter_detalhes.png)


No Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/get_obter_informacao.png)











