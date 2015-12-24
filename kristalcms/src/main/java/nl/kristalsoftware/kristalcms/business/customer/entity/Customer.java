package nl.kristalsoftware.kristalcms.business.customer.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import nl.kristalsoftware.kristalcms.core.jcr.JcrProperty;
import org.jboss.resteasy.links.RESTServiceDiscovery;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.*;

/**
 * Created by sjoerdadema on 19-09-15.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="customer")
public class Customer implements BaseEntity {

    @XmlID
    @XmlAttribute(name="id")
    private String customerId = "test customerid";

    @XmlElement
    private String version = "test version";

    @XmlElement
    private String city = "test city";

    @XmlElementRef
    private RESTServiceDiscovery rest;

    @Inject
    private JcrProperty<String> jcrVersion;

    @Inject
    private JcrProperty<String> jcrCity;

    public Customer() {}

    @Override
    public String getId() {
        return customerId;
    }

    public void setId(String customerId) {
        this.customerId = customerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public void setEntityValues(Node node) throws RepositoryException {
        setId(node.getName());
        setVersion(getJcrVersion(node));
        setCity(getJcrCity(node));
    }

    @Override
    public void setDatabaseValues(Node node) throws RepositoryException {
        System.out.println("Node: " + node.getName() + " and pending session is " + node.getSession().hasPendingChanges());
        setJcrVersion(node, getVersion());
        setJcrCity(node, getCity());
    }

    public String getJcrVersion(Node node) throws RepositoryException {
        return jcrCity.getPropertyValue(node, "version");
    }

    public void setJcrVersion(Node node, String value) throws RepositoryException {
        jcrCity.setPropertyValue(node, "version", value);
    }

    public String getJcrCity(Node node) throws RepositoryException {
        return jcrCity.getPropertyValue(node, "city");
    }

    public void setJcrCity(Node node, String value) throws RepositoryException {
        jcrCity.setPropertyValue(node, "city", value);
    }

}
