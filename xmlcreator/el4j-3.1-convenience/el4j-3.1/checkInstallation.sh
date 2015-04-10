#!/bin/sh

echo java -version:
java -version
echo -----------------------------------------

echo javac -version:
javac -version
echo -----------------------------------------

echo mvn -version
mvn -version
echo -----------------------------------------

echo echo \$MAVEN_OPTS
echo $MAVEN_OPTS
echo -----------------------------------------
echo -----------------------------------------

echo expected output:
echo Must print out the version number of a Java 6 JDK or newer.
echo Must print out the same version number as above.
echo Must print out the version "3.0.1" or newer.
echo Must print something like ea -Xmx1024M -Xss128k -XX:MaxPermSize=256M -Duser.language=en -Duser.region=US -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Dcom.sun.management.jmxremote
 
