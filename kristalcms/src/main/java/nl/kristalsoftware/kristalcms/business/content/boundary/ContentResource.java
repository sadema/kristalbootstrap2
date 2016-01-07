package nl.kristalsoftware.kristalcms.business.content.boundary;

import nl.kristalsoftware.kristalcms.business.content.entity.Content;
import nl.kristalsoftware.kristalcms.core.boundary.BaseResource;
import nl.kristalsoftware.kristalcms.core.boundary.Controller;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

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
 * Created by sjoerdadema on 04/01/16.
 */
@Stateless
@Path("/cms")
@Produces("application/json,application/xml")
@Consumes("application/json")
public class ContentResource extends BaseResource<Content> {

    @Inject
    Logger logger;

    @Inject
    Session session;

    @Inject
    Processor<Content> contentProcessor;

    @GET
    @Path("{customerId}/content/{pageId}/paragraphs/{paragraphId}")
    public Content getContent(@PathParam("customerId") String customerId,
                              @PathParam("pageId") String pageId,
                              @PathParam("paragraphId") String paragraphId,
                              @Context UriInfo uriInfo) {
        Content content = super.getEntity(uriInfo.getPath(), session);
        return content;
    }

    @POST
    @Path("{customerId}/content/{pageId}/paragraphs")
    public Response createContent(@PathParam("customerId") String customerId,
                                  @PathParam("pageId") String pageId,
                                  JsonObject jsonObject,
                                  @Context UriInfo uriInfo) {
        return super.createEntity(uriInfo, jsonObject, session);
    }

    @DELETE
    @Path("{customerId}/content/{pageId}/paragraphs/{paragraphId}")
    public Response removeContent(@PathParam("customerId") String customerId,
                                  @PathParam("pageId") String pageId,
                                  @PathParam("paragraphId") String paragraphId,
                                  @Context UriInfo uriInfo) {
        return super.removeEntity(uriInfo.getPath(), session);
    }

    @Override
    protected Processor<Content> getProcessor() {
        return contentProcessor;
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    protected void entityCreated() throws CMSDataException {
        super.persist(session);
    }

    @Override
    protected void entityRemoved() throws CMSDataException {
        super.persist(session);
    }
}
