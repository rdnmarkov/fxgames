FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/fxgames-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} fxgames.jar
ENTRYPOINT ["java","-jar","fxgames.jar","--spring.config.location=classpath://application-dev.yml"]