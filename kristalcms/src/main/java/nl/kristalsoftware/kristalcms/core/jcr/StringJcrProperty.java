package nl.kristalsoftware.kristalcms.core.jcr;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 16-09-15.
 */
public class StringJcrProperty implements JcrProperty<String> {

    @Inject
    private Logger logger;

    @Override
    public String getPropertyValue(Node node, String propertyName) throws RepositoryException{
        String value = null;
        try {
            value = node.getProperty(propertyName).getString();
        } catch(PathNotFoundException e) {
            logger.info("Property " + propertyName + " does not exist");
        } catch (ValueFormatException e) {
            logger.severe(e.getMessage());
            throw new RepositoryException(e);
        }
        return value;
    }

    @Override
    public void setPropertyValue(Node node, String propertyName, String pageContent) throws RepositoryException {
        node.setProperty(propertyName, pageContent);
    }
}
