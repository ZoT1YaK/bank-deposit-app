# ---- Build stage ----
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dquarkus.package.type=uber-jar

# ---- Runtime stage ----
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /work
COPY --from=build /app/target/*-runner.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
