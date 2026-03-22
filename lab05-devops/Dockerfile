# ============================================================
# LAB 05 PART B: Dockerfile
# ============================================================
#
# This file is EMPTY — use Copilot to generate it.
#
# COPILOT CHAT PROMPT (paste this into Chat):
# ─────────────────────────────────────────────
# Generate a multi-stage Dockerfile for a Spring Boot Maven application:
#
# Stage 1 — Build stage:
#   - Base image: maven:3.9-eclipse-temurin-17
#   - Working directory: /app
#   - Copy pom.xml first (for dependency caching)
#   - Run: mvn dependency:go-offline -B
#   - Copy source code
#   - Run: mvn package -DskipTests -B
#
# Stage 2 — Runtime stage:
#   - Base image: eclipse-temurin:17-jre-alpine (minimal JRE)
#   - Working directory: /app
#   - Copy the JAR from the build stage (target/*.jar → app.jar)
#   - Create a non-root user for security
#   - Expose port 8080
#   - Set ENTRYPOINT: java -jar app.jar
#   - Add HEALTHCHECK for /actuator/health endpoint
# ─────────────────────────────────────────────
#
# ECLIPSE COMMENT PROMPT (alternative — type above this file):
#   # Multi-stage Dockerfile: Stage 1 builds with Maven 3.9 + Java 17,
#   # Stage 2 runs with eclipse-temurin:17-jre-alpine. App port 8080.

# TODO: Copilot generates the full Dockerfile here
