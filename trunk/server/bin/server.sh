#!/bin/sh


HOME=..
LIB=$HOME/lib
 
CP=$HOME/classes
CP=$CP:$LIB/mysql-connector-java-5.1.10-bin.jar
CP=$CP:$LIB/gna-jorm-runtime-05.11.jar
CP=$CP:$LIB/jetty-6.1.15.rc3.jar
CP=$CP:$LIB/jetty-util-6.1.15.rc3.jar
CP=$CP:$LIB/hessian-3.2.1.jar
CP=$CP:$LIB/servlet-api.jar 
CP=$CP:$LIB/click-2.0.1-incubating.jar
CP=$CP:$LIB/mail.jar
CP=$CP:$LIB/activation.jar
CP=$CP:$LIB/stringtree-json-2.0.9.jar
echo $CP

java -classpath $CP de.ama.Starter > $HOME/log/server.log 2>&1 &


