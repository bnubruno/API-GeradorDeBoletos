@echo off
cd desafio-eureka
call gradle build --info
cd ..\
if "%1" == "all" start cmd /k  run-api.bat all
call java -jar "desafio-eureka/build/libs/desafio-eureka-1.0.0.jar"