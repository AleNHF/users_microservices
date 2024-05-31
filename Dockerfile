# Utiliza una imagen base de JDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo jar de la aplicación al contenedor
COPY target/users_services-0.0.1-SNAPSHOT.jar /app/users_services-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "users_services-0.0.1-SNAPSHOT.jar"]