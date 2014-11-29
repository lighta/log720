::Building setup for project

@echo off

:define_java
IF "%JAVA_HOME%"=="" ( 
  set /p JAVA_HOME="Enter JAVA_HOME (path ou est installer le jdk) :  "
  echo Registering JAVA_HOME as %JAVA_HOME%
) 
set /p res="Using JAVA_HOME=%JAVA_HOME% (Y/N):  "
IF NOT "%res%"=="Y" (
  set JAVA_HOME=
  goto :define_java
)
set path=%PATH%;%JAVA_HOME%\bin
::set classpath=.


:define_catahome
IF "%CATALINA_HOME%"=="" ( 
  set /p CATALINA_HOME="Enter CATALINA_HOME (path de tomcat) :  "
  echo Registering CATALINA_HOME as %CATALINA_HOME%
) 
set /p res="Using CATALINA_HOME=%CATALINA_HOME% (Y/N):  "
IF NOT "%res%"=="Y" (
  set CATALINA_HOME=
  goto :define_catahome
) 
set path=%PATH%;%CATALINA_HOME%\bin

:define_catabase
IF "%CATALINA_BASE%"=="" ( 
  set /p CATALINA_BASE="Enter CATALINA_BASE (path ou faire notre instance projet de tomcat) :  "
  echo Registering CATALINA_BASE as %CATALINA_BASE%
) 
set /p res="Using CATALINA_BASE=%CATALINA_BASE% (Y/N):  "
IF NOT "%res%"=="Y" (
  set CATALINA_BASE=
  goto :define_catabase
)

:define_maven
IF "%MVN_HOME%"=="" ( 
  set /p MVN_HOME="Enter MVN_HOME (path ou est installer maven) :  "
  echo Registering MVN_HOME as %MVN_HOME%
) 
set /p res="Using MVN_HOME=%MVN_HOME% (Y/N):  "
IF NOT "%res%"=="Y" (
  set MVN_HOME=
  goto :define_maven
)
set path=%PATH%;%MVN_HOME%\bin

:: copy stuff from CATALINA_HOME to CATALINA_BASE
echo Preparation du CATALINA_BASE
IF EXIST %CATALINA_BASE% goto :catabase_exist else goto :catabase_create
:catabase_create
echo Creating CATALINA_BASE directory
set CATALINA_BASE_CREATION="1"
md %CATALINA_BASE%
md %CATALINA_BASE%\conf
md %CATALINA_BASE%\lib
md %CATALINA_BASE%\webapps
md %CATALINA_BASE%\temp
xcopy %CATALINA_HOME%\conf %CATALINA_BASE%\conf /e /s
xcopy %CATALINA_HOME%\lib %CATALINA_BASE%\lib /e /s
xcopy %CATALINA_HOME%\webapps %CATALINA_BASE%\webapps /e /s

goto :created_catabase


:catabase_exist
set /p res="Delete and recreate CATALINA_BASE ? (Y/N):  "
IF NOT "%res%"=="N" (
  rmdir %CATALINA_BASE% /s/q
  goto :catabase_create
)
echo Assuming CATALINA_BASE directory is fine

:created_catabase
echo CATALINA_BASE folder is ready 



:create_database
IF "%PSQL_HOME%"=="" ( 
  set /p PSQL_HOME="Enter PSQL_HOME (path ou se trouve psql) :  "
  echo Registering PSQL_HOME as "%PSQL_HOME%"
) 
set /p res="Using PSQL_HOME=%PSQL_HOME% (Y/N):  "
IF NOT "%res%"=="Y" (
  set PSQL_HOME=
  goto :create_database
)



