Integrating Apache CXF and Spring Security with Embedded Jetty 
==============

mvn clean package   
java -jar target/jax-rs-2.0-spring-security-0.0.1-SNAPSHOT.jar

 - HTTP/200: curl -u user:password http://localhost:8080/rest/api/people
 - HTTP/401: curl http://localhost:8080/rest/api/people 