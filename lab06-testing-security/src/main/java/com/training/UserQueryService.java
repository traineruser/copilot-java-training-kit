package com.training;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * LAB 06 PART B: UserQueryService — INTENTIONAL VULNERABILITIES
 * ============================================================
 *
 * THIS CLASS IS DELIBERATELY INSECURE FOR TRAINING PURPOSES.
 * It contains multiple OWASP Top 10 vulnerabilities.
 *
 * YOUR TASK:
 * 1. Open Copilot Chat
 * 2. Paste this class into Chat
 * 3. Prompt: "Find all OWASP Top 10 vulnerabilities in this class.
 *    For each, state: line number, vulnerability type (e.g. A03:Injection),
 *    severity (Critical/High/Medium), and recommended fix."
 * 4. Review Copilot's findings
 * 5. Prompt: "Generate a fixed version of UserQueryService that remediates
 *    all identified vulnerabilities."
 *
 * Vulnerabilities included (DO NOT READ until after the exercise):
 * [SPOILER BELOW — scroll past for the exercise]
 * .
 * .
 * .
 * .
 * .
 * .
 * A03 - SQL Injection (findUserByName, getUserActivity)
 * A02 - Hardcoded Credentials (DB_PASSWORD constant)
 * A01 - Broken Access Control (getAdminData — no auth check)
 * A08 - Insecure Deserialization (deserializeUserProfile)
 * A05 - Security Misconfiguration (exportUserData — path traversal)
 * A09 - Logging Sensitive Data (logUserAction)
 * ============================================================
 */
@Service
public class UserQueryService {

    private final JdbcTemplate jdbcTemplate;

    // VULNERABILITY: A02 — Hardcoded credential (should be in environment variable)
    private static final String DB_PASSWORD = "admin123";
    private static final String ADMIN_SECRET = "secret_admin_key_2024";

    public UserQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * VULNERABILITY: A03:Injection — SQL Injection via string concatenation.
     * User input is directly concatenated into the SQL query.
     * Fix: Use parameterized queries / PreparedStatement.
     */
    public Map<String, Object> findUserByName(String username) {
        // VULNERABLE: direct string concatenation
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        return jdbcTemplate.queryForMap(query);
    }

    /**
     * VULNERABILITY: A03:Injection — Second SQL injection point.
     * The userId parameter is not sanitized before being embedded in SQL.
     */
    public List<Map<String, Object>> getUserActivity(String userId, String activityType) {
        // VULNERABLE: both parameters concatenated
        String query = "SELECT * FROM activity_log WHERE user_id = " + userId +
                       " AND activity_type = '" + activityType + "'";
        return jdbcTemplate.queryForList(query);
    }

    /**
     * VULNERABILITY: A01:Broken Access Control — No authentication or authorization check.
     * Any caller can access admin data without verifying they have admin role.
     */
    public List<Map<String, Object>> getAdminData() {
        // VULNERABLE: no @PreAuthorize or role check
        return jdbcTemplate.queryForList("SELECT * FROM admin_audit_log");
    }

    /**
     * VULNERABILITY: A08:Insecure Deserialization — Deserializing untrusted data.
     * Deserializing arbitrary bytes can lead to remote code execution.
     * Fix: Use safe formats like JSON with a known schema, or validate before deserializing.
     */
    public Object deserializeUserProfile(String base64Data) {
        try {
            // VULNERABLE: deserializing untrusted input
            byte[] data = Base64.getDecoder().decode(base64Data);
            ObjectInputStream ois = new ObjectInputStream(
                new java.io.ByteArrayInputStream(data)
            );
            return ois.readObject(); // DANGEROUS: arbitrary class instantiation
        } catch (Exception e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }

    /**
     * VULNERABILITY: A05:Security Misconfiguration / Path Traversal.
     * User-supplied path is used directly to open a file.
     * Attacker can read /etc/passwd, application.properties, etc.
     */
    public byte[] exportUserData(String userId, String exportPath) {
        try {
            // VULNERABLE: path traversal — userId and exportPath not sanitized
            File file = new File("/data/exports/" + exportPath + "/" + userId + ".json");
            FileInputStream fis = new FileInputStream(file);
            return fis.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Export failed", e);
        }
    }

    /**
     * VULNERABILITY: A09:Security Logging and Monitoring Failures.
     * Logging sensitive data (passwords, tokens) to application logs.
     */
    public void logUserAction(String userId, String action, String userPassword) {
        // VULNERABLE: logging sensitive data including password
        System.out.println("USER ACTION: userId=" + userId +
                           " action=" + action +
                           " password=" + userPassword +      // NEVER log passwords
                           " adminSecret=" + ADMIN_SECRET);   // NEVER log secrets
    }
}
