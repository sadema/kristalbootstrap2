package nl.kristalsoftware.kristalcms.business.pages.entity;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import org.jboss.resteasy.links.ParentResource;
import org.jboss.resteasy.links.RESTServiceDiscovery;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.*;

/**
 * Created by sjoerdadema on 07/01/16.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="pages")
public class Pages implements BaseEntity {

    @ParentResource
    private Customer customer;

    @XmlAttribute(name="id")
    private String pagesId;

    @XmlElementRef
    private RESTServiceDiscovery rest;
    public Pages() {
        customer = new Customer();
    }

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
        // intentionaly empty (no properties)
    }
}
