package com.rikkabot.rikkabotcore.dao.maps;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;
import com.rikkabot.rikkabotcore.dao.maps.stations.StationFactory;
import com.rikkabot.rikkabotcore.dao.maps.portals.PortalFactory;

/**
 * Map factory.
 * ============
 *
 * Factory for the `maps` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class MapFactory extends Factory<Map>
{
    /**
     * Stations factory.
     */
    @Getter(lazy = true)
    private StationFactory _stations = new StationFactory();

    /**
     * Portals factory.
     */
    @Getter(lazy = true)
    private PortalFactory _portals = new PortalFactory();

    /**
     * Map builder.
     */
    @Getter(lazy = true)
    private MapBuilder _builder = new MapBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "maps";
}
