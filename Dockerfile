# Step 1: Assembling the Project
FROM gradle:jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Stage 2: Runtime Environment
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar
EXPOSE 8080
# Command to start
ENTRYPOINT ["java", "-jar", "app.jar"]