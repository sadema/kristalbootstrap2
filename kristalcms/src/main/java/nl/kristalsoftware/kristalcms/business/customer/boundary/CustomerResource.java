package nl.kristalsoftware.kristalcms.business.customer.boundary;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.core.boundary.BaseResource;
import nl.kristalsoftware.kristalcms.core.boundary.Controller;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;
import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 07-09-15.
 */
@Stateless
@Path("/cms")
@Produces("application/json,application/xml")
@Consumes("application/json")
public class CustomerResource extends BaseResource<Customer> {

    @Inject
    Logger logger;

    @Inject
    Session session;

    @Inject
    CustomerHttpController customerHttpController;

    @PostConstruct
    public void init() {
        logger.info("++++++++++++++instantiating resource");
    }

    @AddLinks
    @LinkResource
    @GET
    @Path("{customerId}")
    public Customer getCustomer(@PathParam("customerId") String customerId, @Context UriInfo uriInfo) {
        Customer customer = super.get(uriInfo, session);
        return customer;
    }

    @POST
    @Consumes("application/json")
    public Response createCustomer(JsonObject jsonObject, @Context UriInfo uriInfo) {
        return super.post(uriInfo, jsonObject, session);
    }

    @DELETE
    @Path("{customerId}")
    public Response removeCustomer(@PathParam("customerId") String customerId, @Context UriInfo uriInfo) {
        return super.delete(uriInfo, session);
    }

    protected Controller<Customer> getHttpController() {
        return customerHttpController;
    }

    @Override
    protected void entityCreated() throws CMSDataException {
        super.persist(session);
    }

    @Override
    protected void entityRemoved() throws CMSDataException {
        super.persist(session);
    }

    @PreDestroy
    public void destroy() {
        logger.info("-----------------destroy resource");
    }

}
