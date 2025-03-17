FROM openjdk:21
WORKDIR /app
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "user-service.jar"]
EXPOSE 8081