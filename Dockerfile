FROM eclipse-temurin:21-jdk-alpine

RUN mkdir /sistema

WORKDIR /sistema

COPY target/*.jar onibusgo.jar

EXPOSE 3333

ENTRYPOINT ["java", "-jar", "onibusgo.jar"]