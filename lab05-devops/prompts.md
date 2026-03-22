# Lab 05 — DevOps & CI/CD Prompts

## Part A: Complete the GitHub Actions Workflow

### Prompt A1 — Paste into Copilot Chat with the partial ci.yml
```
This is a partial GitHub Actions CI/CD workflow for a Spring Boot Maven project.
Complete ALL TODO items. Requirements:
- Java 17 with Temurin distribution and Maven cache
- Maven build: mvn compile -B
- Maven test: mvn test -B
- Upload test results from target/surefire-reports as artifact "test-results"
- Keep all existing job structure and step names

Partial workflow:
[PASTE contents of .github/workflows/ci.yml here]
```

### Prompt A2 — Docker Job Completion
```
Complete the docker-build job in this GitHub Actions workflow.
Add these missing steps:
1. Set up Java 17 (same as build job)
2. Maven package: mvn package -DskipTests -B
3. Set up Docker Buildx using docker/setup-buildx-action@v3
4. Log in to GitHub Container Registry (ghcr.io) using docker/login-action@v3
   - Username: ${{ github.actor }}
   - Password: ${{ secrets.GITHUB_TOKEN }}
5. Build and push Docker image using docker/build-push-action@v5
   - Tags: ghcr.io/${{ github.repository }}:latest and :${{ github.sha }}
```

### Prompt A3 — SonarQube Quality Gate
```
Add a complete SonarQube quality gate step to this GitHub Actions job.
Requirements:
- Run after the test step
- Use mvn verify sonar:sonar
- Configure: sonar.projectKey, sonar.host.url (use https://sonarcloud.io)
- Pass SONAR_TOKEN from GitHub secrets
- Set sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
- Add a comment noting that SONAR_TOKEN must be added as a repository secret
```

---

## Part B: Generate Dockerfile

### Prompt B1 — Multi-stage Dockerfile (Copilot Chat)
```
Generate a production-ready multi-stage Dockerfile for a Spring Boot Maven application:

Stage 1 — Build:
  FROM maven:3.9-eclipse-temurin-17 AS builder
  - Copy pom.xml separately first (enables Docker layer caching for dependencies)
  - Run mvn dependency:go-offline -B
  - Copy remaining source
  - Run mvn package -DskipTests -B

Stage 2 — Runtime:
  FROM eclipse-temurin:17-jre-alpine
  - Create a non-root user: addgroup -S spring && adduser -S spring -G spring
  - Set WORKDIR /app
  - Copy JAR from builder stage as app.jar
  - Switch to spring user
  - EXPOSE 8080
  - HEALTHCHECK: curl -f http://localhost:8080/actuator/health || exit 1
  - ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Prompt B2 — Docker Compose
```
Generate a docker-compose.yml for the ClaimsService application:
- Service: claims-service
  - Build from Dockerfile in current directory
  - Port mapping: 8080:8080
  - Environment variables for Spring datasource (PostgreSQL)
  - Depends on: postgres service
  - Health check

- Service: postgres
  - Image: postgres:15-alpine
  - Environment: POSTGRES_DB=claimsdb, POSTGRES_USER, POSTGRES_PASSWORD
  - Volume for data persistence
  - Port: 5432:5432

- Network: claims-network (bridge driver)
```

---

## Verification Prompts

### Check your completed ci.yml
```
Review this GitHub Actions workflow and identify:
1. Any missing or incorrect Maven commands
2. Any steps that might fail due to missing secrets or permissions
3. Any best practice violations (e.g., pinned action versions, unnecessary steps)
4. Whether the SonarQube step will correctly fail the build on quality gate failure

Workflow:
[PASTE completed ci.yml]
```

### Check your Dockerfile
```
Review this Dockerfile and identify:
1. Any security concerns (running as root, exposed secrets, etc.)
2. Any layer caching inefficiencies
3. Whether the multi-stage build correctly minimizes the final image size
4. Any missing best practices for Spring Boot containers

Dockerfile:
[PASTE completed Dockerfile]
```
