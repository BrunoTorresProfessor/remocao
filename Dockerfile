FROM eclipse-temurin:21-jdk

# Install Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copy the pom.xml and source files
COPY /pom.xml /app/
COPY /src /app/src

# Run Maven build
RUN mvn clean package -DskipTests

# Expose default Spring Boot port
EXPOSE 8080

# Command to run the Spring Boot app
CMD ["java", "-jar", "target/remocao-0.0.1-SNAPSHOT.jar"]
