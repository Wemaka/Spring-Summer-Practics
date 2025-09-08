# Сборка артефакта с использованием Maven и JDK 17
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Копируем pom.xml для кэширования зависимостей
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# Создание минимального финального образа на основе JRE
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Копируем только скомпилированный jar-файл из стадии 'builder'
COPY --from=builder /app/target/*.jar app.jar

# Приложение по умолчанию работает на порту 8080
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]