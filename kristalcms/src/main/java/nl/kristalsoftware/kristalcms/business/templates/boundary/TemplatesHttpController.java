package nl.kristalsoftware.kristalcms.business.templates.boundary;

import nl.kristalsoftware.kristalcms.business.templates.entity.Templates;
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
 * Created by sjoerdadema on 12/01/16.
 */
public class TemplatesHttpController implements Controller<Templates> {

    @Inject
    Processor<Templates> templatesProcessor;

    @Override
    public Templates get(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        return templatesProcessor.getEntity(uriInfo.getPath(), session);
    }

    @Override
    public String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        return templatesProcessor.createNewEntity(uriInfo.getPath(), jsonObject, session);
    }

    @Override
    public void delete(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        templatesProcessor.deleteEntity(uriInfo.getPath(), session);
    }

}
