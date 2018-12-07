package okon.CPD_CACHE;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;
import java.time.LocalDateTime;

public class JdbcConnection implements Closeable {
    private final Connection connection;

    public JdbcConnection(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    public Message execute() {
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        startTime = getTimeStamp();
        executeDbcc();
        endTime = getTimeStamp();

        return new Message(startTime, endTime);
    }

    public void executeDbcc() {
        String sql = "dbcc proc_cache(free_unused)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    public LocalDateTime getTimeStamp() {
        return LocalDateTime.now();
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
