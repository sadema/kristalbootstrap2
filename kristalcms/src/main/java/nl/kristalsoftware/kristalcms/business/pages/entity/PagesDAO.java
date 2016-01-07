package nl.kristalsoftware.kristalcms.business.pages.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 07/01/16.
 */
public class PagesDAO extends BaseDAO<Pages> {

    @Inject
    Pages pages;

    @Override
    public Pages getEntity() throws RepositoryException {
        return pages;
    }

}
