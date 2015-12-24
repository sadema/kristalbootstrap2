package nl.kristalsoftware.kristalcms.business.template.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;

import javax.inject.Inject;

/**
 * Created by sjoerdadema on 28/10/15.
 */
public class TemplateDAO extends BaseDAO<Template> {

    @Inject
    Template template;

    public TemplateDAO() {}

    @Override
    public Template getEntity() {
        return template;
    }

}
