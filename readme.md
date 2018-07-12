
# Desafio - Gerador de Boletos!

O **objetivo** do desafio é construir uma API REST para geração de boletos que será consumido por
um módulo de um sistema de gestão financeira de microempresas.
No final do desafio vamos ter os seguintes **endpoints** para:

 - Criar boleto
 - Listar boletos
 - Ver detalhes
 - Pagar um boleto
 - Cancelar um boleto

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
    call java -jar "build/libs/desafio-config-0.0.1-SNAPSHOT.jar"

ou

    run-config.bat

#### Eureka

    cd desafio-eureka
    call gradle build
    call java -jar "build/libs/desafio-eureka-0.0.1-SNAPSHOT.jar"
ou

    run-eureka.bat

#### API

    cd boleto-api
    call gradle build
    call java -jar "build/libs/boleto-api-0.0.1-SNAPSHOT.jar"
ou

    run-api.bat

#### Gateway

	cd desafio-gateway
    call gradle build
    call java -jar "build/libs/desafio-gateway-0.0.1-SNAPSHOT.jar"
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

# Testes

Total de testes: 32

![Testes automatizados](https://imagemhost.com.br/images/2018/07/11/image.png)

# Documentação

## Swagger

http://localhost:8080/rest/swagger-ui.html

![Documentação da API com Swagger](https://www.imagemhost.com.br/images/2018/07/11/swagger.png)