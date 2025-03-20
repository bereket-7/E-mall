# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file
COPY target/*.jar app.jar

# Expose the port
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]