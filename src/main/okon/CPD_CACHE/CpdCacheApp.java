package okon.CPD_CACHE;

import com.sybase.jdbc4.jdbc.SybDataSource;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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

        Message message1 = cpd_cache_app.clearDatabaseCache(dataSource1);
        Message message2 = cpd_cache_app.clearDatabaseCache(dataSource2);

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        cpd_cache_app.saveToFile("CPD_CACHE.txt", messages);
    }

    public Message clearDatabaseCache(DataSource dataSource) {
        Message message = null;

        try (SybConnection connection = connectionFactory.build(dataSource)) {
            message = connection.execute();
        } catch (Exception e) {
            throw new AppException(e);
        }

        return message;
    }

    public DataSource createDataSource(String serverName, int portNumber, String user, String password) {
        SybDataSource dataSource = new SybDataSource();

        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName("master");
        dataSource.setAPPLICATIONNAME("CPD_CACHE");

        return dataSource;
    }

    public void saveToFile(String fileName, List<Message> messages) {
        try (FileOutputStream out = new FileOutputStream(new java.io.File(fileName))) {
            for(Message message : messages) {
                List<byte[]> formattedText = new TxtFormatter(message).format();

                for (byte[] item : formattedText) {
                    out.write(item);
                    out.write(System.getProperty("line.separator").getBytes());
                }
                out.write(System.getProperty("line.separator").getBytes());
            }
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}