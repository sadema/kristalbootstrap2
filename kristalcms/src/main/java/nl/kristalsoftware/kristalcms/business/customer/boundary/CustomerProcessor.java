package nl.kristalsoftware.kristalcms.business.customer.boundary;

import nl.kristalsoftware.kristalcms.business.customer.entity.Customer;
import nl.kristalsoftware.kristalcms.core.boundary.BaseProcessor;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.entity.BaseDAO;
import nl.kristalsoftware.kristalcms.core.session.TXEnforcer;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.JsonObject;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 28/10/15.
 */
@Stateless
@Interceptors(TXEnforcer.class)
public class CustomerProcessor extends BaseProcessor implements Processor<Customer> {

    @Inject
    private Logger logger;

    @Inject
    BaseDAO<Customer> baseDAO;

    @Override
    public Customer get(String path, Session session) throws PathNotFoundException, CMSDataException {
        Customer entity = baseDAO.getEntity(session, path);
        return entity;
    }

    @Override
    public String post(String path, JsonObject jsonObject, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        Customer customer = null;
        try {
            customer = baseDAO.getEntity();
            setEntityValues(customer, jsonObject);
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        String newLocation = baseDAO.createEntity(session, path, customer);
        return newLocation;
    }

    @Override
    public void delete(String path, Session session) throws PathNotFoundException, CMSDataException {
        baseDAO.removeEntity(session, path);
    }

    public void setEntityValues(Customer customer, JsonObject jsonObject) {
        JsonObject jsonCustomer = jsonObject.getJsonObject("customer");
        String id = super.getStringValue(jsonCustomer, "@id");
        String city = super.getStringValue(jsonCustomer, "city");
        String version = super.getNumberValueAsString(jsonCustomer, "version");
        logger.info("id: " + id);
        logger.info("city: " + city);
        logger.info("version: " + version);
        customer.setId(id);
        customer.setCity(city);
        customer.setVersion(version);
    }
}
