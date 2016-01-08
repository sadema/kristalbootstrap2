package nl.kristalsoftware.kristalcms.business.pages.entity;

import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.entity.EntityFactory;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by sjoerdadema on 08/01/16.
 */
public class PagesFactory implements EntityFactory {

    @Inject
    Processor<Pages> pagesProcessor;

    @Override
    public String createEntity(String parentPath, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        return pagesProcessor.createNewEntity(parentPath, createJsonObject(), session);
    }

    private JsonObject createJsonObject() {
        return Json.createObjectBuilder().
                add("pages", Json.createObjectBuilder().add("@id", "pages").build()).build();
    }

}
