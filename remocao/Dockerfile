FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

# If using Maven wrapper
RUN ./mvnw clean package -X -DskipTests


# If using the system's Maven installation
#RUN mvn clean package -X -DskipTests

# Exposes the default Spring Boot port
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/remocao-0.0.1-SNAPSHOT.jar"]
