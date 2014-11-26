::this script is to contruct and deploy the project easily. (assuming variable are set)
::you need to have tomcat as a service, CATALIBA_BASE set and maven =)
::by lighta

::back to root dir
cd .. 
::compile and package
mvn clean compile package 
::cleanup old webapp release
rmdir /Q /S %CATALINA_BASE%\webapps\lab3
del %CATALINA_BASE%\webapps\lab3.war 

::copy new one into webapp
copy target\lab3.war %CATALINA_BASE%\webapps\lab3.war

::relaunch tomcat
::%CATALINA_HOME%\bin\startup 
