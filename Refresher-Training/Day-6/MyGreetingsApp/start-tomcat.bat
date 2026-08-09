@echo off
title Tomcat 9 - MyGreetingsApp [KEEP OPEN]
echo ============================================
echo  Tomcat 9 - Spring MVC My Greetings App
echo  DO NOT CLOSE THIS WINDOW
echo ============================================
echo.

set "JAVA_HOME=C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"
set "JRE_HOME=C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"
set "CATALINA_HOME=C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120"
set "CATALINA_BASE=C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120"

echo JAVA_HOME=%JAVA_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo.
echo Starting Tomcat... Open http://localhost:8080/MyGreetingsApp/greetings/ in your browser
echo.

"%CATALINA_HOME%\bin\catalina.bat" run

echo.
echo Tomcat stopped. Press any key to exit.
pause
