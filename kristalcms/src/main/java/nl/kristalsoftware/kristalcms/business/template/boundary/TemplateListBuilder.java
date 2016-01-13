package nl.kristalsoftware.kristalcms.business.template.boundary;

import nl.kristalsoftware.kristalcms.business.template.entity.Template;
import nl.kristalsoftware.kristalcms.core.boundary.ListBuilder;
import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjoerdadema on 13/01/16.
 */
public class TemplateListBuilder implements ListBuilder<Template> {

    private String customerId;

    @Override
    public List<Template> build(String parentPath, Session session) throws CMSDataException {
        List<Template> templateList = new ArrayList<Template>();
        try {
            String templateId;
            Node node = session.getNode(parentPath);
            NodeIterator nodeIterator = node.getNodes();
            while(nodeIterator.hasNext()) {
                templateId = nodeIterator.nextNode().getName();
                Template template = new Template();
                template.getCustomer().setId(customerId);
                template.setId(templateId);
                templateList.add(template);
            }
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        return templateList;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
