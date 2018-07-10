@echo off
cd desafio-eureka
call gradle build
cd ..\
if "%1" == "all" start cmd /k  3-desafio-api.bat all
call java -jar "desafio-eureka/build/libs/desafio-eureka-0.0.1-SNAPSHOT.jar"