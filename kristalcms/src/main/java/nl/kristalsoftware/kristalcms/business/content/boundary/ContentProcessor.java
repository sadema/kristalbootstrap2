package nl.kristalsoftware.kristalcms.business.content.boundary;

import nl.kristalsoftware.kristalcms.business.content.entity.Content;
import nl.kristalsoftware.kristalcms.core.boundary.BaseProcessor;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 04/01/16.
 */
public class ContentProcessor extends BaseProcessor implements Processor<Content> {

    @Inject
    Logger logger;

    @Inject
    BaseDAO<Content> baseDAO;

    @Override
    public Content getEntity(String path, Session session) throws PathNotFoundException, CMSDataException {
        return baseDAO.getEntity(session, path);
    }

    @Override
    public String createNewEntity(String path, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        Content content = null;
        try {
            content = baseDAO.getEntity();
            setEntityValues(content, jsonObject);
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        String newLocation = baseDAO.createEntity(session, path, content);
        return newLocation;
    }

    @Override
    public void deleteEntity(String path, Session session) throws PathNotFoundException, CMSDataException {
        baseDAO.removeEntity(session, path);
    }

    public void setEntityValues(Content customer, JsonObject jsonObject) {
        JsonObject jsonContent = jsonObject.getJsonObject("content");
        String id = super.getStringValue(jsonContent, "@id");
        String title = super.getStringValue(jsonContent, "title");
        String text = super.getStringValue(jsonContent, "text");
        customer.setContentId(id);
        customer.setTitle(title);
        customer.setText(text);
    }

}