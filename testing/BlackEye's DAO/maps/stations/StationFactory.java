package com.rikkabot.rikkabotcore.dao.maps.stations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Station factory.
 * ================
 *
 * Factory for the `maps_stations` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class StationFactory extends Factory<Station>
{
    /**
     * Station builder.
     */
    @Getter(lazy = true)
    private StationBuilder _builder = new StationBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "maps_stations";
}
