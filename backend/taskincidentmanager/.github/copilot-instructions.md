

# Copilot Instructions for Task Incident Manager API

## Architecture & Key Concepts

- **Framework:** Spring Boot (Java 17), layered (controller → service → repository → entity)
- **Purpose:** REST API for managing tasks and users, with robust validation and error handling
- **Layers:**
  - `entity/`: JPA entities (Lombok for boilerplate, plural table names)
  - `repository/`: Spring Data JPA interfaces (custom finders, e.g., `findAllByOrderByCreatedAtDesc`)
  - `service/`: Business logic, validation, and entity orchestration
  - `controller/`: REST endpoints, DTO mapping, OpenAPI annotations
  - `dto/`: API payloads (input/output separation)
- **Entry Point:** [src/main/java/com/alexandre/taskmanager/TaskIncidentManagerApplication.java](src/main/java/com/alexandre/taskmanager/TaskIncidentManagerApplication.java)
- **Configuration:** [src/main/resources/application.properties](src/main/resources/application.properties) (MySQL by default)
- **API Docs:** OpenAPI/Swagger via [OpenApiConfig.java](src/main/java/com/alexandre/taskmanager/config/OpenApiConfig.java)

## Developer Workflows

- **Build:** `./mvnw clean package` (uses Maven Wrapper)
- **Run:** `./mvnw spring-boot:run` or run main class in IDE
- **Test:** `./mvnw test` (JUnit 5, see [TaskControllerIntegrationTest.java](src/test/java/com/alexandre/taskmanager/TaskControllerIntegrationTest.java))
- **Debug:** Standard Spring Boot debug via IDE or Maven
- **API Docs:** Swagger UI at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Error Handling:** All errors handled globally ([GlobalExceptionHandler.java](src/main/java/com/alexandre/taskmanager/exception/GlobalExceptionHandler.java)), returns JSON with `error`, `message`, and `timestamp` fields
- **Test Scripts:** See [test-api.bat](test-api.bat) and [test-error-handling.ps1](test-error-handling.ps1) for API smoke/error tests

## Project-Specific Conventions

- **Entities:** Annotated with `@Entity`, plural table names (e.g., `users`)
- **Lombok:** Used for all entities; annotation processing enabled in Maven
- **DTOs:** Used for all API input/output; never expose entities directly
- **Validation:** Use `jakarta.validation` annotations in DTOs; errors return 400 with field details
- **Error Codes:**
  - 404: `NOT_FOUND` (see [ResourceNotFoundException.java](src/main/java/com/alexandre/taskmanager/exception/ResourceNotFoundException.java))
  - 400: `VALIDATION_ERROR` (field-level details)
  - 500: `INTERNAL_SERVER_ERROR`
- **Repository:** Custom query methods for sorting/filtering (e.g., `findAllByOrderByCreatedAtDesc`)
- **OpenAPI:** All controllers use `@Operation` and `@Tag` for API docs
- **Static Resources:** Place frontend files in [src/main/resources/static/](src/main/resources/static/)

## Integration & Extensibility

- **Database:** MySQL by default (see [application.properties](src/main/resources/application.properties)); update credentials as needed
- **API Docs:** OpenAPI config in [OpenApiConfig.java](src/main/java/com/alexandre/taskmanager/config/OpenApiConfig.java)
- **Testing:** Integration tests in [src/test/java/com/alexandre/taskmanager/](src/test/java/com/alexandre/taskmanager/)
- **Extending:** Add new features by following the controller → service → repository → entity → dto pattern

## Key Files & Examples

- [pom.xml](pom.xml): dependencies, plugins, Java version
- [TaskController.java](src/main/java/com/alexandre/taskmanager/controller/TaskController.java): REST endpoint pattern
- [TaskService.java](src/main/java/com/alexandre/taskmanager/service/TaskService.java): business logic
- [Task.java](src/main/java/com/alexandre/taskmanager/entity/Task.java): JPA entity with enums and relations
- [CreateTaskRequest.java](src/main/java/com/alexandre/taskmanager/dto/CreateTaskRequest.java): DTO with validation
- [GlobalExceptionHandler.java](src/main/java/com/alexandre/taskmanager/exception/GlobalExceptionHandler.java): error handling
- [test-api.bat](test-api.bat): API smoke test
- [test-error-handling.ps1](test-error-handling.ps1): error scenario tests

---
For more details, see [README.md](../README.md) and [HELP.md](HELP.md).
