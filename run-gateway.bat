@echo off
cd desafio-gateway
call gradle build --info
cd ..\
call java -jar "desafio-gateway/build/libs/desafio-gateway-0.0.1-SNAPSHOT.jar"
