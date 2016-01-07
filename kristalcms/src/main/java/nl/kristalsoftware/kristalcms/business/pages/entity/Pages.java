package nl.kristalsoftware.kristalcms.business.pages.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sjoerdadema on 07/01/16.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="pages")
public class Pages implements BaseEntity {

    @XmlAttribute(name="id")
    private String pagesId;

    public Pages() {}

    @Override
    public String getId() {
        return pagesId;
    }

    public void setId(String pagesId) {
        this.pagesId = pagesId;
    }

    @Override
    public void setEntityValues(Node node) throws RepositoryException {
        setId(node.getName());
    }

    @Override
    public void setDatabaseValues(Node node) throws RepositoryException {
        // intentionaly empty (no proerties)
    }
}
