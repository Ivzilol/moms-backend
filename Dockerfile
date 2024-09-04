FROM openjdk:22

WORKDIR /app

COPY mvnw* pom.xml ./
COPY .mvn /app/.mvn

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

RUN ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-jar"]