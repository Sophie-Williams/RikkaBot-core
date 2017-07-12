package com.rikkabot.rikkabotcore.dao.maps.portals;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.manulaiko.tabitha.utils.Point;
import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Portal builder.
 * ===============
 *
 * Builds `Portal` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class PortalBuilder implements Builder<Portal>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Portal build(JSONObject json)
    {
        Portal portal = null;

        try {
            JSONArray positionJSON       = json.getJSONArray("position");
            JSONArray targetPositionJSON = json.getJSONArray("position");

            Point position       = new Point(positionJSON.getInt(0), positionJSON.getInt(1));
            Point targetPosition = new Point(targetPositionJSON.getInt(0), targetPositionJSON.getInt(1));

            portal = new Portal(
                    json.getInt("id"),
                    json.getInt("levels_id"),
                    json.getInt("maps_id"),
                    position,
                    json.getInt("target_maps_id"),
                    targetPosition,
                    (json.getInt("is_visible") == 1),
                    (json.getInt("is_working") == 1),
                    json.getInt("gfx")
            );
        } catch(Exception e) {
            Console.println("Couldn't build portal for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return portal;
    }
}
