FROM maven:3.9.5-eclipse-temurin-21-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /sistema
COPY --from=build /app/target/*.jar onibusgo.jar
EXPOSE 3333
ENTRYPOINT ["java", "-jar", "onibusgo.jar"]