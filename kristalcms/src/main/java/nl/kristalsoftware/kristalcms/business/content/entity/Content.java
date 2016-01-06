package nl.kristalsoftware.kristalcms.business.content.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseEntity;
import nl.kristalsoftware.kristalcms.core.jcr.JcrProperty;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.*;

/**
 * Created by sjoerdadema on 04/01/16.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="content")
public class Content implements BaseEntity {

    @XmlID
    @XmlAttribute(name="id")
    private String contentId;

    @XmlElement
    private String title;

    @XmlElement
    private String text;

    @Inject
    JcrProperty<String> jcrTitle;

    @Inject
    JcrProperty<String> jcrText;

    @Override
    public String getId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setEntityValues(Node node) throws RepositoryException {
        setContentId(node.getName());
        setTitle(getJcrTitle(node));
        setText(getJcrText(node));
    }

    @Override
    public void setDatabaseValues(Node node) throws RepositoryException {
        setJcrTitle(node, getTitle());
        setJcrText(node, getText());
    }

    public String getJcrTitle(Node node) throws RepositoryException {
        return jcrTitle.getPropertyValue(node, "title");
    }

    public void setJcrTitle(Node node, String value) throws RepositoryException {
        jcrTitle.setPropertyValue(node, "title", value);
    }

    public String getJcrText(Node node) throws RepositoryException {
        return jcrText.getPropertyValue(node, "text");
    }

    public void setJcrText(Node node, String value) throws RepositoryException {
        jcrText.setPropertyValue(node, "text", value);
    }

}
