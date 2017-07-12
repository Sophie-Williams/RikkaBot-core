package com.rikkabot.rikkabotcore.dao.galaxygates;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;
import com.rikkabot.rikkabotcore.dao.galaxygates.waves.WaveFactory;

/**
 * GalaxyGate factory.
 * ===================
 *
 * Factory for the `galaxygates` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class GalaxyGateFactory extends Factory<GalaxyGate>
{
    /**
     * Waves factory.
     */
    @Getter(lazy = true)
    private WaveFactory _waves = new WaveFactory();

    /**
     * GalaxyGate builder.
     */
    @Getter(lazy = true)
    private GalaxyGateBuilder _builder = new GalaxyGateBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "galaxygates";
}
