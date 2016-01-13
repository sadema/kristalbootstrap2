package nl.kristalsoftware.kristalcms.business.templates.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 12/01/16.
 */
public class TemplatesDAO extends BaseDAO<Templates> {

    @Inject
    Templates templates;

    @Override
    public Templates getEntity() throws RepositoryException {
        return templates;
    }
}
