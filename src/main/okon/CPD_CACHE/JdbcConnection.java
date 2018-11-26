package okon.CPD_CACHE;

import java.io.Closeable;
import java.sql.*;
import java.util.Properties;

public class JdbcConnection implements Closeable {
    private final Connection connection;

    JdbcConnection(String url, Properties properties) {
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    public void execute() {
        String sql = "dbcc proc_cache(free_unused)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }
}
