FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-boot-crud-example-2-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8443
ENTRYPOINT ["java","-jar","/app.jar"]
