package nl.kristalsoftware.kristalcms.business.template.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import nl.kristalsoftware.kristalcms.core.jcr.TextFile;
import nl.kristalsoftware.kristalcms.core.jcr.JcrContentNode;
import org.jboss.resteasy.links.RESTServiceDiscovery;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.*;

/**
 * Created by sjoerdadema on 20-09-15.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="template")
public class Template implements BaseEntity {

//    @ParentResource
//    @Inject
//    Customer customer;

    @XmlID
    @XmlAttribute(name="id")
    private String templateId;

    @XmlElement(name="content")
    private String templateContent;

    @XmlElementRef
    private RESTServiceDiscovery rest;

    @Inject
    @TextFile
    JcrContentNode<String> jcrContent;

    public Template() {}

    @Override
    public String getId() {
        return templateId;
    }

    public void setId(String id) {
        this.templateId = id;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    @Override
    public void setEntityValues(Node node) throws RepositoryException {
        setId(node.getName());
        setTemplateContent(getJcrContent(node));
    }

    @Override
    public void setDatabaseValues(Node node) throws RepositoryException {
        setJcrContent(node, getTemplateContent());
    }

    public String getJcrContent(Node node) throws RepositoryException {
        return jcrContent.getValue(node);
    }

    public void setJcrContent(Node node, String value) throws RepositoryException {
        if (value != null) {
            jcrContent.setValue(node, value);
        }
    }
}
