package okon.CPD_CACHE;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigurationParser {
    private DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

    public Element parseXml(File file) {
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);

            return document.getDocumentElement();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}