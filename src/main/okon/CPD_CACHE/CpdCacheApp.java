package okon.CPD_CACHE;

import com.sybase.jdbc4.jdbc.SybDataSource;

import javax.sql.DataSource;

public class CpdCacheApp {
    private final ConnectionFactory connectionFactory;

    public CpdCacheApp() {
        this(new ConnectionFactory());
    }

    public CpdCacheApp(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static void main (String args[]) {
        CpdCacheApp cpd_cache_app = new CpdCacheApp();

        DataSource dataSource1 = cpd_cache_app.createDataSource("xx.xx.xx.xx", 1111, "xxx", "xxx");
        DataSource dataSource2 = cpd_cache_app.createDataSource("yy.yy.yy.yy", 2222, "yyy", "yyy");

        cpd_cache_app.clearDatabaseCache(dataSource1);
        cpd_cache_app.clearDatabaseCache(dataSource2);
    }

    public void clearDatabaseCache(DataSource dataSource) {
        try (JdbcConnection connection = connectionFactory.build(dataSource)) {
            connection.execute();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public DataSource createDataSource(String serverName, int portNumber, String user, String password) {
        SybDataSource dataSource = new SybDataSource();

        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        //dataSource.setDatabaseName("xxx");
        dataSource.setAPPLICATIONNAME("CPD_CACHE");

        return dataSource;
    }
}