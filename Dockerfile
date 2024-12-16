# Usa OpenJDK 17 como base
FROM openjdk:17-jdk-slim AS build

# Instalar Maven manualmente
RUN apt-get update && apt-get install -y maven

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente de la aplicación
COPY . .

# Ejecuta el comando de Maven para construir el JAR
RUN mvn clean install -DskipTests

# Usa OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en el paso anterior
COPY --from=build /app/target/retoCodificacion-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
