package okon.CPD_CACHE;

import com.sybase.jdbc4.jdbc.SybDataSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.sql.DataSource;
import java.util.ArrayList;

public class DataSourceBuilder {
    public ArrayList<DataSource> build(Element root) {
        ArrayList<DataSource> dataSources = new ArrayList<>();
        NodeList children = root.getElementsByTagName("server");

        if (children != null && children.getLength() > 0) {
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) child;

                    String dbIp = element.getElementsByTagName("db_ip").item(0).getTextContent();
                    Integer dbPort = Integer.valueOf(element.getElementsByTagName("db_port").item(0).getTextContent());
                    String dbUser = element.getElementsByTagName("db_user").item(0).getTextContent();
                    String dbPassword = element.getElementsByTagName("db_pswd").item(0).getTextContent();

                    dataSources.add(createDataSource(dbIp, dbPort, dbUser, dbPassword));
                }
            }
        }

        return dataSources;
    }

    private DataSource createDataSource (String serverName,int portNumber, String user, String password){
        SybDataSource dataSource = new SybDataSource();

        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName("master");
        dataSource.setAPPLICATIONNAME("CPD_CACHE");

        return dataSource;
    }
}
