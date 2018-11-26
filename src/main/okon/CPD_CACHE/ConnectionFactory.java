package okon.CPD_CACHE;

import java.util.Properties;

public class ConnectionFactory {
    public JdbcConnection build(String url, Properties properties) {
        return new JdbcConnection(url, properties);
    }
}