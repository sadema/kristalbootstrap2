package nl.kristalsoftware.kristalcms.core.boundary;

import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by sjoerdadema on 20-10-15.
 */
public abstract class BaseResource<E extends BaseEntity> {

    public E getEntity(String path, Session session) {
        E entity = null;
        try {
            Processor<E> processor = getProcessor();
            entity = processor.get(path, session);
        } catch (PathNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (CMSDataException e) {
            throw new WebApplicationException((Response.Status.INTERNAL_SERVER_ERROR));
        }
        return entity;
    }

    public Response createEntity(UriInfo uriInfo, JsonObject jsonObject, Session session) {
        Response response = null;
        try {
            Controller controller = getController();
            String newPath = controller.post(uriInfo, jsonObject, session);

//            Processor<E> processor = getProcessor();
//            String newPath = processor.post(uriInfo, jsonObject, session);
            entityCreated();
            if (newPath != null) {
                response = Response.created(URI.create(newPath)).build();
            }
        } catch (PathNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (ItemExistsException e) {
            throw new WebApplicationException(422);     // unprocessable entity
        } catch (CMSDataException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public Response removeEntity(String path, Session session) {
        Response response = null;
        try {
            Processor<E> processor = getProcessor();
            processor.delete(path, session);
            entityRemoved();
            response = Response.noContent().build();
        } catch (PathNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (CMSDataException e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    protected void persist(Session session) throws CMSDataException {
        try {
            session.save();
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }

    }
    abstract protected Processor<E> getProcessor();

    abstract protected Controller getController();

    abstract protected void entityCreated() throws CMSDataException;

    abstract protected void entityRemoved() throws CMSDataException;

}
