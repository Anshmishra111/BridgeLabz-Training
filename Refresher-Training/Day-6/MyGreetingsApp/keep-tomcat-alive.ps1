# keep-tomcat-alive.ps1
# Starts Tomcat and keeps it running. Run this script and minimise the window.
# Tomcat will restart automatically if it crashes.

$tomcat = "C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120"
$java   = "C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"

$env:JAVA_HOME     = $java
$env:JRE_HOME      = $java
$env:CATALINA_HOME = $tomcat
$env:CATALINA_BASE = $tomcat

Write-Host "=== Tomcat 9 Keeper ===" -ForegroundColor Cyan
Write-Host "Tomcat: $tomcat"
Write-Host "Java:   $java"
Write-Host ""
Write-Host "Starting Tomcat... (minimise this window, DO NOT CLOSE IT)" -ForegroundColor Yellow
Write-Host ""

# Run catalina.bat run — this blocks until Tomcat exits
& "$tomcat\bin\catalina.bat" run

Write-Host "Tomcat exited." -ForegroundColor Red
