package nl.kristalsoftware.kristalcms.business.customer.boundary;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.business.pages.entity.PagesFactory;
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
 * Created by sjoerdadema on 06/01/16.
 */
public class CustomerController implements Controller {

    @Inject
    Processor<Customer> customerProcessor;

    @Inject
    PagesFactory entityFactory;

    @Override
    public String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        String newPath = customerProcessor.createNewEntity(uriInfo.getPath(), jsonObject, session);
        entityFactory.createEntity(newPath, session);
        return newPath;
    }

}
