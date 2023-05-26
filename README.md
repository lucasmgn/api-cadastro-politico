# API CADASTRO POLITICO

<h2> Informações</h2>

````text
Atualizando para banco de dados na Azure, SQL SERVER
````

<h3>Tecnologias utilizadas:</h3>
<ul>
  <li><strong>Spring Boot</strong></li>
  <li><strong>MySql</strong></li>
  <li><strong>Flyway</strong></li>
</ul>

<h3>Party - Listar</h3>

````text
curl --location 'http://localhost:8080/parties
````

<p>Filtrando por Ideology: </p>

````text
curl --location 'http://localhost:8080/parties?Ideology=left'
````

<p>Filtrando por name desc/asc:</p>

````text
curl --location 'http://localhost:8080/parties?sort=name%2Cdesc'
````
<p>Retorno de Sucesso:</p>

````json
[
  {
    "id": 1,
    "name": "Party da Social Democracia Brasileira",
    "acronym": "PSDB",
    "ideology": "Center",
    "foundation": "25-06-1988"
  },
  {
    "id": 2,
    "name": "Progressistas",
    "acronym": "PP",
    "ideology": "Right",
    "foundation": "14-05-1995"
  }
]
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "timestamp": "2023-04-06T00:13:49.043+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/partiess"
}
````

<h3>Party - Buscar</h3>

````text
curl --location 'http://localhost:8080/parties/4'
````

<p>Retorno de Sucesso:</p>

````json
{
  "id": 4,
  "name": "Novo",
  "acronym": "NOVO",
  "ideology": "Right",
  "foundation": "12-02-2011"
}
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "code": "PARTY_NOT_FOUND",
  "message": "Party not found",
  "details": [
    "PARTY_NOT_FOUND"
  ]
}
````

<h3>Party - Listar associates de Party</h3>

````text
curl --location 'http://localhost:8080/parties/4/associates'
````
<p>Retorno de Sucesso:</p>

````json
[
  {
    "id": 4,
    "name": "Thai Spinello",
    "office": "Alderman",
    "sex": "FEMALE",
    "birth": "12-07-1989"
  }
]
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "code": "PARTY_NOT_FOUND",
  "message": "Party not found",
  "details": [
    "PARTY_NOT_FOUND"
  ]
}
````
````json
{
  "timestamp": "2023-04-06T00:24:57.750+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/parties/4/associatess"
}
````
<h3>Party - Adicionar</h3>

````text
curl --location 'http://localhost:8080/parties' \
--header 'Content-Type: application/json' \
--data '{
    "name": "União Brasil",
    "acronym": "UNIAO",
    "ideology": "Right",
    "foundation": "26-02-2002"
}'
````
<p>Retorno de Sucesso:</p>

````json
{
  "id": 6,
  "name": "União Brasil",
  "acronym": "UNIAO",
  "ideology": "Right",
  "foundation": "26-02-2002"
}
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "code": "BAD_REQUEST",
  "message": "Invalid Request",
  "details": [
    "[acronym : não deve estar em branco]"
  ]
}
````
````json
{
  "timestamp": "2023-04-06T00:32:41.292+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/partiesw"
}
````

<h3>Party - Atualizar</h3>

````text
curl --location --request PUT 'http://localhost:8080/parties/1' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Partaido da Social Democracia Brasileira",
    "acronym": "PSDB",
    "ideology": "Right",
    "foundation": "26-02-2002"
}'
````
<p>Retorno de Sucesso:</p>

````json
{
  "id": 1,
  "name": "Partaido da Social Democracia Brasileira",
  "acronym": "PSDB",
  "ideology": "Right",
  "foundation": "26-02-2002"
}
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "code": "BAD_REQUEST",
  "message": "Invalid Request",
  "details": [
    "[acronym : não deve estar em branco]"
  ]
}
````
````json
{
  "timestamp": "2023-04-06T00:32:41.292+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/partiesw/1"
}
````

<h3>Party - Excluir</h3>

````text
curl --location --request DELETE 'http://localhost:8080/parties/6' \
--data ''
````
<p>Retorno de Sucesso:</p>

````text
204 - No Content
````

<p>Possíveis Retorno de Erro:</p>

````json
{
  "code": "ENTITY_IN_USE",
  "message": "Entity in Use",
  "details": [
    "Party code 4 cannot be removed as it is in use"
  ]
}
````
````json
{
  "code": "PARTY_NOT_FOUND",
  "message": "Party not found",
  "details": [
    "PARTY_NOT_FOUND"
  ]
}
````
````json
{
  "timestamp": "2023-04-06T00:32:41.292+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/partiesw/1"
}
````

<h4>associate - Listar</h4>

![Captura de Tela (143)](https://user-images.githubusercontent.com/81782608/204154488-b2ecaed4-43d7-489e-9dbf-474b8831b7cf.png)

<h4>associate - Buscar</h4>

![Captura de Tela (144)](https://user-images.githubusercontent.com/81782608/204154534-8bfc2a1e-8634-4d03-a1c3-90bda41ee13f.png)

<h4>associate - Adicionar</h4>

![Captura de Tela (145)](https://user-images.githubusercontent.com/81782608/204154566-038e41f9-127d-4c85-93bf-9e44561c666a.png)

<h4>associate - Vincular a um Party</h4>
<h5> Será retornado no content, mas se você listar novamente, verá o associate vinculado ao Party</h5>

![Captura de Tela (146)](https://user-images.githubusercontent.com/81782608/204154589-ac5988f1-053a-43b0-bf09-c0dad4e57d4b.png)

<h4>associate - Desvincular a um Party</h4>
<h5> Será retornado no content, mas se você listar novamente, verá o associate desvinculado ao Party</h5>

![Captura de Tela (147)](https://user-images.githubusercontent.com/81782608/204154723-de6cfcf6-554b-4f5a-82cc-896788256095.png)

<h4>associate - Atualizar</h4>

![Captura de Tela (148)](https://user-images.githubusercontent.com/81782608/204154765-9c4aa0bf-b389-4da2-b485-2ed903a38ec1.png)

<h4>associate - Excluir</h4>

![Captura de Tela (149)](https://user-images.githubusercontent.com/81782608/204154827-7eb6bc43-a257-48f4-97c7-6710da8877c6.png)


