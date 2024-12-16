FROM maven:3.8.6-openjdk-17-slim AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente de la aplicación
COPY . .

# Ejecuta el comando de Maven para construir el JAR
RUN mvn clean install -DskipTests

# Ahora copia el JAR generado
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/retoCodificacion-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
