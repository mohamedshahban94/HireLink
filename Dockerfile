# Stage 1: Build the app using Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

# ✅ Make mvnw executable before running it
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/HireLink-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]