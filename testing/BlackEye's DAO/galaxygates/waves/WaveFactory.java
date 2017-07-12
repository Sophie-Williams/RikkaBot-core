package com.rikkabot.rikkabotcore.dao.galaxygates.waves;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Wave factory.
 * =============
 *
 * Factory for the `galaxygates_waves` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class WaveFactory extends Factory<Wave>
{
    /**
     * Wave builder.
     */
    @Getter(lazy = true)
    private WaveBuilder _builder = new WaveBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "galaxygates_waves";

    /**
     * Returns all the waves belonging to a certain gate.
     *
     * @param id GalaxyGate ID.
     */
    public Map<Integer, Wave> byGalaxyGateID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("galaxygates_id", id +"");

        return super.where(params);
    }
}
