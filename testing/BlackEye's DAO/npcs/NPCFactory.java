package com.rikkabot.rikkabotcore.dao.npcs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * NPC factory.
 * ============
 *
 * Factory for the `npcs` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class NPCFactory extends Factory<NPC>
{
    /**
     * NPC builder.
     */
    @Getter(lazy = true)
    private NPCBuilder _builder = new NPCBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "npcs";
}
