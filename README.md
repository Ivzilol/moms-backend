# SupplyManager-Backend - Microservices Architecture Documentation

## Overview

The `Supply-Manager` project is a microservices-based architecture built with Spring Boot for the backend, and Docker for containerization.
It consists of several services that handle various aspects of the application, including user management, inventory, order processing, notifications, and more. The microservices communicate via REST APIs and use a variety of databases and technologies like MySQL, MongoDB, and Redis.

## Technologies Used

### 1. Spring Boot (v3.3.0)
Spring Boot is used as the foundation of the backend services. Each service is a Spring Boot application with the following capabilities:
- **REST APIs** for communication between services and the frontend.
- **Spring Data JPA** and **Hibernate** for ORM and database interaction.
- **Spring Security** for authentication and authorization, with JWT tokens.
- **Spring Cloud** for service discovery and configuration management.

### 2. Docker and Docker Compose
The project is containerized using Docker, with Docker Compose orchestrating the multi-container setup. Each microservice runs in its own container.
- **MySQL** is used as the relational database for transactional data.
- **MongoDB** is used for NoSQL storage where necessary.
- **Redis** is used for caching and messaging between services.
- **Docker Compose** file orchestrates the deployment of all services, databases, and Redis.

### 3. Databases
- **MySQL**: Used by several services for structured, relational data storage. A default MySQL container is set up with persistent volumes and environment variables for root credentials.
- **MongoDB**: Used for services that require flexible, document-oriented data storage.
- **Redis**: Provides caching and possibly messaging support for fast data access.

### 4. Service Discovery and API Gateway
The system uses **Spring Cloud Netflix Eureka** for service discovery. This ensures that all microservices can discover and communicate with each other.
- **Eureka Discovery Service** registers all services.
- **Spring Cloud Gateway** acts as the entry point for external requests, routing them to appropriate microservices.

### 5. Microservices
The project follows a microservices architecture, with each microservice responsible for a distinct domain. The main microservices include:
- **Authentication Service**: Handles user login and JWT-based authentication.
- **Authorization Service**: Manages roles, permissions, and access control.
- **User Command/Query Services**: Implements CQRS pattern for user data. Separate services for handling commands (creating, updating) and queries (reading user data).
- **Inventory Command/Query Services**: Follows CQRS pattern for inventory management.
- **Order Command/Query Services**: Handles order management, also using CQRS.
- **Notification Service**: Sends notifications to users based on certain events.

### 6. CQRS (Command Query Responsibility Segregation)
The system uses the **CQRS pattern** for services like **inventory**, **order**, and **user management**. By separating command and query operations, the application scales better, ensuring optimized reads and writes.

### 7. Messaging and Caching with Redis
Redis is used in this project to improve performance via caching and also supports message-based communication between microservices using Redis Pub/Sub patterns.

## Services Overview

### 1. Discovery Service
- Registers all microservices and enables dynamic service discovery.

### 2. Config Service
- Provides centralized configuration management for all services.

### 3. Gateway Service
- Acts as the API gateway for external requests and forwards them to internal microservices.

### 4. Authentication and Authorization
- **Authentication**: JWT tokens are used for secure communication.
- **Authorization**: Role-based access control ensures proper permissions.

### 5. User, Inventory, and Order Services
These services implement the CQRS pattern:
- **Command Services** handle data modifications (Create, Update, Delete).
- **Query Services** handle data retrieval and complex queries.

## Running the Application

### Prerequisites
- **Node.js** (for any frontend services)
- **Java 17** (for backend services)
- **Docker and Docker Compose**

### Steps to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd moms-backend
   ```

3. Build and start the services using Docker Compose:
   ```bash
   docker-compose up --build
   ```

4. Access the services via the API Gateway:
    - **Gateway URL**: `http://localhost:8080`