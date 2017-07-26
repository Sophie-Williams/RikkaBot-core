package com.rikkabot.rikkabotcore.api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * IEndpoint interface.
 * ===================
 *
 * Interface for all endpoint classes.
 *
 * Each endpoint will implement the `execute` method which accepts
 * a JSON array with its parameter and will return a JSON object
 * with the result of the method.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IEndpoint {
    /**
     * Executes the endpoint.
     *
     * @param params Endpoint params.
     *
     * @return JSON response.
     */
    JSONObject execute(JSONArray params);
}
