package nl.kristalsoftware.kristalcms.core.jcr;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.List;

/**
 * Created by sjoerdadema on 03/11/15.
 */
public interface JcrList {

    public List<Node> getList(Session session, String parentPath) throws RepositoryException;

}
