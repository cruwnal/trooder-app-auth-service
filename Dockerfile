# Use Eclipse Temurin for a lightweight Java 21 runtime
FROM eclipse-temurin:21-jre-alpine

# Create a non-root user for better security (SOLID principle)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the JAR from the target folder
# Note: Ensure your pom.xml generates a JAR named 'auth-service.jar'
# or update the name below.
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port your auth-service runs on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]