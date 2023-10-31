FROM amazoncorretto:17
LABEL authors="KangShinGyu"


# 소스 코드와 빌드 파일을 이미지 내로 복사
COPY build.gradle .
COPY src ./src

# Gradle 빌드 실행
RUN gradle clean build

ARG JAR_FILE=build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


