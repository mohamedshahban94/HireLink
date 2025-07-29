# Use Java 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar to app
COPY target/HireLink-0.0.1-SNAPSHOT.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
