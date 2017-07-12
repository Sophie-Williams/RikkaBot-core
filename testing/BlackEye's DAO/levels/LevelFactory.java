package com.rikkabot.rikkabotcore.dao.levels;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Level factory.
 * ==============
 *
 * Factory for the `levels` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class LevelFactory extends Factory<Level>
{
    /**
     * Level builder.
     */
    @Getter(lazy = true)
    private LevelBuilder _builder = new LevelBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "levels";
}
