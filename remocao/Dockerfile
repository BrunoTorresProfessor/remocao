# Etapa de construção
FROM ubuntu:latest AS build

# Atualiza pacotes e instala o OpenJDK 21 e Maven
RUN apt-get update && apt-get install -y openjdk-21-jdk maven

# Define a variável de ambiente JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Copia o código-fonte para o contêiner
COPY . .

# Compila o projeto usando Maven
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:21-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR gerado na etapa de build
COPY --from=build /target/spring-deploy-render-0.0.1-SNAPSHOT.jar app.jar

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
