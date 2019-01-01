package okon.CPD_CACHE;

import org.w3c.dom.Element;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CpdCacheApp {
    private final ConnectionFactory connectionFactory;
    private final DataSourceBuilder dataSourceBuilder;

    public CpdCacheApp() {
        this(new ConnectionFactory(), new DataSourceBuilder());
    }

    public CpdCacheApp(ConnectionFactory connectionFactory, DataSourceBuilder dataSourceBuilder) {
        this.connectionFactory = connectionFactory;
        this.dataSourceBuilder = dataSourceBuilder;
    }

    public static void main (String args[]) {
        CpdCacheApp cpd_cache_app = new CpdCacheApp();

        List<DataSource> dataSources = cpd_cache_app.loadConfiguration("./settings/config.xml");

        List<Message> messages = cpd_cache_app.clearAllCaches(dataSources);

        cpd_cache_app.saveReport("CPD_CACHE.txt", messages);
    }

    public List<DataSource> loadConfiguration(String pathname) {
        ConfigurationParser parser = new ConfigurationParser();
        Element root = parser.parseXml(new File(pathname));

        return dataSourceBuilder.build(root);
    }

    public List<Message> clearAllCaches(List<DataSource> dataSources) {
        List<Message> messages = new ArrayList<>();

        for (DataSource dataSource : dataSources) {
            Message message = clearDatabaseCache(dataSource);
            messages.add(message);
        }

        return messages;
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

    public void saveReport(String fileName, List<Message> messages) {
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