package nl.kristalsoftware.kristalcms.core.boundary;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import java.util.List;

/**
 * Created by sjoerdadema on 13/01/16.
 */
public interface ListBuilder<E> {

    List<E> build(String parentPath, Session session) throws CMSDataException;
}
