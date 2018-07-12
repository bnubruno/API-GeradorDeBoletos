



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

[Link](https://desafio-bnubruno.herokuapp.com/rest/swagger-ui.html#/bank-slip-endpoint) do Heroku

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

# FAQ

### 1 - Boleto-API não inicia pelo eclipse, o que fazer?

Para que o MapStruc funcione no eclipse é necessário rodar o comando de build do gradle no projeto 


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

[Link](https://desafio-bnubruno.herokuapp.com/rest/swagger-ui.html#/bank-slip-endpoint) do Heroku

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

# FAQ

### 1 - Como configurar o Map Struc no Eclipse?

Não se desespere. Você configurou o output do mapstruct no Build Path do projeto? Para isso execute o comando abaixo na pasta do projeto 'boleto-api':

    gradle build

No eclipse, clique botão direito no projeto 'boleto-api' -> properties -> Java Build Path, na aba Source adicione um novo folder para a pasta `build/generated/source/apt/main` como na imagem abaixo.

![enter image description here](https://image.ibb.co/f0YNXT/image.png)


