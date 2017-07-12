package com.rikkabot.rikkabotcore.dao.accounts.configurations;

import com.manulaiko.blackeye.launcher.GameManager;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ConfigurationBuilder implements Builder<Configuration>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Configuration build(JSONObject json)
    {
        Configuration configuration = null;

        try {
            JSONArray lasers     = json.getJSONArray("lasers");
            JSONArray hellstorms = json.getJSONArray("hellstorms");
            JSONArray generators = json.getJSONArray("generators");
            JSONArray extras     = json.getJSONArray("extras");
            JSONArray drones     = json.getJSONArray("drones");

            configuration = new Configuration(
                    json.getInt("id"),
                    json.getInt("configuration_id"),
                    GameManager.accounts.items().find(lasers),
                    GameManager.accounts.items().find(hellstorms),
                    GameManager.accounts.items().find(generators),
                    GameManager.accounts.items().find(extras),
                    GameManager.accounts.items().find(drones)
            );
        } catch(Exception e) {
            Console.println("Couldn't build configuration for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return configuration;
    }
}
