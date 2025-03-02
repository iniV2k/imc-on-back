FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

WORKDIR /app

COPY . /app

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build target/proj-imc-on-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]