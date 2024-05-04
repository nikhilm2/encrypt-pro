FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /target/blowalgo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/demo.jar"]
