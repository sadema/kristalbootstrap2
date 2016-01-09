package nl.kristalsoftware.kristalcms.business.customer.boundary;

import nl.kristalsoftware.kristalcms.core.boundary.Builder;
import nl.kristalsoftware.kristalcms.core.boundary.ChildrenBuilder;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sjoerdadema on 08/01/16.
 */
public class CustomerChildrenBuilder implements ChildrenBuilder {

    private List<Builder> childBuilderlist;

    @Inject
    Builder pagesBuilder;

    @PostConstruct
    public void init() {
        childBuilderlist = Arrays.asList(pagesBuilder);
    }

    public void build(String parentPath, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        for(Builder builder : childBuilderlist) {
            builder.build(parentPath, session);
        }
    }

}
