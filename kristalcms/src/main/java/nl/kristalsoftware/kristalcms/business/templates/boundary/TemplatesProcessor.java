package nl.kristalsoftware.kristalcms.business.templates.boundary;

import nl.kristalsoftware.kristalcms.business.templates.entity.Templates;
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

/**
 * Created by sjoerdadema on 12/01/16.
 */
public class TemplatesProcessor extends BaseProcessor implements Processor<Templates> {

    @Inject
    BaseDAO<Templates> baseDAO;

    @Override
    public Templates getEntity(String path, Session session) throws PathNotFoundException, CMSDataException {
        return baseDAO.getEntity(session, path);
    }

    @Override
    public String createNewEntity(String path, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        Templates templates = null;
        try {
            templates = baseDAO.getEntity();
            setEntityValues(templates, jsonObject);
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        String newPath = baseDAO.createEntity(session, path, templates);
        return newPath;
    }

    @Override
    public void deleteEntity(String path, Session session) throws PathNotFoundException, CMSDataException {

    }

    public void setEntityValues(Templates templates, JsonObject jsonObject) {
        JsonObject jsonTemplates = jsonObject.getJsonObject("templates");
        String id = super.getStringValue(jsonTemplates, "@id");
        templates.setId(id);
    }

}
