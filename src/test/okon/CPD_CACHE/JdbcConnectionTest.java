package okon.CPD_CACHE;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class JdbcConnectionTest {
    @Mock
    private DataSource dataSourceMock;

    @Mock
    private Connection connectionMock;

    @Before
    public void setUp() throws Exception {
        assertNotNull(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
    }

    @Test
    public void shouldSayThatTimeIsNotNull() {
        JdbcConnection jdbcConnection;
        LocalDateTime time = null;

        try {
            jdbcConnection = new JdbcConnection(dataSourceMock);

            time = jdbcConnection.getTimeStamp();
        } catch(AppException ex) {}

        assertNotNull(time);
    }
}