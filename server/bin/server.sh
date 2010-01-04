#!/bin/sh


HOME=..
LIB=$HOME/lib
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
 
CP=$HOME/classes
CP=$CP:$LIB/mysql-connector-java-5.1.0-bin.jar
CP=$CP:$LIB/gna-jorm-runtime-05.11.jar
CP=$CP:$LIB/jetty-6.1.15.rc3.jar
CP=$CP:$LIB/jetty-util-6.1.15.rc3.jar
CP=$CP:$LIB/hessian-3.2.1.jar
CP=$CP:$LIB/servlet-api.jar 
CP=$CP:$LIB/click-2.0.1-incubating.jar
CP=$CP:$LIB/mail.jar
CP=$CP:$LIB/activation.jar
CP=$CP:$LIB/stringtree-json-2.0.9.jar

CP=$CP:$LIB/flex-messaging-remoting.jar
CP=$CP:$LIB/flex-messaging-common.jar
CP=$CP:$LIB/flex-messaging-core.jar
CP=$CP:$LIB/backport-util-concurrent.jar
CP=$CP:$LIB/flex-messaging-proxy.jar
CP=$CP:$LIB/commons-httpclient-3.0.1.jar
CP=$CP:$LIB/commons-logging.jar
echo $CP

java -classpath $CP $DEBUG de.ama.Starter > $HOME/log/server.log 2>&1 &


