package nl.kristalsoftware.kristalcms.business.template.boundary;

import nl.kristalsoftware.kristalcms.business.template.entity.Template;
import nl.kristalsoftware.kristalcms.core.boundary.BaseProcessor;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 01/11/15.
 */
@Stateless
public class TemplateProcessor extends BaseProcessor implements Processor<Template> {

    @Inject
    Logger logger;

    @Inject
    BaseDAO<Template> baseDAO;

    @Override
    public Template get(String path, Session session) throws PathNotFoundException, CMSDataException {
        Template entity = baseDAO.getEntity(session, path);
        return entity;
    }

    @Override
    public String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        Template template = null;
        try {
            template = baseDAO.getEntity();
            setEntityValues(template, jsonObject);
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        String newLocation = baseDAO.createEntity(session, uriInfo.getPath(), template);
        return newLocation;
    }

    @Override
    public void delete(String path, Session session) throws PathNotFoundException, CMSDataException {
        baseDAO.removeEntity(session, path);
    }

    public void setEntityValues(Template template, JsonObject jsonObject) {
        JsonObject jsonTemplate = jsonObject.getJsonObject("template");
        template.setId(this.getStringValue(jsonTemplate, "@id"));
        String content = this.getStringValue(jsonTemplate, "content");
        logger.info("Content: " + content);
        template.setTemplateContent(this.getStringValue(jsonTemplate, "content"));
    }

}
