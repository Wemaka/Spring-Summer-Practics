# Сборка с использованием Maven и JDK 21
FROM maven:3.9-eclipse-temurin-21 AS builder

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем pom.xml для кэширования зависимостей
COPY pom.xml .

# Скачиваем зависимости (этот слой будет кэшироваться, если pom.xml не изменился)
RUN mvn dependency:go-offline

# Копируем остальной исходный код
COPY src ./src

# Собираем приложение, пропуская тесты
RUN mvn package -DskipTests

# Создание минимального финального образа на основе JRE
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Копируем только скомпилированный jar-файл из стадии 'builder'
COPY --from=builder /app/target/*.jar app.jar

# Указываем порт, который слушает приложение
EXPOSE 8081

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]