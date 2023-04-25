FROM amazoncorretto:17-alpine

WORKDIR /app

# Create directories
RUN mkdir -p build

# Copy the built JAR files to the working directory
COPY build/libs/*.jar build/libs/

# Expose the port that the application listens on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "build/libs/questions-0.0.1-SNAPSHOT.jar"]