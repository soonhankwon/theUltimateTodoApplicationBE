
# 소스 코드와 Gradle Wrapper 파일을 이미지 내로 복사
COPY . .

# 실행 권한 설정 및 Gradle 빌드 실행
RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM amazoncorretto:17
LABEL authors="KangShinGyu"

ARG JAR_FILE=build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


