# Usar una imagen base de OpenJDK 17
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY target/users_services-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]