::this script is to contruct and deploy the project easily. (assuming variable are set)
::you need to have tomcat as a service, CATALIBA_BASE set and maven =)
::by lighta

@echo off
cd ..
::mvn clean compile package

echo Copying war into webapp for tomcat
rmdir %CATALINA_BASE%\webapps\lab3 /s/q
del %CATALINA_BASE%\webapps\lab3.war
pause

xcopy target\lab3.war %CATALINA_BASE%\webapps\

::yes this is not stronk but whatever...
cd script
