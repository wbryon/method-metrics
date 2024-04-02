FROM openjdk:17

WORKDIR /app

COPY ./target/openschool1-1.0.0.jar app.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]