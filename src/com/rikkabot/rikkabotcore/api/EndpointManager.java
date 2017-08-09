package com.rikkabot.rikkabotcore.api;

import java.util.HashMap;
import java.util.Map;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.api.endpoint.HTTPLogin;

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
    private Map<String, Class<? extends IEndpoint>> endpoints = new HashMap<>();

    /**
     * Constructor.
     *
     * Sets all endpoints.
     */
    public EndpointManager() {
        this.endpoints.put("httpLogin", HTTPLogin.class);
    }

    /**
     * Finds and returns an endpoint.
     *
     * @param name IEndpoint name.
     *
     * @return IEndpoint instance or null if `name` does not exist or the endpoint can't be instanced.
     */
    public IEndpoint find(String name) {
        if (!this.endpoints.containsKey(name)) {
            return null;
        }

        Class<? extends IEndpoint> c = this.endpoints.get(name);

        try {
            return c.newInstance();
        } catch (Exception e) {
            Console.debug("Couldn't instance API endpoint for `"+ name +"`!");
            Console.print(e);
        }

        return null;
    }
}
