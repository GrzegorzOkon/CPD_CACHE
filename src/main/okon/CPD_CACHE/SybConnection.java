package okon.CPD_CACHE;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;

public class SybConnection implements Closeable {
    private final Connection connection;

    public SybConnection(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    public Message execute() {
        int startingFreeCacheSize = 0;
        int endingFreeCacheSize = 0;
        StopWatch executionProcessWatch = new StopWatch();

        startingFreeCacheSize = checkFreeCacheSize();
        executionProcessWatch.start();
        freeUnusedCache();
        executionProcessWatch.stop();
        endingFreeCacheSize = checkFreeCacheSize();

        return new Message(startingFreeCacheSize, endingFreeCacheSize, DurationFormatUtils.formatDuration(executionProcessWatch.getTime(), "S"));
    }

    public int checkFreeCacheSize() {
        String sql = "sp_monitorconfig 'procedure cache_size'";
        int freeCacheSize = 0;

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                freeCacheSize = rs.getInt("Num_free");
            }
        } catch (SQLException e) {
            throw new AppException(e);
        }

        return freeCacheSize;
    }

    public void freeUnusedCache() {
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