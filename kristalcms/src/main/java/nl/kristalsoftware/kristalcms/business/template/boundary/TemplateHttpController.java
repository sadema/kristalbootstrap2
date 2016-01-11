package nl.kristalsoftware.kristalcms.business.template.boundary;

import nl.kristalsoftware.kristalcms.business.template.entity.Template;
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
public class TemplateHttpController implements Controller<Template> {

    @Inject
    Processor<Template> templateProcessor;

    @Override
    public Template get(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        return templateProcessor.getEntity(uriInfo.getPath(), session);
    }

    @Override
    public String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        return templateProcessor.createNewEntity(uriInfo.getPath(), jsonObject, session);
    }

    @Override
    public void delete(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException {
        templateProcessor.deleteEntity(uriInfo.getPath(), session);
    }

}
