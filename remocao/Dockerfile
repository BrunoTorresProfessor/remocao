# Use uma imagem base com o Java 21 instalado
FROM openjdk:21-jdk-slim

# Instalar o Maven
RUN apt-get update && apt-get install -y maven

# Defina o diretório de trabalho para onde o código será copiado
WORKDIR /app

# Copiar o código do seu projeto para o container
COPY . .

# Executar o Maven para empacotar o aplicativo, sem rodar os testes
RUN mvn clean package -DskipTests

# Expor a porta que o seu aplicativo vai rodar (caso seja necessário)
EXPOSE 8080

# Comando para rodar o aplicativo, ajustado para o nome correto do JAR
CMD ["java", "-jar", "target/remocao-0.0.1-SNAPSHOT.jar"]
