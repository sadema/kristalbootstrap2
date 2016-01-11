package nl.kristalsoftware.kristalcms.core.boundary;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 06/01/16.
 */
public interface Controller<E> {

    E get(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException;
    String post(UriInfo uriInfo, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException;
    void delete(UriInfo uriInfo, Session session) throws PathNotFoundException, CMSDataException;

}
