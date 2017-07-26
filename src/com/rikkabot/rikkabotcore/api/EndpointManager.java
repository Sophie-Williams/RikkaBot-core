package com.rikkabot.rikkabotcore.api;

import java.util.HashMap;
import java.util.Map;

import com.manulaiko.tabitha.Console;

/**
 * EndpointManager class.
 * ======================
 *
 * Manages available endpoints and executes them as requested.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class EndpointManager {
    /**
     * Available endpoints.
     */
    private Map<String, Class<IEndpoint>> endpoints = new HashMap<>();

    /**
     * Constructor.
     *
     * Sets all endpoints.
     */
    public EndpointManager() {
        // TODO Populate `this.endpoints`
    }

    /**
     * Finds and returns an endpoint.
     *
     * @param name IEndpoint name.
     *
     * @return IEndpoint instance or null if `name` does not exist or the endpoint can't be instanced.
     */
    public IEndpoint find(String name) {
        if(!this.endpoints.containsKey(name)) {
            return null;
        }

        Class<IEndpoint> c = this.endpoints.get(name);

        try {
            return c.newInstance();
        } catch (Exception e) {
            Console.debug("Couldn't instance API endpoint for `"+ name +"`!");
            Console.print(e);
        }

        return null;
    }
}
