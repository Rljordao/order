FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

WORKDIR /home/maven/src

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

EXPOSE 8081

RUN mkdir /app

COPY --from=build /home/maven/src/target/*.jar /app/api-order.jar

ENTRYPOINT ["java", "-jar", "/app/api-order.jar"]
