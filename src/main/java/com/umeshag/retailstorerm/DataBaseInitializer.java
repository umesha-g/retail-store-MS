package com.umeshag.retailstorerm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataBaseInitializer {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "retail_store_database";
    private static  String ROOT_USER = "root";
    private static  String ROOT_PASS = "root";
    private static final String APP_USER = "user";
    private static final String APP_PASS = "pass";
    private static final String FILE_PATH = "/src/main/resources/credentials.txt";
    

    public static void initialize() {
        
        try (Connection rootConn = DriverManager.getConnection(DB_URL, ROOT_USER, ROOT_PASS)) {
            String[] credentials = CredentialReader(FILE_PATH);

            ROOT_USER = credentials[0];
            ROOT_PASS = credentials[1];

            if (!databaseExists(rootConn)) {
                createDatabaseAndUser(rootConn);
                System.out.println("Database and user created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Now connect as the application user
        try (Connection appConn = DriverManager.getConnection(DB_URL + DB_NAME, APP_USER, APP_PASS)) {
            if (!tablesExist(appConn)) {
                executeSqlScript(appConn);
                createAdminIfNotExists(appConn);
                System.out.println("Database initialized for the first time.");
            } else {
                System.out.println("Database already initialized. Skipping initialization.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean databaseExists(Connection conn) throws SQLException {
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        while (resultSet.next()) {
            if (DB_NAME.equals(resultSet.getString(1))) {
                return true;
            }
        }
        return false;
    }

    private static void createDatabaseAndUser(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Create database
            stmt.executeUpdate("CREATE DATABASE " + DB_NAME);
            
            // Create user and grant privileges
            stmt.executeUpdate("CREATE USER '" + APP_USER + "'@'localhost' IDENTIFIED BY '" + APP_PASS + "'");
            stmt.executeUpdate("GRANT ALL PRIVILEGES ON " + DB_NAME + ".* TO '" + APP_USER + "'@'localhost'");
            stmt.executeUpdate("FLUSH PRIVILEGES");
        }
    }

    private static boolean tablesExist(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "employees", null);
        return rs.next();
    }

    @SuppressWarnings("resource")
    private static void executeSqlScript(Connection conn) throws SQLException {
        String script = new BufferedReader(
            new InputStreamReader(DataBaseInitializer.class.getResourceAsStream("/src/main/resources/DataBaseInitializer.sql"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        
        try (Statement stmt = conn.createStatement()) {
            for (String sql : script.split(";")) {
                if (!sql.trim().isEmpty()) {
                    stmt.executeUpdate(sql);
                }
            }
        }
    }

    private static void createAdminIfNotExists(Connection conn) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM employees WHERE emp_job = 'Admin'";
        String insertSql = "INSERT INTO employees (emp_id,emp_name,emp_job,emp_passw) VALUES (?, ?, ?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             ResultSet rs = checkStmt.executeQuery()) {
            
            if (rs.next() && rs.getInt(1) == 0) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, "1");
                    insertStmt.setString(2, "Admin");
                    insertStmt.setString(3, "Admin");
                    insertStmt.setString(4, "admin");
                    insertStmt.executeUpdate();
                    System.out.println("Admin user created. Username: Admin, Password: admin");
                }
            }
        }
    }

    private static String[] CredentialReader(String filePath) {

        String username = null;
        String password = null;
        String[] result = new String[2];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("username:")) {
                    username = line.substring("username:".length()).trim();
                } else if (line.startsWith("password:")) {
                    password = line.substring("password:".length()).trim();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        result[0] = username;
        result[1] = password;

        return result;
    }

     /*  private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }*/
}