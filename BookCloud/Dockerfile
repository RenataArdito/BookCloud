# Use a imagem oficial do OpenJDK
FROM openjdk:21-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie o jar gerado para dentro do container
COPY target/*.jar app.jar

# Exponha a porta padrão da aplicação Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]