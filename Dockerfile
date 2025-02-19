# Этап сборки
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Копируем исходный код и собираем проект
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап запуска
FROM eclipse-temurin:17-jdk-alpine

# Копируем jar-файл из этапа сборки
COPY --from=build /app/target/*.jar app.jar

# Указываем команду для запуска Spring Boot приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
