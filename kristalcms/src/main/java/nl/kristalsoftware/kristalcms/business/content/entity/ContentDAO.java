package nl.kristalsoftware.kristalcms.business.content.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 04/01/16.
 */
public class ContentDAO extends BaseDAO<Content> {

    @Inject
    Content content;

    public ContentDAO() {}

    @Override
    public Content getEntity() throws RepositoryException {
        return content;
    }

}
