package nl.kristalsoftware.kristalcms.core.boundary;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 07/12/15.
 */
public interface Processor<E> {

    E getEntity(String path, Session session) throws PathNotFoundException, CMSDataException;
    String createNewEntity(String path, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException;
    void deleteEntity(String path, Session session) throws PathNotFoundException, CMSDataException;
}
