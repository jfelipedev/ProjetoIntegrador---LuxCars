FROM maven:3.9.0-amazoncorretto-17 AS build
WORKDIR /build
COPY . /build/
RUN mvn package -DskipTests
FROM amazoncorretto:17.0.6 AS run
WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]