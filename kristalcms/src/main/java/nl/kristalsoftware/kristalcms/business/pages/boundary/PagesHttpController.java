package nl.kristalsoftware.kristalcms.business.pages.boundary;

import nl.kristalsoftware.kristalcms.business.pages.entity.Pages;
import nl.kristalsoftware.kristalcms.core.boundary.Controller;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 11/01/16.
 */
public class PagesHttpController implements Controller<Pages> {

    @Inject
    Processor<Pages> pagesProcessor;

    @Override
    public Pages get(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        return pagesProcessor.getEntity(uriInfo.getPath(), session);
    }

    @Override
    public String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        return pagesProcessor.createNewEntity(uriInfo.getPath(), jsonObject, session);
    }

    @Override
    public void delete(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        // do nothing
    }
}
