# Usa a imagem do JDK 21
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . /app

# Dá permissão de execução ao Maven Wrapper
RUN chmod +x /app/mvnw

# Compila o projeto com Maven
RUN /app/mvnw clean package -DskipTests

# Expõe a porta do Spring Boot
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "/app/target/remocao-0.0.1-SNAPSHOT.jar"]