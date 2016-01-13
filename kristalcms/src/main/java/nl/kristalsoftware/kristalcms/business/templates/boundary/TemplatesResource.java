package nl.kristalsoftware.kristalcms.business.templates.boundary;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.business.template.boundary.TemplateListBuilder;
import nl.kristalsoftware.kristalcms.business.templates.entity.Templates;
import nl.kristalsoftware.kristalcms.core.boundary.BaseResource;
import nl.kristalsoftware.kristalcms.core.boundary.Controller;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;
import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import org.jboss.resteasy.links.LinkResources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jcr.Session;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 12/01/16.
 */
@Stateless
@Path("cms")
@Produces("application/json,application/xml")
@Consumes("application/json")
public class TemplatesResource extends BaseResource<Templates> {

    @Inject
    Session session;

    @Inject
    Controller<Templates> templatesHttpController;

    @Inject
    TemplateListBuilder templateListBuilder;

    @LinkResources({
            @LinkResource,
            @LinkResource(value = Customer.class, rel = "templates")
    })
    @AddLinks
    @GET
    @Path("{customerId}/templates")
    public Templates getTemplates(@PathParam("customerId") String customerId, @Context UriInfo uriInfo) {
        Templates templates = super.get(uriInfo, session);
        templates.getCustomer().setId(customerId);
        try {
            templateListBuilder.setCustomerId(customerId);
            templates.setTemplateList(templateListBuilder.build(uriInfo.getPath(), session));
        } catch (CMSDataException e) {
            throw new WebApplicationException((Response.Status.INTERNAL_SERVER_ERROR));
        }
        return templates;
    }

    @Override
    protected Controller<Templates> getHttpController() {
        return templatesHttpController;
    }

    @Override
    protected void entityCreated() throws CMSDataException {
        // intentionally empty (cannot be created with a POST)
    }

    @Override
    protected void entityRemoved() throws CMSDataException {
        // intentionally empty (cannot be removed)
    }
}
