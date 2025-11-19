FROM eclipse-temurin:25
LABEL authors="noverlin"
COPY target/Chora_Auth-0.0.1.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
