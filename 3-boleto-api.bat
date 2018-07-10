@echo off
cd desafio-api
call gradle build
cd ..\
if "%1" == "all" start cmd /k  4-desafio-gateway.bat all
call java -jar "boleto-api/build/libs/boleto-api-0.0.1-SNAPSHOT.jar"
cd ..\