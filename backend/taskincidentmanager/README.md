# 🛠️ Task Incident Manager API

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat-square&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

> RESTful API for managing tasks and incidents, following clean architecture and best practices.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Technologies](#-technologies--stack)
- [Architecture](#-architecture)
- [API Endpoints](#-api-endpoints)
- [Error Handling](#-validation--error-handling)
- [API Documentation](#-api-documentation)
- [How to Run](#-how-to-run)
- [Testing](#-testing)
- [Future Improvements](#-future-improvements)
- [Author](#-author)
- [License](#-license)

---

## 🚀 Overview

This project is a **RESTful Task Incident Manager API** built with **Java** and **Spring Boot**, designed to manage tasks, users, and incidents with robust validation and error handling.

The application exposes documented endpoints using **OpenAPI 3.0** and **Swagger UI**, following a layered architecture for maintainability and extensibility.

### Main Goals

✅ REST API design  
✅ CRUD for tasks and users  
✅ Proper error handling and validation  
✅ API documentation with OpenAPI / Swagger  
✅ Clean and maintainable backend structure  
✅ Comprehensive unit and integration tests  

---

## 🛠️ Technologies & Stack

| Technology | Purpose |
|------------|---------|
| **Java 17** | Programming language |
| **Spring Boot** | Application framework |
| **Spring Data JPA** | ORM and database access |
| **Lombok** | Boilerplate code reduction |
| **OpenAPI 3.0** | API specification |
| **Swagger UI** | Interactive API documentation |
| **JUnit 5** | Testing framework |
| **Mockito** | Mocking library for unit tests |
| **AssertJ** | Fluent assertions |
| **Maven** | Build and dependency management |
| **MySQL** | Database |
| **Git** | Version control |

---

## 📁 Architecture

The project follows a **layered architecture**:

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│  Controller │ ──▶ │   Service   │ ──▶ │ Repository  │
└─────────────┘     └─────────────┘     └─────────────┘
       │                   │                   │
       ▼                   ▼                   ▼
  HTTP Request      Business Logic         Database
  HTTP Response       Validation           Persistence
```

### Responsibilities

| Layer | Responsibility |
|-------|----------------|
| **Controller** | Handles HTTP requests and responses |
| **Service** | Contains business logic and validation |
| **Repository** | Data access with Spring Data JPA |
| **DTOs** | Isolates internal models from external contracts |
| **Entities** | JPA entities mapped to database tables |

### Benefits

- ✅ Low coupling
- ✅ High cohesion
- ✅ Easier testing and maintenance

---

## 🔗 API Endpoints

### Task Endpoints

- `POST /api/tasks` — Create a new task
- `GET /api/tasks` — List all tasks
- `GET /api/tasks/{id}` — Get task by ID
- `PUT /api/tasks/{id}` — Update task
- `DELETE /api/tasks/{id}` — Delete task

### User Endpoints

- `POST /api/users` — Create a new user
- `GET /api/users` — List all users
- `GET /api/users/{id}` — Get user by ID
- `PUT /api/users/{id}` — Update user
- `DELETE /api/users/{id}` — Delete user

### Health Check

- `GET /api/health` — Application health status

---

## ⚠️ Validation & Error Handling

The application validates:

- ✅ Required fields (e.g., name, email, task title)
- ✅ Valid email format for users
- ✅ Task status and priority enums
- ✅ Existence of referenced entities (e.g., user for a task)

### Error Response Example

```json
{
  "error": "Resource not found",
  "message": "Task with id 123 not found",
  "timestamp": "2026-02-03T10:30:00"
}
```

> ⚡ All errors are handled globally and return meaningful messages with timestamps.

---

## 📚 API Documentation

The API is fully documented using **OpenAPI 3.0** and **Swagger UI**.

After running the application, access:

🔗 **http://localhost:8080/swagger-ui.html**

### Features

- 📖 Endpoint exploration
- 📝 Request/response visualization
- 🧪 Easy manual testing

---

## ▶️ How to Run

### Prerequisites

- ☕ Java 17+
- 📦 Maven
- 🐬 MySQL (configure em `src/main/resources/application.properties`)

### Steps

```bash
# Clone the repository
git clone <your-repo-url>

# Navigate to project directory
cd task-incident-manager-api/backend/taskincidentmanager

# Run the application
./mvnw spring-boot:run
```

The application will start on:

🔗 **http://localhost:8080**

---

## 🧪 Testing

The project includes comprehensive tests using **JUnit 5**, **Mockito**, and **AssertJ**.

### Test Coverage

| Test Type | Description |
|-----------|-------------|
| ✅ **Unit Tests** | Service and validation logic |
| ✅ **Integration Tests** | End-to-end API and DB validation |
| ✅ **Error Handling** | Invalid input and edge cases |

### Run Tests

```bash
./mvnw test
```

---

## 🔮 Future Improvements

| Feature | Description |
|---------|-------------|
| ⚡ Docker | Add Docker support for containerization |
| 🔐 Security | Add authentication and authorization |
| 🏗️ CI/CD | Integrate with GitHub Actions or similar |
| 📊 Metrics | Add monitoring and metrics |
| 🔄 Multi-Tenancy | Support for multiple organizations |
| 💾 Redis | Add distributed caching |

---

## 👨‍💻 Author

<div align="center">

**Alexandre Kanha**

*Backend Developer | Java | APIs | Software Engineering*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/alexandre-lucas-kanha-353205199/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/AlexandreKanha)

</div>

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

⭐ **If you found this project helpful, consider giving it a star!** ⭐

</div>
