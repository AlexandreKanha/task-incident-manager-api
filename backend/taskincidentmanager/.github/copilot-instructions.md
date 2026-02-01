
# Copilot Instructions for Task Incident Manager API

## Project Architecture & Structure
- **Framework:** Spring Boot (Java 17), layered architecture
- **Purpose:** RESTful API for managing tasks and incidents
- **Key Layers:**
  - `entity/` — JPA entities (e.g., `User`, `Task`, `TaskStatus`), use Lombok for boilerplate
  - `repository/` — Spring Data JPA interfaces (e.g., `UserRepository`, `TaskRepository`)
  - `service/` — Business logic (e.g., `UserService`, `TaskService`)
  - `controller/` — REST API endpoints (currently empty, add new endpoints here)
  - `dto/` — For data transfer objects (currently empty, add DTOs here)
- **Configuration:** `resources/application.yml` (default port 8080, DB config)
- **Entry Point:** `TaskIncidentManagerApplication.java`

## Developer Workflows
- **Build:** `./mvnw clean package` (always use Maven Wrapper)
- **Run:** `./mvnw spring-boot:run` or run main class
- **Test:** `./mvnw test` (see `TaskIncidentManagerApplicationTests`)
- **Debug:** Standard Spring Boot debug via IDE or `mvnw` (no custom debug scripts)
- **Dependencies:** Managed in `pom.xml` (Spring Boot, Data JPA, Validation, Lombok, PostgreSQL)

## Project-Specific Conventions
- **Entities:** Annotated with `@Entity`, use plural table names (e.g., `users` for `User`)
- **Lombok:** Used for getters/setters in all entities; annotation processing enabled in Maven
- **Layering:**
  - Place business logic in `service/`
  - Place data access in `repository/`
  - Place REST endpoints in `controller/`
  - Use DTOs for API payloads (add to `dto/`)
- **Configuration:** All config in `application.yml`; DB expects PostgreSQL
- **Testing:** Use Spring Boot test starter; see `TaskIncidentManagerApplicationTests`

## Integration & Extensibility
- **Database:** PostgreSQL; configure connection in `application.yml`
- **Spring Boot Plugins:** See `pom.xml` for build/test plugins
- **Static/Template Resources:** Place in `resources/static/` or `resources/templates/` as needed

## Examples & Patterns
- **Entity Example:** See `entity/User.java` for JPA + Lombok pattern
- **Repository Example:** See `repository/UserRepository.java` for Spring Data JPA interface
- **Service Example:** See `service/UserService.java` for business logic structure
- **Test Example:** See `TaskIncidentManagerApplicationTests.java` for Spring Boot test setup

## Key Files & Directories
- `pom.xml` — dependencies, plugins, Java version
- `src/main/java/com/alexandre/taskmanager/entity/` — JPA entities
- `src/main/java/com/alexandre/taskmanager/repository/` — Data access
- `src/main/java/com/alexandre/taskmanager/service/` — Business logic
- `src/main/java/com/alexandre/taskmanager/controller/` — REST endpoints
- `src/main/resources/application.yml` — configuration
- `HELP.md` — links to Spring/Maven docs

---
For more details, see Spring Boot and Maven documentation linked in `HELP.md`.
