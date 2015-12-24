package nl.kristalsoftware.kristalcms.core.boundary;

import javax.json.JsonNumber;
import javax.json.JsonObject;

/**
 * Created by sjoerdadema on 11/12/15.
 */
public class BaseProcessor {

    protected BaseProcessor() {}

    public String getStringValue(JsonObject jsonObject, String field) {
        String value = "";
        if (jsonObject.containsKey(field)) {
            value = jsonObject.getString(field);
        }
        return value;
    }

    public Integer getIntegerValue(JsonObject jsonObject, String field) {
        Integer value = 0;
        if (jsonObject.containsKey(field)) {
            value = Integer.valueOf(jsonObject.getInt(field));
        }
        return value;
    }

    public String getNumberValueAsString(JsonObject jsonObject, String field) {
        String numberAsString = "0";
        if (jsonObject.containsKey(field)) {
            JsonNumber number = jsonObject.getJsonNumber("version");
            numberAsString = number.toString();
        }
        return numberAsString;
    }
}
