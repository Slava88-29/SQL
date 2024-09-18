package sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.TimeUnit;

class DatabaseConfig {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/app");
            config.setUsername("app");
            config.setPassword("password1");
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}

public class SqlQuery {
    public static String getValidCode() {
        QueryRunner queryRunner = new QueryRunner(DatabaseConfig.getDataSource());
        String sql = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";

        try {
            TimeUnit.MILLISECONDS.sleep(500);

            String code = queryRunner.query(sql, new ScalarHandler<>());
            System.out.println("returnCode = " + code);
            return code;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving valid code", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Operation was interrupted", e);
        }
    }

    public static void clearDB() {
        QueryRunner queryRunner = new QueryRunner(DatabaseConfig.getDataSource());
        String[] queries = {
                "TRUNCATE auth_codes",
                "TRUNCATE cards",
                "TRUNCATE card_transactions",
                "DELETE FROM users"
        };

        try {
            for (String query : queries) {
                queryRunner.update(query);
            }
            System.out.println("Database cleared successfully.");
        } catch (SQLException e) {
            System.err.println("Error clearing database: " + e.getMessage());
        }
    }
}