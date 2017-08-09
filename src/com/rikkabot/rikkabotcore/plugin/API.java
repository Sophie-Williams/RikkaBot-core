package com.rikkabot.rikkabotcore.plugin;

import com.rikkabot.rikkabotcore.api.EndpointManager;

/**
 * API interface.
 * ==============
 *
 * API to communicate the plugins with the core.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface API {
    /**
     * Returns the endpoint manager.
     *
     * @return Endpoint manager.
     */
    EndpointManager endpointManager();
}
