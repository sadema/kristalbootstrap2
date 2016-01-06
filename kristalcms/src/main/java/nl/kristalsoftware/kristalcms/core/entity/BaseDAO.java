package nl.kristalsoftware.kristalcms.core.entity;

import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.jcr.*;

/**
 * Created by sjoerdadema on 28/10/15.
 */
public abstract class BaseDAO<T extends BaseEntity> {

    protected BaseDAO() {}

    public T getEntity(Session session, String path) throws PathNotFoundException, CMSDataException{
        T entity = null;
        try {
            Node node = session.getNode(path);
            entity = getEntity();
            entity.setEntityValues(node);
        } catch (PathNotFoundException e) {
            throw e;
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        return entity;
    }

    public String createEntity(Session session, String path, T entity) throws PathNotFoundException, ItemExistsException, CMSDataException {
        String newPath = null;
        try {
            Node parentNode = session.getNode(path);
            if (parentNode.hasNode(entity.getId())) {
                throw new ItemExistsException("Node: " + entity.getId() + " already exists!");
            }
            Node newNode = parentNode.addNode(entity.getId());
            entity.setDatabaseValues(newNode);
            newPath = newNode.getPath();
        } catch (PathNotFoundException e) {
            throw e;
        } catch (ItemExistsException e) {
            throw e;
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
        return newPath;
    }

    public void removeEntity(Session session, String path) throws PathNotFoundException, CMSDataException {
        try {
            session.removeItem(path);
        } catch (PathNotFoundException e) {
            throw e;
        } catch (RepositoryException e) {
            throw new CMSDataException(e);
        }
    }

    public void persist(Session session) throws CMSDataException {
        try {
            session.save();
        } catch(Exception e) {
            throw new CMSDataException(e);
        }

    }

    public abstract T getEntity() throws RepositoryException;
}
