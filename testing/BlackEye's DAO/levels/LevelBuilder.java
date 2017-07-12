package com.rikkabot.rikkabotcore.dao.levels;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;

import org.json.JSONObject;

/**
 * Level builder.
 * ==============
 *
 * Builds `Level` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class LevelBuilder implements Builder<Level>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Level build(JSONObject json)
    {
        Level level = null;

        try {
            level = new Level(
                    json.getInt("id"),
                    json.getLong("account"),
                    json.getInt("drone"),
                    json.getInt("pet"),
                    json.getDouble("damage"),
                    json.getDouble("shield")
            );
        } catch(Exception e) {
            Console.println("Couldn't build level for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return level;
    }
}
