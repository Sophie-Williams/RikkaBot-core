package com.rikkabot.rikkabotcore.dao.collectables;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;
import com.rikkabot.rikkabotcore.dao.rewards.RewardBuilder;
import org.json.JSONObject;

/**
 * Collectable builder.
 * ====================
 *
 * Builds `Collectable` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class CollectableBuilder implements Builder<Collectable>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Collectable build(JSONObject json)
    {
        Collectable collectable = null;

        try {
            collectable = new Collectable(
                    json.getInt("id"),
                    json.getInt("gfx"),
                    json.getInt("type"),
                    json.getString("name"),
                    RewardBuilder.build(json.getJSONArray("rewards"))
            );
        } catch(Exception e) {
            Console.println("Couldn't build collectable for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return collectable;
    }
}
