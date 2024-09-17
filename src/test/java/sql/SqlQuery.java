package sql;

import java.sql.*;

public class SqlQuery {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/app";
    public static final String DB_USER = "app";
    public static final String DB_PASSWORD = "password1";

    public String getValidCode() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String queryStr = "SELECT code FROM auth_codes order by created desc limit 1";

            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryStr);
            while (rs.next()) {
                String getCode = rs.getString(1);
                System.out.println("returnCode = " + getCode);
                return getCode;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }
        return null;
    }
}