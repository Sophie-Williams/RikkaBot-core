package com.rikkabot.rikkabotcore.dao.accounts.hangars;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class HangarBuilder implements Builder<Hangar>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Hangar build(JSONObject json)
    {
        Hangar hangar = null;

        try {
            hangar = new Hangar(
                    json.getInt("id"),
                    json.getInt("accounts_ships_id"),
                    json.getInt("accounts_configurations_id")
            );
        } catch(Exception e) {
            Console.println("Couldn't build hangar for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return hangar;
    }
}
