FROM amazoncorretto:17
LABEL authors="rkdtl"
ARG JAR_FILE=build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
