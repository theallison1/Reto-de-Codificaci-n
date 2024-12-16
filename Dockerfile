# Usamos la imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR generado a la imagen
COPY target/retoCodificacion-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto en el que la aplicación escuchará
EXPOSE 8080

# Cargar variables de entorno desde .env si es necesario
COPY .env .env

# Comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
