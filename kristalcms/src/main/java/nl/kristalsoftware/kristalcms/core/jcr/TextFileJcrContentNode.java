package nl.kristalsoftware.kristalcms.core.jcr;

import javax.inject.Inject;
import javax.jcr.*;
import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 16-09-15.
 */
@TextFile
public class TextFileJcrContentNode implements JcrContentNode<String> {

    @Inject
    Logger logger;

    @Override
    public String getValue(Node node) throws RepositoryException {
        String value = null;
        try {
            Node htmlNode = node.getNode("html");
            Node fileNode = htmlNode.getNode("file");
            Node contentNode = fileNode.getNode("jcr:content");
            Property dataProperty = contentNode.getProperty("jcr:data");
            value = dataProperty.getString();
        } catch (PathNotFoundException e) {
            logger.info("Contentnode does not exist");
        }
        return value;
    }

    @Override
    public void setValue(Node node, String content) throws RepositoryException {
        if (node != null) {
            Node htmlNode = node.addNode("html");   //TODO Sjoerd: variabel maken wanneer er meer types zijn
            Node fileNode = htmlNode.addNode("file", "nt:file");
            ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
            Binary binaryData = fileNode.getSession().getValueFactory().createBinary(is);
            Node contentNode = fileNode.addNode("jcr:content", "nt:resource");
            contentNode.setProperty("jcr:mimeType", "text/html");
            contentNode.setProperty("jcr:encoding", "UTF-8");
            contentNode.setProperty("jcr:data", binaryData);
        }
    }
}
