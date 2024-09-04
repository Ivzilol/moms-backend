FROM openjdk:22

WORKDIR /app

COPY mvnw* pom.xml ./
COPY .mvn /app/.mvn

COPY authentication-service/src /app/authentication-service/src
COPY authorization-service/src /app/authorization-service/src
COPY config-service/src /app/config-service/src
COPY discovery-service/src /app/discovery-service/src
COPY file-storage-service/src /app/file-storage-service/src
COPY gateway-service/src /app/gateway-service/src
COPY inventory-command-service/src /app/inventory-command-service/src
COPY inventory-query-service/src /app/inventory-query-service/src
COPY notification-service/src /app/notification-service/src
COPY order-command-service/src /app/order-command-service/src
COPY order-query-service/src /app/order-query-service/src
COPY user-command-service/src /app/user-command-service/src
COPY user-query-service/src /app/user-query-service/src

RUN ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-jar"]