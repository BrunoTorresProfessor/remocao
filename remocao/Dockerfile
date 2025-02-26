FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de configuração e código-fonte para o container
COPY . /app

# Compila o projeto usando Maven
RUN ./mvnw package -DskipTests

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/remocao-0.0.1-SNAPSHOT.jar"]
