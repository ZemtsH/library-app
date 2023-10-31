# Используем базовый образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем MySQL клиент и wget
RUN apt-get update
RUN apt-get install -y default-mysql-client wget

# Загружаем корневой сертификат из Яндекс.Облака и сохраняем его в /etc/mysql/
RUN mkdir -p /etc/mysql/ && \
    wget "https://storage.yandexcloud.net/cloud-certs/CA.pem" \
    --output-document /etc/mysql/root.crt && \
    chmod 0600 /etc/mysql/root.crt

# Устанавливаем рабочий каталог внутри контейнера
WORKDIR /app

# Копируем JAR-файл в контейнер
ARG JAR_FILE=target/messenger-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Открываем порт 8080
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]