package nl.kristalsoftware.kristalcms.core.boundary;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;

/**
 * Created by sjoerdadema on 08/01/16.
 */
public interface Builder {

    String build(String parentPath, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException;

}
