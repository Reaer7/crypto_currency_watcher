# syntax=docker/dockerfile:1
FROM maven:3.8.3-openjdk-17 AS builder
COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app/
RUN mvn clean package


FROM openjdk:17-alpine
WORKDIR /app/
COPY /target/watcher.jar ./
ENTRYPOINT ["java","-jar","watcher.jar"]

