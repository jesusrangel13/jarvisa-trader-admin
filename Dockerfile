# Especifica la imagen base de Gradle para compilar tu aplicación
FROM gradle:7.4.0-jdk17 AS builder

# Copia los archivos de tu proyecto al contenedor
COPY . /home/gradle/src

# Establece el directorio de trabajo en el directorio de tu proyecto
WORKDIR /home/gradle/src

# Compila tu aplicación con Gradle
RUN gradle build --no-daemon

# Crea una nueva imagen a partir de una imagen base de Java
FROM openjdk:17-oracle

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado desde la etapa anterior al contenedor
COPY --from=builder /home/gradle/src/build/libs/jarvis.bot-0.0.1-SNAPSHOT /app/app.jar

# Expone el puerto en el que se ejecutará tu aplicación
EXPOSE 8080

# Comando para ejecutar tu aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]
