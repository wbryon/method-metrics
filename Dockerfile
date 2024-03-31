FROM openjdk:17 as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

FROM openjdk:17
COPY --from=build /app/target/*.jar app.jar

# Запустите приложение
ENTRYPOINT ["java","-jar","/app.jar"]
