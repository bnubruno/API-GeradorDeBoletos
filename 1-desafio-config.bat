@echo off
cd desafio-config
call gradle build
cd ..\
if "%1" == "all" start cmd /k  2-desafio-eureka.bat all
call java -jar "desafio-config/build/libs/desafio-config-0.0.1-SNAPSHOT.jar"



