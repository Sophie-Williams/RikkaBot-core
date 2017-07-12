package com.rikkabot.rikkabotcore.dao.accounts.ships;

import com.manulaiko.tabitha.utils.Point;
import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Ship builder.
 * =============
 *
 * Builds `Ship` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShipBuilder implements Builder<Ship>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Ship build(JSONObject json)
    {
        Ship ship = null;

        try {
            JSONArray positionJSON = json.getJSONArray("position");
            Point     position     = new Point(positionJSON.getInt(0), positionJSON.getInt(1));


            ship = new Ship(
                json.getInt("id"),
                json.getInt("ships_id"),
                json.getInt("maps_id"),
                position,
                json.getInt("health"),
                json.getInt("shield"),
                json.getInt("nanohull"),
                json.getInt("gfx")
            );
        } catch(Exception e) {
            Console.println("Couldn't build ship for `"+ json.toString() +"`!");
            Console.println(e.getMessage());
        }

        return ship;
    }
}
