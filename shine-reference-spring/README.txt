
README
======

Pre-requistes : Maven 2.* must be installed and available from the command line.
On the first invocation this may take a long time to run depending on how upto date your maven repository is
as it will need to download all the necessary components. Run the Spring Services using the following command

> mvn jetty:run-war
The services should now be available at http://localhost:8090/Shine-Spring-Sample

> mvn package
Creates a war file that can be put into an application container like Tomcat


TODO: Put into pom.xml
http://download.java.net/maven/2/javax/transaction/jta/1.0.1B/
or
mvn install:install-file  -Dfile=./jta-1_0_1B-classes.zip -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar

