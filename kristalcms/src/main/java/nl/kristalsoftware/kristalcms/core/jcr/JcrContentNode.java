package nl.kristalsoftware.kristalcms.core.jcr;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 16-09-15.
 */
public interface JcrContentNode<T> {
    T getValue(Node node) throws RepositoryException;
    void setValue(Node node, T pageContent) throws RepositoryException;
}
