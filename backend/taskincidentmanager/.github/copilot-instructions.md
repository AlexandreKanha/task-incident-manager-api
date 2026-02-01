
# Copilot Instructions for Task Incident Manager API

## Project Architecture & Structure

## Architecture Overview
- **Framework:** Spring Boot (Java 17), layered architecture
- **Purpose:** RESTful API for managing tasks and incidents
- **Layers:**
  - `entity/`: JPA entities (e.g., `User`, `Task`, `TaskStatus`) using Lombok for boilerplate
  - `repository/`: Spring Data JPA interfaces (e.g., `UserRepository`, `TaskRepository`)
  - `service/`: Business logic (e.g., `UserService`, `TaskService`)
  - `controller/`: REST API endpoints (see `TaskController.java`, `UserController.java`)
  - `dto/`: Data transfer objects for API payloads (see `CreateTaskRequest.java`, `TaskResponse.java`)
- **Entry Point:** `TaskIncidentManagerApplication.java` (main class)
- **Configuration:** `src/main/resources/application.yml` (default port 8080, PostgreSQL DB config)

## Developer Workflows
- **Build:** Use Maven Wrapper: `./mvnw clean package`
- **Run:** `./mvnw spring-boot:run` or run main class in IDE
- **Test:** `./mvnw test` (see `TaskIncidentManagerApplicationTests.java` and `TaskControllerIntegrationTest.java`)
- **Debug:** Standard Spring Boot debug via IDE or Maven Wrapper
- **Dependencies:** Managed in `pom.xml` (Spring Boot, Data JPA, Validation, Lombok, PostgreSQL)

## Project-Specific Conventions
- **Entities:** Annotated with `@Entity`, plural table names (e.g., `users` for `User`)
- **Lombok:** Used for getters/setters in all entities; annotation processing enabled in Maven
- **Layering:**
  - Business logic in `service/`
  - Data access in `repository/`
  - REST endpoints in `controller/`
  - DTOs for API payloads in `dto/`
- **Configuration:** All config in `application.yml`; DB expects PostgreSQL
- **Testing:** Use Spring Boot test starter; integration tests in `test/java/com/alexandre/taskmanager/`

## Integration & Extensibility
- **Database:** PostgreSQL; configure connection in `application.yml`
- **Static/Template Resources:** Place in `resources/static/` or `resources/templates/` as needed
- **Spring Boot Plugins:** See `pom.xml` for build/test plugins

## Examples & Patterns
- **Entity Example:** [src/main/java/com/alexandre/taskmanager/entity/User.java](src/main/java/com/alexandre/taskmanager/entity/User.java)
- **Repository Example:** [src/main/java/com/alexandre/taskmanager/repository/UserRepository.java](src/main/java/com/alexandre/taskmanager/repository/UserRepository.java)
- **Service Example:** [src/main/java/com/alexandre/taskmanager/service/UserService.java](src/main/java/com/alexandre/taskmanager/service/UserService.java)
- **Controller Example:** [src/main/java/com/alexandre/taskmanager/controller/TaskController.java](src/main/java/com/alexandre/taskmanager/controller/TaskController.java)
- **DTO Example:** [src/main/java/com/alexandre/taskmanager/dto/CreateTaskRequest.java](src/main/java/com/alexandre/taskmanager/dto/CreateTaskRequest.java)
- **Test Example:** [src/test/java/com/alexandre/taskmanager/TaskIncidentManagerApplicationTests.java](src/test/java/com/alexandre/taskmanager/TaskIncidentManagerApplicationTests.java)

## Key Files & Directories
- [pom.xml](pom.xml) — dependencies, plugins, Java version
- [src/main/java/com/alexandre/taskmanager/entity/](src/main/java/com/alexandre/taskmanager/entity/) — JPA entities
- [src/main/java/com/alexandre/taskmanager/repository/](src/main/java/com/alexandre/taskmanager/repository/) — Data access
- [src/main/java/com/alexandre/taskmanager/service/](src/main/java/com/alexandre/taskmanager/service/) — Business logic
- [src/main/java/com/alexandre/taskmanager/controller/](src/main/java/com/alexandre/taskmanager/controller/) — REST endpoints
- [src/main/java/com/alexandre/taskmanager/dto/](src/main/java/com/alexandre/taskmanager/dto/) — DTOs
- [src/main/resources/application.yml](src/main/resources/application.yml) — configuration
- [HELP.md](HELP.md) — links to Spring/Maven docs

---
For more details, see Spring Boot and Maven documentation linked in [HELP.md](HELP.md).
