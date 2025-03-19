FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8095  # Matches the port in application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]