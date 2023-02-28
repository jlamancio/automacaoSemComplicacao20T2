#language: pt
Funcionalidade: Realizar login
  Testes da API de Login

  Cenario: Realizar Login com Sucesso
    Dado que tenha payload valido da API de Login
    Quando envio uma requisição do tipo POST de Login
    Entao valido que recebo status 200 no Response
    E armazeno o token que recebo do response de Login

  Cenario: Realizar Login com usuario invalido
    Dado que tenha um payload da API de login com as seguintes informações
      | email | invalido@email.com |
      | senha | 123456             |
    Quando envio uma requisição do tipo POST de Login
    Entao valido que recebo status 400 no Response
    E armazeno o token que recebo do response de Login

  Cenario: Realizar login com senha invalida
    Dado que tenha um payload da API de login com as seguintes informações
      | email | aluno@email.com |
      | senha | 1234567         |
    Quando envio uma requisição do tipo POST de Login
    Entao valido que recebo status 400 no Response
    E armazeno o token que recebo do response de Login