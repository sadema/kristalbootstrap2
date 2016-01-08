package nl.kristalsoftware.kristalcms.business.pages.boundary;

import nl.kristalsoftware.kristalcms.business.pages.entity.Pages;
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
 * Created by sjoerdadema on 07/01/16.
 */
public class PagesProcessor extends BaseProcessor implements Processor<Pages> {

    @Inject
    BaseDAO<Pages> baseDAO;

    @Override
    public Pages getEntity(String path, Session session) throws PathNotFoundException, CMSDataException {
        return null;
    }

    @Override
    public String createNewEntity(String path, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        Pages pages = null;
        try {
            pages = baseDAO.getEntity();
            setEntityValues(pages, jsonObject);
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        String newPath = baseDAO.createEntity(session, path, pages);
        return newPath;
    }

    @Override
    public void deleteEntity(String path, Session session) throws PathNotFoundException, CMSDataException {

    }

    public void setEntityValues(Pages pages, JsonObject jsonObject) {
        JsonObject jsonPages = jsonObject.getJsonObject("pages");
        String id = super.getStringValue(jsonPages, "@id");
        pages.setId(id);
    }

}
