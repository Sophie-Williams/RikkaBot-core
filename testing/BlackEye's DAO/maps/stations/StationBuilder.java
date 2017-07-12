package com.rikkabot.rikkabotcore.dao.maps.stations;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.manulaiko.tabitha.utils.Point;
import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Station builder.
 * ================
 *
 * Builds `Station` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class StationBuilder implements Builder<Station>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Station build(JSONObject json)
    {
        Station station = null;

        try {
            JSONArray positionJSON = json.getJSONArray("position");

            Point position = new Point(positionJSON.getInt(0), positionJSON.getInt(1));

            station = new Station(
                    json.getInt("id"),
                    position,
                    json.getInt("maps_id"),
                    json.getInt("factions_id")
            );
        } catch(Exception e) {
            Console.println("Couldn't build station for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return station;
    }
}
