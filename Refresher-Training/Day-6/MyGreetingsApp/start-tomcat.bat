@echo off
title GreetingsApplication [KEEP OPEN]
echo ========================================================
echo  GreetingsApplication (Embedded Tomcat)
echo  DO NOT CLOSE THIS WINDOW
echo ========================================================
echo.

set "JAVA_HOME=C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Running GreetingsApplication using Maven...
echo.

"C:\Users\anshm\apache-maven-3.9.16\apache-maven-3.9.16\bin\mvn.cmd" spring-boot:run

echo.
echo Application stopped. Press any key to exit.
pause
