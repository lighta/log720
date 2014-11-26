#!/bin/bash
#this script is to contruct and deploy the project easily. (assuming variable are set)
#you need to have tomcat as a service, CATALIBA_BASE set and maven =)
#by lighta

cd .. #back to root dir
mvn clean compile package #compile and package
sudo rm -rf ${CATALINA_BASE}/webapps/lab3* #cleanup old webapp release
cp target/lab3.war ${CATALINA_BASE}/webapps/ #copy new one into webapp
sudo service tomcat restart #relaunch tomcat
