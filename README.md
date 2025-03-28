![imagem Local](imagem_readme/artigo_programacao-orientada-a-objetos-com-java_18449.png)


# API Restfull

  - [Tecnologias Usadas](#Tecnologias-Usadas)
  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Desenvolvendo a API](#Mapeando-a-API)
  - [Melhorias](#Melhorias)
  - [API Livros](#API-Livros)

 
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



## Mapeando a API


### DTO

Para começar criaremos o record AutorDTO, ele vai servi para simular alguns campos da classe Autor.


![imgagem local](imagem_readme/Controller/autorDTO/record_autordto.png)


E criamos o método mapeandoParaAutor para transformar campos de AutorDTO em Autor, setando os campos obrigatórios de autor:


![imgagem local](imagem_readme/Controller/autorDTO/mapeando_para_autor.png)




### Criando CRUD e Testando Estrutura do Programa


#### POST

Criando um metodo post básico na classe Controller para testar requisições e a estrutura do projeto:



![imgagem local](imagem_readme/Controller/AutorController/metodo_salvar.png)



Na camada Service:

![imgagem local](imagem_readme/service/autor_service/metodo_salvar.png)


O retorno no Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/post_metodo_salvar1.png)



No Banco de Dados:


![imgagem local](/imagem_readme/postgresSQL_resultado/resultado_do_post_1.png)


Como funciona o Post, no nosso Postman utilizamos a URL juntamente com a requicição POST, No nosso metodo, ele está anotado com @PostMapping, para que quando A requisição for POST junto com a url definida no parametro do método(Que geralmente o padrão e o mesmo da classe), ele execute as ações do método. No caso do nosso método POST, ele transita entre service e repository para salvar no banco de dados, o metodo recebe como parametro um AutorDTO que e convertido em Autor, e como o corpo do método é ResponseEntity para ter um retorno mais personalizado no Postman, ele necessita da URI para retorna no Postman.


#### GET


Agora com o método Get:


![imgagem local](/imagem_readme/Controller/AutorController/metodo_obter_detalhes.png)



Na camada Service:


![imgagem local](imagem_readme/service/autor_service/metodo_obter_detalhes.png)



No Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/get_obter_informacao.png)



No caso do Get, na anotação GetMapping, necessita do parametro /id, o UUID do autor no banco de dados. Depois de realiar a Requisição Get juntamente com o http e id, ele retorna os dados do autor(ou não) e o código do ResponseEntity.


#### DELETE


Metodo delete, com a anotação DeleteMapping com o parametro /id:


![imgagem local](imagem_readme/Controller/AutorController/metodo_delete_autor.png)


Na camada Service:


![imgagem local](imagem_readme/service/autor_service/metodo_delete_autor.png)



No Postman:



![imgagem local](imagem_readme/Postman/resultado_autor/metodo_delete_autor.png)



(Parecido com o metodo GET)


#### PUT


Metodo Put, recebe a anotação @PutMapping com parametro id da requisição:


![imgagem local](imagem_readme/Controller/AutorController/metodo_put_atualizar_autor.png)


Na camada Service:


![imgagem local](imagem_readme/service/autor_service/metodo_put_atualizar_autor.png)



Ele é uma mistura de Get com Post, com o id da requisição ele busca no banco se o autor existe ou não, o outro parametro tem a anotação @RequestBody para receber o o Autor do banco, em seguida da um set nas novas informações que é posta no Postman em formato JSON.

No Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/put_atualizar_autor.png)


## Melhorias


### Melhorando a entidade Autor


Antes de começar a adicionar melhores na classe autor, no arquivo aplication.yml(yamal) vamos adicionar uma mudança de jpa, para que não precise dropar a tabela no banco de dados e implementar mudanças no prório programa atraves de java e jpa:



![imgagem local](/imagem_readme/app_yamal_config.png)


Feito a mudança, na classe Autor foi implementado as colunas: dataCadastro, dataAtualizacao e idUsuario:


![imgagem local](/imagem_readme/Classe_Autor/novos_campos_classe_autor.png)


O campo dataCadastro irá servi para que novos  Autores cadastrado no banco de dados tenha sua data e hora registrada nessa coluna;
O campo DataAutalizacao irá servi para quando o campo for atualizado será modificado a data e hora para cada respectiva atualização;


### AutorRepository

Adicionando QueryMethods personalizados para ser utilizados para consulta personalizadas e possiveis erros:


![imgagem local](imagem_readme/Autor_repository/query_metodo_findbByNomeAndDataNascimentoAndNacionalidade.png)



### Criação de Erros personalizados:


Para que o codigo fique mais limpos, iremos implementar soluções personalizadas para pósiveis erros;



![imgagem local](imagem_readme/Controller/DtosErros/ErroCampo/record_erro_campo.png)



O record ErroCampo vai servi para identificar em qual campo o erro aconteceu e com sua descriação



![imgagem local](imagem_readme/Controller/DtosErros/ErroResposta/record_erro_resposta_and_metodos.png)



O record ErroResposta será a representação do corpo do erro, que não necessariamente possam ser com o campo, possui um campo com atributo int para representar o código de status,
a uma string para retornar que tipo de erro aconteceu e uma lista de ErroCampo para no caso de erro em campos da entidade. Ele possui 2 metodos statics para situações com que não sejam erros com campos então por isso qe retornar uma lista vazia


### Exceptions


Criando também exceptions personalizadas que herdam de Runtime, para deixar o código mais limpo.

A classe RegistroDuplicado vai servi para uma tratar um erro expecífico de duplicação de registro no banco de dados.


![imgagem local](imagem_readme/exception/classe_registroDuplicadoException.png)



A classe OperacaoNaoPermitida, vai servi para tratar possuveis erros genéricos que possam acontecer:


![imgagem local](imagem_readme/exception/classe_operacaonaopermitida.png)


### Criação de Validator


Criando uma classe chamada Validator para checar alguns condições antes de enviar as mdanças ao banco de dados, caso contrário irá lançar uma dos nossos erros ou exceções:


![imgagem local](imagem_readme/service/validator/classe_AutorValidator.png)


Metodos de validação com exceções personlizadas:


![imgagem local](/imagem_readme/service/validator/metodo_para_validar.png)


### Mudanças na camada Service


Implementando injeções de dependencias para a classe LivroRepository para possivel tratativa de erro:


![imgagem local](/imagem_readme/service/autor_service/classe_service_atualizado.png)


Agora precisamos atualizar os metodos, colocar validações para deixar o código mais seguro.

#### Delete no Service


Primeiro vamos criar um metodo boolean para identificar se o autor possui livro. Por regra de negocios, você não pode excluir um autor se ele tiver um livro associado a ele:


![imgagem local](/imagem_readme/service/autor_service/metodo_possui_livro.png)


Depois atualizamos o metodo com essa validação para então excluir o autor:


![imgagem local](/imagem_readme/service/autor_service/metodo_deletar_atualizado.png)


#### Salvar e Atualizar


Vamos atualizar também os metodos salvar e atualizar, onde precisamos validar antes de salvar se o existe o autor da mesma forma que precisamos validar se existe o autor antes de tentar atualizar, caso exista retornar a nossa exceção personalizada:


![imgagem local](/imagem_readme/service/autor_service/metodo_salvar_e_atualizar_atualizados.png)



### Mudança em métodos na camada controller


Agora na camada controller vamos também atualizar nossos cruds.


#### POST


Como na camada service os nossos validador lança exceções personalizadas, os nossos metodos da camada controller serão tratados com o try-catch.



![imgagem local](imagem_readme/Controller/AutorController/post_metodo_atualizado.png)



Repare que se o metodo lança exceção o retorno deverá ser um ResponseEntity, que tem a representação no record ErroResposta com o codigo de status http, a mensagem da exceção e talves os campos que possam ter causados erros. No postman para testar o metodo, erramos de proposito e o resultado é:



![imgagem local](imagem_readme/Postman/resultado_autor/post_resultado_errostatus.png)



#### PUT

Da mesma forma, o put tem o mesmo tratamento que o post:


![imgagem local](imagem_readme/Controller/AutorController/put_metodo_atualizado.png)


o retorno no postman:


![imgagem local](imagem_readme/Postman/resultado_autor/put_resultado_errostatus.png)



#### DELETE


No metodo delete, utilizamos o metodo static conflito do record ErroStatus para dizer que não podemos deletar um autor que tenha livros associado:


![imgagem local](/imagem_readme/Controller/AutorController/delete_metodo_atualizado.png)



O retorno no Postman:


![imgagem local](imagem_readme/Postman/resultado_autor/delete_resultado_atualizado.png)


### Validação no Projeto

O validation serve para facilitar a implementação de validações em aplicações Spring, especialmente em DTOs (Data Transfer Objects) usados em requisições HTTP. Como se fosse constraints, e ao você violar alguma ele retornar um error e não deixa a requisição ir adiante

Para começar a implemetanção de validações precisamos implementar no nosso arquivo xml um novo starter:


![imgagem local](imagem_readme/starter_validation.png)



Ele vai servi para habilitar nossas anotações de validações. 


Agora começamos a personalizar nosso Record DTO:


![imgagem local](/imagem_readme/Controller/autorDTO/autorDTO_validation.png)



Adicionando básicamente as constrants que inicializamos ao criar as tabelas no banco de dados. No nosso Controller precisamos utilizar a anotação @Valid para ativar a validação automática de objetos com base nas regras definidas no nosso validation, no caso no Record DTO.

Agora criamos a classe GlobalExceptionHandle em conjunto com a anotação @RestControllerAdvice para capturar qualquer classe de exceção, e retornar um Body para nossa requisão em formato de erro:


![imgagem local](/imagem_readme/Controller/common/classe_globalExceptionHandler.png)



Utilizamos um metodo para retornar o record ErrosResposta já que ele e utilizado como retorno no catch dos nosso métodos como body de um response entity.

Os retornos ao forçar alguns erros, a começar por erro de data de nascimento ser futura:


![imgagem local](/imagem_readme/Controller/common/retornos/somentedatapassada.png)


Agora campos nullos:


![imgagem local](imagem_readme/Controller/common/retornos/campoforadopadrao_e_campoobrigatorio.png)



#### Melhoria no pesquisar com query by example 

Query by Example (QBE) é um recurso do Spring Data que permite realizar consultas dinâmicas a partir de um objeto de exemplo. Com QBE, você cria uma instância de uma entidade com os valores desejados e o Spring automaticamente gera uma consulta baseada nesses valores. O QBE é útil para consultas flexíveis sem precisar escrever SQL ou JPQL manualmente.


![imgagem local](/imagem_readme/service/autor_service/pesquisarByExample.png)


Depois de implementar esse metodo na camada service na classe AutorService, fazemos a alteração do metodo na camada constroller para utilizar esse metodo. Na prática os retornos ficariam assim:


![imgagem local](/imagem_readme/Postman/resultado_autor/get_parametro/pesquisando_brasileiro.png)


![imgagem local](/imagem_readme/Postman/resultado_autor/get_parametro/pesquisando_com_nome_e_nacionalidade.png)



A primeira parte da nossa API foi desenvolvida focada na entidade Autor do nosso projeto, agora começamos a desenvolver a API focado na entidade Livro.


## API-Livros


Antes de começar a desenvolver a API de livros, precisamos atualizar alguns a entidade Livro.


A começar por adicionar os mesmo campos que foi adicionado na estidade Autor. Vai servi para deixar mais lógico nosso sistema:


![imgagem local](imagem_readme/API_Livro/Entidade_Livro/novos_campos_dataCadastro_dataAtualizacao_idUsuario.png)


Depois como regra de negocio, vamos atualizar o campo isbn, adicionando uma constrants para que ele não tenha dois livros com o mesmo isbn:


![imgagem local](/imagem_readme/API_Livro/Entidade_Livro/atualizacao_do_campo_isbn.png)


Proximo passo, criação da camada service e controller da nossa API livro. A começar pela Service:


![imgagem local](/imagem_readme/API_Livro/Livro_service/classe_LivroService.png)


E agora o controller:


![imgagem local](/imagem_readme/API_Livro/LivroController/classe_LivroController.png)


### Camamda DTO


Agora vamos utilizar também na API livros o [padrão DTO](https://devnit.medium.com/a-m%C3%A1gica-por-tr%C3%A1s-da-convers%C3%A3o-de-dados-o-padr%C3%A3o-dto-explicado-25f68a718b5a#:~:text=O%20padr%C3%A3o%20DTO%20%C3%A9%20mais,a%20efici%C3%AAncia%20do%20seu%20software.) para representar livros em dois aspectos, cadastro e resultado.

DTO cadastro:


![imgagem local](/imagem_readme/API_Livro/DTO/record_CadastroLivroDTO.png)


DTO de pesquisa:


![imgagem local](/imagem_readme/API_Livro/DTO/record_ResultadoPesquisaLivroDTO.png)



## Desenvolvendo a API


Antes de começar a desenvolver as comadas service e controller, vamos adicionar o [MapStruct](https://mapstruct.org/documentation/stable/reference/html/), ele vai servi como facilitador para mapear DTO em entidades e entidades em DTO. Você precisará configurar no xml o maven, todas as informações podem ser encontradas no site.

Agora podemos criar uma interface para representar nossos mappears para mapear nossos DTOs para entidades. A começar pela API de autor como exemplo:


![imgagem local](/imagem_readme/API_Livro/Mappers/interface_autorMapper.png)


A interface é anotada com o @Mapper que tem como parametro o "componentmodel, que serve para dizer que é essa interface é uma estrutura mapper, e o parametro vai servi para dizer que essa estrutura é gerenciada pelo spring, logo possa ser usada como container de injeção de dependencia.

Refatorando a camada controller da API de autor, ficaria assim:


![imgagem local](imagem_readme/Controller/AutorController/classe_autorcontroller_com_mapper.png)


Metodo POST:


![imgagem local](/imagem_readme/Controller/AutorController/Post_com_mapper.png)


Agora a criação da interface de LivroMapper:


![imgagem local](imagem_readme/API_Livro/Mappers/abstractClass_LivroMapper.png)



Como a entidade livro tem um autor, precisamos usar também o AutorRepository para agregar em nossas metodos, e como interfaces variaveis viram constantes, nesse caso, o melhor é a criação de uma abstract class, dessa forma posso injetar uma dependencia.

Antes de desenvolvermos a camada controller e service, iremos adicionar metodos para tratar algumas exceções na nossa classe GlobalExceptionHandle, dessa forma não precisaremos implementar um try catch para retornar nossos erros:


![imgagem local](/imagem_readme/API_Livro/GlobalHandleException/metodos_para_tratar_exceptions.png)

E também a implementação de uma interface com um metodo default para gerar o hearder de URI e retonar nos metodos que necessitam de URI:


![imgagem local](/imagem_readme/API_Livro/interface_GenericController.png)

Na camada service a implementação do metodo salvar livro para ser usada na camada controller:


![imgagem local](/imagem_readme/API_Livro/Livro_service/Post_metodo_salvarLivro.png)


Agora na implementação do nosso metodo POST na camada controller fica mais limpo e elegante:


![imgagem local](/imagem_readme/API_Livro/LivroController/Post_metodo.png)



Por fim o resultado no Postman:



![imgagem local](/imagem_readme/API_Livro/Postman/teste_LivroMapper.png)


Agora com o metodo Get, primeiro na camada service a implementação do metodo obterPorId:


![imgagem local](/imagem_readme/API_Livro/Livro_service/metodo_obterPorId.png)


Na camada Controller:


![imgagem local](imagem_readme/API_Livro/LivroController/GET_obterPorID.png)



Resultado no Postman:


![imgagem local](/imagem_readme/API_Livro/Postman/GET_obterPorID_resultado.png)



Agora com o metodo Delete, na camada Service primeiro:



![imgagem local](/imagem_readme/API_Livro/Livro_service/metodo_deletarLivro.png)



Na camada controller:


![imgagem local](imagem_readme/API_Livro/LivroController/delete_deletarLivro.png)



Antes de deletar, eu vou criar um livro primeiro para ser deletado:



![imgagem local](imagem_readme/API_Livro/Postman/novo_livro_criado_para_delete.png)



Agora com heardle do livro é só copiar e colar a url e deletar:


![imgagem local](/imagem_readme/API_Livro/Postman/delete_deletarLivro_resultado.png)












