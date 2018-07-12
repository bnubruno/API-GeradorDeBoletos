@echo off
cd desafio-config
call gradle build --info
cd ..\
if "%1" == "all" start cmd /k  run-eureka.bat all
call java -jar "desafio-config/build/libs/desafio-config-1.0.0.jar"



