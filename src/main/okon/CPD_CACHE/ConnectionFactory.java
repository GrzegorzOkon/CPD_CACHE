package okon.CPD_CACHE;

import com.sybase.jdbc4.jdbc.SybDataSource;
import java.util.Properties;

public class ConnectionFactory {
    public JdbcConnection build(String url, Properties properties) {
        SybDataSource dataSource = new SybDataSource();
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setServerName("xx.xx.xx.xx");
        dataSource.setPortNumber(1234);

        return new JdbcConnection(dataSource);
    }
}