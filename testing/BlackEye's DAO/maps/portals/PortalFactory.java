package com.rikkabot.rikkabotcore.dao.maps.portals;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Portal factory.
 * ===============
 *
 * Factory for the `maps_portals` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class PortalFactory extends Factory<Portal>
{
    /**
     * Portal builder.
     */
    @Getter(lazy = true)
    private PortalBuilder _builder = new PortalBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "maps_portals";
}
