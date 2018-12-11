package okon.CPD_CACHE;

import javax.sql.DataSource;

public class ConnectionFactory {

    public SybConnection build(DataSource dataSource) {
        return new SybConnection(dataSource);
    }
}