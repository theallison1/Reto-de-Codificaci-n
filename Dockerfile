# Usa Maven para construir la aplicación
FROM maven:3.9.0-openjdk-17-slim AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente de la aplicación
COPY . .

# Ejecuta el comando de Maven para construir el JAR
RUN mvn clean install -DskipTests

# Usa OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia el JAR generado desde el contenedor anterior
COPY --from=build /app/target/retoCodificacion-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
