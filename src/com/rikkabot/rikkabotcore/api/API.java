package com.rikkabot.rikkabotcore.api;

import lombok.Data;

/**
 * API class.
 * ==========
 *
 * API to communicate the plugins with the core.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class API implements com.rikkabot.rikkabotcore.plugin.API {
    /**
     * Endpoint manager instance.
     */
    private EndpointManager endpointManager = new EndpointManager();
}
