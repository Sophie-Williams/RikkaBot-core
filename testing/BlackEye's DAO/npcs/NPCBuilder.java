package com.rikkabot.rikkabotcore.dao.npcs;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;
import com.rikkabot.rikkabotcore.dao.rewards.RewardBuilder;
import org.json.JSONObject;

/**
 * NPC builder.
 * ==============
 *
 * Builds `NPC` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class NPCBuilder implements Builder<NPC>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public NPC build(JSONObject json)
    {
        NPC npc = null;

        try {
            npc = new NPC(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getInt("health"),
                    json.getInt("shield"),
                    json.getDouble("shield_absorption"),
                    json.getInt("damage"),
                    json.getInt("speed"),
                    RewardBuilder.build(json.getJSONArray("rewards")),
                    json.getInt("gfx"),
                    json.getInt("ai")
            );
        } catch(Exception e) {
            Console.println("Couldn't build NPC for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return npc;
    }
}
