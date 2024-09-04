FROM openjdk:22

WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

COPY authentication-service/src /app/authentication-service/src
COPY authorization-service/src /app/authorization-service/src
COPY config-service/src /app/config-service/src
COPY config-service/src /app/discovery-service/src
COPY config-service/src /app/file-storage-service/src
COPY config-service/src /app/gateway-service/src
COPY config-service/src /app/inventory-command-service/src
COPY config-service/src /app/inventory-query-service/src
COPY config-service/src /app/notification-service/src
COPY config-service/src /app/order-command-service/src
COPY config-service/src /app/order-query-service/src
COPY config-service/src /app/user-command-service/src
COPY config-service/src /app/user-query-service/src


# Package each service using Maven
RUN ./mvnw clean package -DskipTests

# Set the entrypoint to run a service; this will be overridden in docker-compose
ENTRYPOINT ["java", "-jar"]