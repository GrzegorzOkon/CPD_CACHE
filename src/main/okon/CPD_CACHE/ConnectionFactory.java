package okon.CPD_CACHE;

import javax.sql.DataSource;

public class ConnectionFactory {

    public JdbcConnection build(DataSource dataSource) {
        return new JdbcConnection(dataSource);
    }
}