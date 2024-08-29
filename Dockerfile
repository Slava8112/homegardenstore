# Используем официальный образ OpenJDK в качестве базового

FROM openjdk:21-jdk-slim
# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем ваш JAR файл в контейнер
COPY target/scotlandyard1-0.0.1-SNAPSHOT.jar /app/app.jar

# Указываем команду для запуска вашего приложения
CMD ["java", "-jar", "/app/app.jar"]