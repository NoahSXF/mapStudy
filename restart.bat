@echo off
copy C:\Users\99451\Documents\GitHub\mapStudy\target\mapstudy-0.0.1-SNAPSHOT.jar C:\Users\99451\Documents\GitHub\mapStudy\mapstudy-0.0.1-SNAPSHOT.jar 
timeout /nobreak /t 5
set port=8080
for /f "tokens=1-5" %%i in ('netstat -ano^|findstr ":%port%"') do (
    echo kill the process %%m who use the port 
    taskkill /pid %%m -t -f
    goto q
)
:q
START "app" javaw -jar mapstudy-0.0.1-SNAPSHOT.jar