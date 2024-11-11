FROM gradle:7.5.0-jdk17 AS build
COPY . /app
WORKDIR /oms-order-manager

FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/oms-order-manager-0.0.1-SNAPSHOT.jar /oms-order-manager/oms-order-manager-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/oms-order-manager/oms-order-manager-0.0.1-SNAPSHOT.jar"]