package nl.kristalsoftware.kristalcms.business.templates.boundary;

import nl.kristalsoftware.kristalcms.business.templates.entity.Templates;
import nl.kristalsoftware.kristalcms.core.boundary.Builder;
import nl.kristalsoftware.kristalcms.core.boundary.Processor;
import nl.kristalsoftware.kristalcms.core.main.CMSDataException;

import javax.inject.Inject;
import javax.jcr.ItemExistsException;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by sjoerdadema on 12/01/16.
 */
public class TemplatesBuilder implements Builder {

    @Inject
    Processor<Templates> templatesProcessor;

    @Override
    public String build(String parentPath, Session session) throws PathNotFoundException, ItemExistsException, CMSDataException {
        return templatesProcessor.createNewEntity(parentPath, createJsonObject(), session);
    }

    private JsonObject createJsonObject() {
        return Json.createObjectBuilder().
                add("templates", Json.createObjectBuilder().add("@id", "templates").build()).build();
    }

}