FROM openjdk:17.0.1-oracle
EXPOSE 8081
ADD target/Conditional-1.0.0.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
