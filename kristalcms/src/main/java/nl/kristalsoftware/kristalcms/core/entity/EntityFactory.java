package nl.kristalsoftware.kristalcms.core.entity;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sjoerdadema on 08/01/16.
 */
public interface EntityFactory {

    String createEntity(String parentPath, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException;

}
