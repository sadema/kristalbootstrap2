package nl.kristalsoftware.kristalcms.business.template.boundary;

import nl.kristalsoftware.kristalcms.business.template.entity.Template;
import nl.kristalsoftware.kristalcms.business.templates.entity.Templates;
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
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 29-09-15.
 */
@Path("/cms")
@Produces("application/json,application/xml")
@Consumes("application/json")
@Stateless
public class TemplateResource extends BaseResource<Template> {

    @Inject
    private Logger logger;

    @Inject
    Session session;

    @Inject
    private Controller<Template> templateHttpController;

    @PostConstruct
    public void init() {
        logger.info("++++++++++++++instantiating resource");
    }

//    @AddLinks
//    @LinkResources({
//            @LinkResource,
//            @LinkResource(value = TemplatesRepresentation.class, rel = "template")
//    })
    @AddLinks
    @LinkResource
//    @LinkResource(value = Templates.class, rel = "template")
    @GET
    @Path("{customerId}/templates/{templateId}")
    public Template getTemplate(@PathParam("customerId") String customerId, @PathParam("templateId") String templateId, @Context UriInfo uriInfo) {
        Template template = super.get(uriInfo, session);
        template.getCustomer().setId(customerId);
        return template;
    }

    @GET
    @Produces("text/html")
    @Path("{customerId}/templates/{templateId}")
    public String getTemplateContent(@PathParam("customerId") String customerId, @PathParam("templateId") String templateId, @Context UriInfo uriInfo) {
        Template template = super.get(uriInfo, session);
//        templateEntity.setCustomerId(customerId);
        return new String(Base64.getDecoder().decode(template.getTemplateContent()));
    }

//    @LinkResource(value = TemplatesRepresentation.class)
    @POST
    @Path("{customerId}/templates")
    public Response createTemplate(@PathParam("customerId") String customerId, JsonObject jsonObject, @Context UriInfo uriInfo) {
        return super.post(uriInfo, jsonObject, session);
    }

    @DELETE
    @Path("{customerId}/templates/{templateId}")
    public Response removeTemplate(@PathParam("customerId") String customerId, @PathParam("templateId") String templateId, @Context UriInfo uriInfo) {
        return super.delete(uriInfo, session);
    }

    @Override
    protected Controller<Template> getHttpController() {
        return templateHttpController;
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
