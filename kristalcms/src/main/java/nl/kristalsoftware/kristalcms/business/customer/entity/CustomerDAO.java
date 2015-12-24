package nl.kristalsoftware.kristalcms.business.customer.entity;

import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

/**
 * Created by sjoerdadema on 28/10/15.
 */
public class CustomerDAO extends BaseDAO<Customer> {

    @Inject
    Customer customer;

    public CustomerDAO() {}

    @Override
    public Customer getEntity() throws RepositoryException {
        return customer;
    }


}
