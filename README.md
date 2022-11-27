# cadastroPolitico

<h2> Informações</h2>
<p>Na pasta resources estão os arquivos, applications.yml e dentro da pasta swagger tem o arquivo swagger.yaml.
  
<h3>Eu habilitei o cors do Spring, para poder fazer testes da requisição via Swagger</h3>

![Captura de Tela (126)](https://user-images.githubusercontent.com/81782608/204153254-349a73af-50c4-4795-b41f-056786303fe4.png)

<h3> Na classe WebConfig, foi habilitado o Cors e addFormatters para formatar Enuns que serão utilizados para filtros</h3>

![Captura de Tela (128)](https://user-images.githubusercontent.com/81782608/204153366-188be648-98b2-47ca-9e80-e28512613b8c.png)

<h3> Foram criadas duas classes para converter os Enums Ideologia e Cargo \src\main\java\br\com\sprint4\services</h3>

<h4>Na pasta service se encontram os dtos de requisição e de resposta, também a pasta assembler, que contem classes relacionadas a serialização de objetos</h4>
<h4>Também se encontram as classes de serviços</h4>

![Captura de Tela (130)](https://user-images.githubusercontent.com/81782608/204153529-21fbf85e-fd01-4e12-9473-73062eba96ef.png)

![Captura de Tela (131)](https://user-images.githubusercontent.com/81782608/204153759-8ee3b180-3270-4e76-b5f3-48b075aed977.png)

<h3>Na pasta exceptions, coloquei todas a classes para tratamentos de erros, a subpasta handler contem a captura de erros e seus tratamentos</h3>

![Captura de Tela (132)](https://user-images.githubusercontent.com/81782608/204153899-60873d2d-76c2-4429-b84b-702914b0c1a2.png)

<h3>A aplicação utiliza o banco de dados Mysql e faz versionamento com a utilização do Flyway</h3>

![Captura de Tela (134)](https://user-images.githubusercontent.com/81782608/204154016-2b0bd40f-2e4e-48d0-a53d-234ff1121618.png)

<h4>O arquivo application.yml</h4>

![Captura de Tela (136)](https://user-images.githubusercontent.com/81782608/204154068-98ae5c64-66ae-42b8-831b-d5be7e0367e4.png)

<h3>Postman</h3>

<h4>Partido - Listar</h4>

![Captura de Tela (137)](https://user-images.githubusercontent.com/81782608/204154189-f1309048-58a1-44da-8664-b1088ea88221.png)

<h4>Partido - Buscar</h4>

![Captura de Tela (138)](https://user-images.githubusercontent.com/81782608/204154231-1678bb2d-b3dc-419f-996f-5e5921d44533.png)

<h4>Partido - Listar Associados de Partido</h4>

![Captura de Tela (139)](https://user-images.githubusercontent.com/81782608/204154282-34b4575c-eb01-420e-a50d-25a9521d63d1.png)

<h4>Partido - Adicionar</h4>

![Captura de Tela (140)](https://user-images.githubusercontent.com/81782608/204154356-a1e301dc-d348-449e-823b-1798d73331b6.png)

<h4>Partido - Atualizar</h4>

![Captura de Tela (141)](https://user-images.githubusercontent.com/81782608/204154376-77c18643-89c9-4e14-a8d3-11b61e8c0754.png)

<h4>Partido - Excluir</h4>
<h5>Se um associado estiver no partido que você deseja excluir, será retornado um Conflict</h5>
  
![Captura de Tela (142)](https://user-images.githubusercontent.com/81782608/204154399-8efe2416-4269-419b-8e7c-c5b3482b1bb6.png)

![Captura de Tela (150)](https://user-images.githubusercontent.com/81782608/204154895-0ac35a01-f769-4b86-9a36-ea0344c7917e.png)

<h4>Associado - Listar</h4>

![Captura de Tela (143)](https://user-images.githubusercontent.com/81782608/204154488-b2ecaed4-43d7-489e-9dbf-474b8831b7cf.png)

<h4>Associado - Buscar</h4>

![Captura de Tela (144)](https://user-images.githubusercontent.com/81782608/204154534-8bfc2a1e-8634-4d03-a1c3-90bda41ee13f.png)

<h4>Associado - Adicionar</h4>

![Captura de Tela (145)](https://user-images.githubusercontent.com/81782608/204154566-038e41f9-127d-4c85-93bf-9e44561c666a.png)

<h4>Associado - Vincular a um partido</h4>
<h5> Será retornado no content, mas se você listar novamente, verá o Associado vinculado ao partido</h5>

![Captura de Tela (146)](https://user-images.githubusercontent.com/81782608/204154589-ac5988f1-053a-43b0-bf09-c0dad4e57d4b.png)

<h4>Associado - Desvincular a um partido</h4>
<h5> Será retornado no content, mas se você listar novamente, verá o Associado desvinculado ao partido</h5>

![Captura de Tela (147)](https://user-images.githubusercontent.com/81782608/204154723-de6cfcf6-554b-4f5a-82cc-896788256095.png)

<h4>Associado - Atualizar</h4>

![Captura de Tela (148)](https://user-images.githubusercontent.com/81782608/204154765-9c4aa0bf-b389-4da2-b485-2ed903a38ec1.png)

<h4>Associado - Excluir</h4>

![Captura de Tela (149)](https://user-images.githubusercontent.com/81782608/204154827-7eb6bc43-a257-48f4-97c7-6710da8877c6.png)


