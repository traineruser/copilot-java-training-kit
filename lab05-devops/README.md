# Lab 05 — DevOps & CI/CD Integration

## Objective
Complete a partial GitHub Actions CI workflow, generate a multi-stage Dockerfile, and optionally add SonarQube quality gate — all using Copilot Chat.

## Time
25 minutes (Part A: 10 min | Part B: 15 min)

## Files Provided
| File | Purpose |
|------|---------|
| `.github/workflows/ci.yml` | Partial workflow — has TODO placeholders |
| `Dockerfile` | Empty — Copilot generates |
| `ClaimsService/` | Working Spring Boot app to containerize |
| `prompts.md` | Exact prompts for all parts |

## Part A: Complete the GitHub Actions CI Workflow (10 min)
1. Open `.github/workflows/ci.yml`
2. Read all the TODO comments — understand what each job needs
3. Open Copilot Chat
4. Paste Prompt A1 from `prompts.md` with the partial YAML
5. Review the output — check Maven commands and Java version
6. Paste completed YAML back into ci.yml

**Check your YAML has:**
- [ ] `actions/setup-java@v4` with Java 17 + Maven cache
- [ ] `mvn compile -B` build step
- [ ] `mvn test -B` test step
- [ ] Artifact upload for test results

## Part B: Generate Dockerfile (15 min)
1. Open the empty `Dockerfile`
2. Use Prompt B1 from `prompts.md` in Copilot Chat
3. Paste generated Dockerfile and review it
4. Use Prompt B2 to generate `docker-compose.yml`

**Check your Dockerfile has:**
- [ ] Two stages (build + runtime)
- [ ] Maven build stage with `maven:3.9-eclipse-temurin-17`
- [ ] Runtime stage with `eclipse-temurin:17-jre-alpine`
- [ ] Non-root user
- [ ] EXPOSE 8080
- [ ] Correct ENTRYPOINT

## Common Mistakes
- YAML indentation breaks easily — if it looks wrong: "Fix the YAML indentation in this file"
- Maven artifactId in the COPY command must match your project's pom.xml
- SonarQube step requires `SONAR_TOKEN` secret — note it but don't configure live
