FROM amazoncorretto:17
LABEL authors="KangShinGyu"
COPY build.gradle .
COPY src ./src

RUN gradle clean build
ARG JAR_FILE=build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
