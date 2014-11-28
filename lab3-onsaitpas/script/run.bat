::Handling tomcat for windows
::well since this f* doesn't appear in tasklist we check if it's binded in 8080 then suppose itÈs tomcat

@echo off

set param1=%~1
IF "%param1%"=="restart" (
  set RESTART="1"
)

:checking
netstat -na | find "LISTENING" | find /C /I ":8080" > NUL
if %errorlevel%==0 (
  goto :running
)
goto :off

:running
echo tomcat is running
echo Stopping tomcat
%CATALINA_HOME%\bin\shutdown
if %RESTART%=="1" (
  goto :off
)
goto :eof

:off
echo tomcat is not running
echo Starting tomcat
%CATALINA_HOME%\bin\startup


:eof
