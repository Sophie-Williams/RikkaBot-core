package com.rikkabot.rikkabotcore.dao.clans;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Clan builder.
 * =============
 *
 * Builds `Clan` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ClanBuilder implements Builder<Clan>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Clan build(JSONObject json)
    {
        Clan clan = null;

        try {
            clan = new Clan(
                    json.getInt("id"),
                    json.getInt("accounts_id"),
                    json.getString("tag"),
                    json.getString("name")
            );
        } catch(Exception e) {
            Console.println("Couldn't build clan for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return clan;
    }
}
