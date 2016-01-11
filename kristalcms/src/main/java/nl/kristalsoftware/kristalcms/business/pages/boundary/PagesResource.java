package nl.kristalsoftware.kristalcms.business.pages.boundary;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.business.pages.entity.Pages;
import nl.kristalsoftware.kristalcms.core.boundary.BaseResource;
import nl.kristalsoftware.kristalcms.core.boundary.Controller;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;
import org.jboss.resteasy.links.LinkResource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jcr.Session;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 11/01/16.
 */
@Stateless
@Path("cms")
@Produces("application/json,application/xml")
@Consumes("application/json")
public class PagesResource extends BaseResource<Pages> {

    @Inject
    Session session;

    @Inject
    Controller<Pages> pagesHttpController;

    @GET
    @LinkResource(value = Customer.class, rel = "pages")
    @Path("{customerId}/pages")
    public Pages getPages(@PathParam("customerId") String customerId, @Context UriInfo uriInfo) {
        return super.get(uriInfo, session);
    }

    @Override
    protected Controller<Pages> getHttpController() {
        return pagesHttpController;
    }

    @Override
    protected void entityCreated() throws CMSDataException {
        super.persist(session);
    }

    @Override
    protected void entityRemoved() throws CMSDataException {
        // intentionally empty (cannot be removed)
    }
}
