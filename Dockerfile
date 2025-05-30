# Etapa 1: Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml y luego descargamos dependencias para aprovechar cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el resto del código fuente
COPY src ./src

# Compilamos el proyecto sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera de ejecución
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el .jar desde la etapa de build
COPY --from=build /app/target/app.jar app.jar

ARG PORT=8080
EXPOSE ${PORT}

# Comando que ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
