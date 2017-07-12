package com.rikkabot.rikkabotcore.dao.factions;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Faction builder.
 * ================
 *
 * Builds `Faction` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class FactionBuilder implements Builder<Faction>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Faction build(JSONObject json)
    {
        Faction faction = null;

        try {
            JSONArray lowPositionJSON  = json.getJSONArray("low_maps_position");
            JSONArray highPositionJSON = json.getJSONArray("high_maps_position");

            Point lowPosition  = new Point(lowPositionJSON.getInt(0), lowPositionJSON.getInt(1));
            Point highPosition = new Point(highPositionJSON.getInt(0), highPositionJSON.getInt(1));

            faction = new Faction(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("tag"),
                    json.getString("description"),
                    (json.getInt("is_public") == 1),
                    json.getInt("low_maps_id"),
                    lowPosition,
                    json.getInt("high_maps_id"),
                    highPosition
            );
        } catch(Exception e) {
            Console.println("Couldn't build faction for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return faction;
    }
}
