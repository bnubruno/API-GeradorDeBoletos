@echo off
cd boleto-api
call gradle build 
cd ..\
if "%1" == "all" start cmd /k  run-gateway.bat all
call java -jar "boleto-api/build/libs/boleto-api-1.0.0.jar"
cd ..\