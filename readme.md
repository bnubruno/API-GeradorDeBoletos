


# Desafio - Gerador de Boletos!

O **objetivo** do desafio é construir uma API REST para geração de boletos que será consumido por
um módulo de um sistema de gestão financeira de microempresas.
No final do desafio vamos ter os seguintes **endpoints** para:

 - Criar boleto
 - Listar boletos
 - Ver detalhes
 - Pagar um boleto
 - Cancelar um boleto

## Demonstração

[Link](#%20Desafio%20-%20Gerador%20de%20Boletos!%20%20O%20**objetivo**%20do%20desafio%20%C3%A9%20construir%20uma%20API%20REST%20para%20gera%C3%A7%C3%A3o%20de%20boletos%20que%20ser%C3%A1%20consumido%20por%20um%20m%C3%B3dulo%20de%20um%20sistema%20de%20gest%C3%A3o%20financeira%20de%20microempresas.%20No%20final%20do%20desafio%20vamos%20ter%20os%20seguintes%20**endpoints**%20para:%20%20%20-%20Criar%20boleto%20%20-%20Listar%20boletos%20%20-%20Ver%20detalhes%20%20-%20Pagar%20um%20boleto%20%20-%20Cancelar%20um%20boleto%20%20#%20Come%C3%A7ando%20%20##%20Pr%C3%A9%20requisitos%20-%20%5BJava%5D%28https://java.com/pt_BR/download/%29%208%20-%20%5BGradle%5D%28https://gradle.org/%29%204.8%20ou%20superior%20%20##%20Iniciando%20a%20aplica%C3%A7%C3%A3o%20%20A%20implementa%C3%A7%C3%A3o%20deste%20desafio%20foi%20dividido%20em%205%20grandes%20projetos,%20s%C3%A3o%20eles:%20%20%20-%20desafio-config%20%20-%20desafio-eureka%20%20-%20boleto-api%20%20-%20desafio-gateway%20%20-%20desafio-base%20%20Para%20iniciar%20todos%20os%20projetos%20de%20uma%20%C3%BAnica%20vez%20basta%20apenas%20executar%20o%20arquivo%20%60run-all.bat%60%20que%20fica%20na%20pasta%20raiz.%20%20###%20Iniciando%20manualmente%20%20####%20Config:%20%20%20%20%20%20cd%20desafio-config%20%20%20%20%20call%20gradle%20build%20%20%20%20%20call%20java%20-jar%20%22build/libs/desafio-config-1.0.0.jar%22%20%20ou%20%20%20%20%20%20run-config.bat%20%20####%20Eureka%20%20%20%20%20%20cd%20desafio-eureka%20%20%20%20%20call%20gradle%20build%20%20%20%20%20call%20java%20-jar%20%22build/libs/desafio-eureka-1.0.0.jar%22%20ou%20%20%20%20%20%20run-eureka.bat%20%20####%20API%20%20%20%20%20%20cd%20boleto-api%20%20%20%20%20call%20gradle%20build%20%20%20%20%20call%20java%20-jar%20%22build/libs/boleto-api-1.0.0.jar%22%20ou%20%20%20%20%20%20run-api.bat%20%20####%20Gateway%20%20%09cd%20desafio-gateway%20%20%20%20%20call%20gradle%20build%20%20%20%20%20call%20java%20-jar%20%22build/libs/desafio-gateway-1.0.0.jar%22%20%20%20%20ou%20%20%20%20%20%20%20%20%20run-gateway.bat%20%20%20%20%20%20##%20Arquivos%20de%20configura%C3%A7%C3%A3o%20%20Todos%20os%20arquivos%20de%20configura%C3%A7%C3%A3o%20ficam%20no%20projeto%20%60desafio-config%60.%20%20##%20Portas%20%20%7CProjeto%7C%20Porta%7C%20%7C--%7C--%7C%20%7C%20desafio-config%20%7C%208888%20%7C%20desafio-eureka%7C%208761%20%7C%20desafio-gateway%7C%208080%20%7C%20boleto-api%20%7C%208780%20%20##%20Ambientes%20%20###%20Desenvolvimento%20%20Para%20utilizar%20a%20API%20em%20modo%20desenvolvimento:%20%20%20%20%20%20-Dspring.profiles.active=dev%20%20###%20Testes%20%20Para%20utilizar%20a%20API%20em%20modo%20de%20testes:%20%20%20%20%20%20-Dspring.profiles.active=test%20%20###%20Heroku%20%28Simplificado%29%20%20Esta%20aplica%C3%A7%C3%A3o%20foi%20deployada%20no%20Heroku%20em%20modo%20simplificado.%20%20%20%20%20%20-Dspring.profiles.active=heroku%20%20#%20Testes%20%20Total%20de%20testes:%2032%20%20!%5BTestes%20automatizados%5D%28https://imagemhost.com.br/images/2018/07/11/image.png%29%20%20#%20Documenta%C3%A7%C3%A3o%20%20##%20Swagger%20%20https://desafio-bnubruno.herokuapp.com/rest/swagger-ui.html%20%20!%5BDocumenta%C3%A7%C3%A3o%20da%20API%20com%20Swagger%5D%28https://image.ibb.co/hyTiMo/image.png%29%20%20##%20HATEOAS%20%20A%20URL%20%60/bankslips/hateoas%60%20foi%20inserida%20para%20demonstrar%20o%20uso%20de%20HATEOAS.%20%20Exemplo%20de%20resposta:%20%20!%5Benter%20image%20description%20here%5D%28https://image.ibb.co/eQLG7T/image.png%29) do Heroku

# Começando

## Pré requisitos
- [Java](https://java.com/pt_BR/download/) 8
- [Gradle](https://gradle.org/) 4.8 ou superior

## Iniciando a aplicação

A implementação deste desafio foi dividido em 5 grandes projetos, são eles:

 - desafio-config
 - desafio-eureka
 - boleto-api
 - desafio-gateway
 - desafio-base

Para iniciar todos os projetos de uma única vez basta apenas executar o arquivo `run-all.bat` que fica na pasta raiz.

### Iniciando manualmente

#### Config:

    cd desafio-config
    call gradle build
    call java -jar "build/libs/desafio-config-1.0.0.jar"

ou

    run-config.bat

#### Eureka

    cd desafio-eureka
    call gradle build
    call java -jar "build/libs/desafio-eureka-1.0.0.jar"
ou

    run-eureka.bat

#### API

    cd boleto-api
    call gradle build
    call java -jar "build/libs/boleto-api-1.0.0.jar"
ou

    run-api.bat

#### Gateway

	cd desafio-gateway
    call gradle build
    call java -jar "build/libs/desafio-gateway-1.0.0.jar"
   ou
   
    run-gateway.bat
    
## Arquivos de configuração

Todos os arquivos de configuração ficam no projeto `desafio-config`.

## Portas

|Projeto| Porta|
|--|--|
| desafio-config | 8888
| desafio-eureka| 8761
| desafio-gateway| 8080
| boleto-api | 8780

## Ambientes

### Desenvolvimento

Para utilizar a API em modo desenvolvimento:

    -Dspring.profiles.active=dev

### Testes

Para utilizar a API em modo de testes:

    -Dspring.profiles.active=test

### Heroku (Simplificado)

Esta aplicação foi deployada no Heroku em modo simplificado.

    -Dspring.profiles.active=heroku

# Testes

Total de testes: 32

![Testes automatizados](https://imagemhost.com.br/images/2018/07/11/image.png)

# Documentação

## Swagger

https://desafio-bnubruno.herokuapp.com/rest/swagger-ui.html

![Documentação da API com Swagger](https://image.ibb.co/hyTiMo/image.png)

## HATEOAS

A URL `/bankslips/hateoas` foi inserida para demonstrar o uso de HATEOAS.

Exemplo de resposta:

![enter image description here](https://image.ibb.co/eQLG7T/image.png)
