# Gradle 이미지를 사용하여 빌드 환경 설정
FROM gradle:7.3.0-jdk17 AS builder
WORKDIR /app

# 소스 코드와 빌드 파일을 이미지 내로 복사
COPY build.gradle .
COPY src ./src

# Gradle 빌드 실행
RUN gradle clean build

# 런타임 이미지 설정
FROM openjdk:17-jdk

# 메타데이터 설정
LABEL authors="KangShinGyu"

# 빌드된 JAR 파일을 런타임 이미지로 복사
ARG JAR_FILE=/app/build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} app.jar

# 앱 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]