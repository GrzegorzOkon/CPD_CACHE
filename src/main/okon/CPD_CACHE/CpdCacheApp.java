package okon.CPD_CACHE;

import java.util.Properties;

public class CpdCacheApp {
    private static final String DB_URL_TEMPLATE = "jdbc:sybase:Tds:%s:%s/master";

    private final ConnectionFactory connectionFactory;

    public CpdCacheApp() {
        this(new ConnectionFactory());
    }

    public CpdCacheApp(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static void main (String args[]) {
        CpdCacheApp cpd_cache_app = new CpdCacheApp();

        cpd_cache_app.clearDatabaseCache("xx.xx.xx.xx", "xxxx", "xxx", "xxx");
        cpd_cache_app.clearDatabaseCache("yy.yy.yy.yy", "yyy", "yyy", "yyy");
        //cpd_cache_app.clearDatabaseCache("192.168.43.216", "5000", "sa", "");
    }

    public void clearDatabaseCache(String ip, String port, String login, String password) {
        String requestUrl = String.format(DB_URL_TEMPLATE, ip, port);

        Properties properties = new Properties();
        properties.put("user", login );
        properties.put("password", password);

        try (JdbcConnection connection = connectionFactory.build(requestUrl, properties)) {
            connection.execute();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
