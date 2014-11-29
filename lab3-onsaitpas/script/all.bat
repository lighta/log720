::Handling tomcat for windows
::well since this f* doesn't appear in tasklist we check if it's binded in 8080 then suppose it»s tomcat

@echo off

call log720.bat
set /p res="Skipping setup ? : (Y/N) "
IF NOT "%res%"=="Y" (
  call setup.bat
)
set /p res="Skipping DB creation ? : (Y/N) "
IF NOT "%res%"=="Y" (
  call prep_sql.bat
)
call deploy.bat
call run.bat restart

:eof
