FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8096  # Matches the port in application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]