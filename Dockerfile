FROM maven:3.6-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean compile install

FROM openjdk:8-alpine
COPY --from=build /usr/src/app/target/weather-app-0.0.1-SNAPSHOT.jar /usr/app/weather-app-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","weather-app-0.0.1-SNAPSHOT.jar"]