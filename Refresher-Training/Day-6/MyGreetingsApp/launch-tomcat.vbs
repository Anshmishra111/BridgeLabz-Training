Set WshShell = CreateObject("WScript.Shell")

' Set environment variables
WshShell.Environment("PROCESS")("JAVA_HOME") = "C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"
WshShell.Environment("PROCESS")("JRE_HOME")  = "C:\Program Files\RedHat\java-17-openjdk-17.0.19.0.10-1"
WshShell.Environment("PROCESS")("CATALINA_HOME") = "C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120"
WshShell.Environment("PROCESS")("CATALINA_BASE") = "C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120"

' Run catalina.bat run — 1 = show window, 0 = wait
' Window style 1 = normal, set to 2 for hidden but then Tomcat dies
' We use a minimized window so it's out of the way
WshShell.Run """C:\Users\anshm\Downloads\apache-tomcat-9.0.120-windows-x64\apache-tomcat-9.0.120\bin\catalina.bat"" run", 2, False

WScript.Echo "Tomcat started! Open: http://localhost:8080/MyGreetingsApp/greetings/"
