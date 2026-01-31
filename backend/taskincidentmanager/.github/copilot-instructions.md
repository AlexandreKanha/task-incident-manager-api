# Copilot Instructions for Task Incident Manager API

## Project Overview
- **Framework:** Spring Boot (Java 17)
- **Purpose:** RESTful API for task and incident management
- **Structure:**
  - `model/` — JPA entities (e.g., `User`)
  - `controller/`, `service/`, `repository/`, `dto/` — follow standard Spring layering, but may be empty or incomplete
  - `resources/application.yml` — Spring Boot config (default port 8080)

## Build & Run
- **Build:** `./mvnw clean package` (uses Maven Wrapper)
- **Run:** `./mvnw spring-boot:run` or run `TaskIncidentManagerApplication` main class
- **Test:** `./mvnw test`
- **Dependencies:**
  - Spring Boot (Web, Data JPA, Validation)
  - PostgreSQL (runtime)
  - Lombok (annotation processing)

## Patterns & Conventions
- **Entities:** Annotated with `@Entity`, use Lombok for getters/setters
- **Database:** Table names are plural (e.g., `users` for `User` entity)
- **No custom controllers/services yet:** Folders exist for future expansion
- **Configuration:** Minimal, see `application.yml`
- **Testing:** Uses Spring Boot test starter, see `TaskIncidentManagerApplicationTests`

## Integration & Extensibility
- **Database:** Expects PostgreSQL, configure connection in `application.yml` if needed
- **Spring Boot plugins:** See `pom.xml` for build/test plugins
- **Lombok:** Annotation processing enabled via Maven config

## Key Files
- `pom.xml` — dependencies, plugins, Java version
- `TaskIncidentManagerApplication.java` — main entry point
- `model/User.java` — example entity
- `HELP.md` — links to Spring/Maven docs

## AI Agent Guidance
- Follow Spring Boot idioms for new features (REST controllers, services, repositories)
- Place new business logic in `service/`, API endpoints in `controller/`, data access in `repository/`
- Use Lombok for boilerplate in models
- Keep configuration in `application.yml`
- Use Maven Wrapper for all build/test commands

---
For more details, see Spring Boot and Maven documentation linked in `HELP.md`.
