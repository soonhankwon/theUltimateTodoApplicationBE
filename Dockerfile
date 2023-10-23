# Build stage
FROM adoptopenjdk/openjdk17
WORKDIR /app
COPY . .
RUN ./gradlew build

# Run stage
FROM adoptopenjdk/openjdk17
WORKDIR /app
COPY --from=build /app/build/libs/theUltimateTodo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
