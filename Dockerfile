FROM openjdk:22

WORKDIR /app

COPY mvnw* pom.xml ./
COPY .mvn /app/.mvn

COPY user-query-service /app/user-query-service
COPY authentication-service /app/authentication-service
COPY authorization-service /app/authorization-service
COPY config-service /app/config-service
COPY discovery-service /app/discovery-service
COPY file-storage-service /app/file-storage-service
COPY gateway-service /app/gateway-service
COPY inventory-command-service /app/inventory-command-service
COPY inventory-query-service /app/inventory-query-service
COPY notification-service /app/notification-service
COPY order-command-service /app/order-command-service
COPY order-query-service /app/order-query-service
COPY user-command-service /app/user-command-service

COPY application-env.properties /app/application-env.properties


RUN ./mvnw clean package -DskipTests
