package nl.kristalsoftware.kristalcms.core.entity;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 28/10/15.
 */
public interface BaseEntity {

    String getId();
//    void setId(String id);
//    void setNode(Node node) throws RepositoryException;
//    Node getNode();
    void setEntityValues(Node node) throws RepositoryException;
    void setDatabaseValues(Node node) throws RepositoryException;

}
