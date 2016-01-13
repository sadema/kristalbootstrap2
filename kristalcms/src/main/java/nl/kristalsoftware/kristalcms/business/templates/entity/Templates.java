package nl.kristalsoftware.kristalcms.business.templates.entity;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.business.template.entity.Template;
import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import org.jboss.resteasy.links.ParentResource;
import org.jboss.resteasy.links.RESTServiceDiscovery;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjoerdadema on 12/01/16.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "templates")
public class Templates implements BaseEntity {

    @ParentResource
    private Customer customer;

    @XmlAttribute(name = "id")
    private String templatesId;

    @XmlElement
    private List<Template> templateList;

    @XmlElementRef
    private RESTServiceDiscovery rest;

    public Templates() {
        customer = new Customer();
    }

    @Override
    public String getId() {
        return templatesId;
    }

    public void setId(String templatesId) {
        this.templatesId = templatesId;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public Customer getCustomer() {
        return customer;
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
