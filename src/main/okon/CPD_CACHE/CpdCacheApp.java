package okon.CPD_CACHE;

import okon.CPD_CACHE.config.ConfigurationParamsReader;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CpdCacheApp {
    static final List<DataSource> databases = ConfigurationParamsReader.readConfigurationParams(new File("./settings/config.xml"));
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public static void main (String args[]) {
        CpdCacheApp cpd_cache_app = new CpdCacheApp();
        List<Message> messages = cpd_cache_app.clearAllCaches(databases);
        cpd_cache_app.print(messages);
    }

    private List<Message> clearAllCaches(List<DataSource> databases) {
        List<Message> messages = new ArrayList<>();
        for (DataSource dataSource : databases) {
            Message message = clearDatabaseCache(dataSource);
            messages.add(message);
        }
        return messages;
    }

    private Message clearDatabaseCache(DataSource dataSource) {
        Message message = null;
        try (SybConnection connection = connectionFactory.build(dataSource)) {
            message = connection.execute();
        } catch (Exception e) {
            throw new AppException(e);
        }
        return message;
    }

    private void print(List<Message> content) {
        printToConsole(content);
        printToFile(content);
    }

    private void printToConsole(List<Message> content) {
        for(Message message : content) {
            List<String> formattedText = new MessageFormatter(message).format();
            for (String item : formattedText) {
                System.out.println(item);
            }
            System.out.println(System.getProperty("line.separator"));
        }
    }

    private void printToFile(List<Message> content) {
        try (Writer out = new FileWriter(new java.io.File(CpdCacheApp.getJarFileName() + ".txt"))) {
            for (Message message : content) {
                List<String> formattedText = new MessageFormatter(message).format();
                for (String item : formattedText) {
                    out.write(item);
                    out.write(System.getProperty("line.separator"));
                }
                out.write(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    static String getJarFileName() {
        String path = CpdCacheApp.class.getResource(CpdCacheApp.class.getSimpleName() + ".class").getFile();
        path = path.substring(0, path.lastIndexOf('!'));
        path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf('.'));
        return path;
    }
}