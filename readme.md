# Desafio - Gerador de Boletos!

O **objetivo** do desafio é construir uma API REST para geração de boletos que será consumido por
um módulo de um sistema de gestão financeira de microempresas.
No final do desafio vamos ter os seguintes **endpoints** para:

 - Criar boleto
 - Listar boletos
 - Ver detalhes
 - Pagar um boleto
 - Cancelar um boleto

# Getting Started

## Pré requisitos
- Java 8
- Gradle 4.8 ou superior

## Iniciando a aplicação

A implementação deste desafio foi dividido em 5 grandes projetos, são eles:

 - desafio-config
 - desafio-eureka
 - boleto-api
 - desafio-gateway
 - desafio-base

Cada um precisa ser executado na sua ordem.

Para iniciar todos os projetos de uma única vez basta apenas executar o arquivo `run-all.bat` que fica na pasta raiz.

## Arquivos de configuração

Todos os arquivos de configuração ficam no projeto `desafio-config`.

## Portas

|Projeto| Porta|
|--|--|
| desafio-config | 8888
| desafio-eureka| 8761
| desafio-gateway| 8080
| boleto-api | 8780
