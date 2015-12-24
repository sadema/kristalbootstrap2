package nl.kristalsoftware.kristalcms.core.jcr;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjoerdadema on 03/11/15.
 */
public class JcrNodeList implements JcrList {

    public List<Node> getList(Session session, String parentPath) throws RepositoryException {
        List<Node> nodeList = new ArrayList<Node>();
        Node parentNode = session.getNode(parentPath);
        NodeIterator iter = parentNode.getNodes();
        while (iter.hasNext()) {
            nodeList.add(iter.nextNode());
        }
        return nodeList;
    }
}
