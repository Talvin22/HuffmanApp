# Этап сборки
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап запуска
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]