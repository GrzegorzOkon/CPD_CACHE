package okon.CPD_CACHE;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SybConnectionTest {
    @Mock
    private DataSource dataSourceMock;

    @Mock
    private Connection connectionMock;

    @Mock
    private Statement statementMock;

    @Mock
    private ResultSet resultSetMock;

    @Before
    public void setUp() throws Exception {
        assertNotNull(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(resultSetMock.first()).thenReturn(true);
        when(resultSetMock.getInt("Num_free")).thenReturn(100000);
        when(statementMock.executeQuery(any(String.class))).thenReturn(resultSetMock);
    }

    @Test
    public void shouldSayThatCacheIsBiggerThanZero() {
        SybConnection sybConnection;;
        int freeCache = 0;

        try {
            sybConnection = new SybConnection(dataSourceMock);

            freeCache = sybConnection.checkFreeCacheSize();
        } catch(AppException ex) {}

        assertTrue(freeCache > 0);
    }

    @Test
    public void shouldSayThatTimeIsNotNull() {
        SybConnection sybConnection;
        LocalDateTime time = null;

        try {
            sybConnection = new SybConnection(dataSourceMock);

            time = sybConnection.getTimeStamp();
        } catch(AppException ex) {}

        assertNotNull(time);
    }
}