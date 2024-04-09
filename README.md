# Приложение OpenSchool1

## Описание
OpenSchool1 - это приложение на Spring Boot, предназначенное для управления пользователями и отслеживания метрик выполнения методов. Приложение позволяет создавать, обновлять, удалять и получать информацию о пользователях, а также просматривать статистику времени выполнения методов.

## Требования

Для работы с приложением потребуется:
- JDK 17
- Maven
- Docker и Docker Compose (для запуска с использованием Docker)
- PostgreSQL (для локального запуска без Docker)

## Локальная настройка

### Настройка базы данных

Для локального запуска необходим PostgreSQL. Создайте базу данных и пользователя, затем обновите настройки в `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=pgadmin
spring.datasource.password=1111
```

### Сборка и запуск

1. Соберите приложение с помощью Maven:

    ```bash
    mvn mvn clean package -DskipTests
    ```

2. Запустите собранное приложение:

    ```bash
    java -jar target/openschool1-1.0.0.jar
    ```

## Запуск с использованием Docker

### Сборка и запуск

1. Соберите Docker-образ приложения:

    ```bash
    docker build -t openschool1 .
    ```

2. Запустите приложение и его зависимости с помощью Docker Compose:

    ```bash
    docker-compose up
    ```

## Работа с API

После запуска приложения API будет доступен по адресу `http://localhost:8080/api/`.

### Доступные эндпойнты:

- `GET /api/users` - получить список всех пользователей.
- `GET /api/users/{userId}` - получить информацию о пользователе по ID.
- `POST /api/users` - создать нового пользователя. Тело запроса должно содержать информацию о пользователе в формате JSON.
- `PATCH /api/users/{userId}` - обновить информацию о пользователе по ID.
- `DELETE /api/users/{userId}` - удалить пользователя по ID.

### Пример тела запроса для создания пользователя:

```json
{
  "login": "userlogin",
  "firstName": "FirstName",
  "middleName": "MiddleName",
  "lastName": "LastName",
  "age": 30,
  "address": {
    "street": "Main Street",
    "city": "CityName",
    "building": "1234"
  }
}
```

## Документация API

После запуска приложения документация API доступна через Swagger UI по адресу `http://localhost:8080/swagger-ui.html`.

## Проверка работоспособности

Для проверки работоспособности эндпойнтов можно использовать инструменты, такие как Postman или curl. Например, для получения списка всех пользователей:

```bash
curl -X GET http://localhost:8080/api/users