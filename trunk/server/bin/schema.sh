#!/bin/sh
HOME=..
LIB=$HOME/lib
 
CP=$HOME/classes
CP=$CP:$LIB/mysql-connector-java-5.1.10-bin.jar
CP=$CP:$LIB/gna-jorm-runtime-05.11.jar
echo $CP

java -classpath $CP de.ama.db.tools.SchemaManager flow
