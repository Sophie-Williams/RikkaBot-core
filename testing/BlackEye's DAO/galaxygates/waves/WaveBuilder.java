package com.rikkabot.rikkabotcore.dao.galaxygates.waves;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.npcs.NPC;
import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Wave builder.
 * =============
 *
 * Builds `Wave` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class WaveBuilder implements Builder<Wave>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Wave build(JSONObject json)
    {
        Wave wave = null;

        try {
            wave = new Wave(
                    json.getInt("id"),
                    this._getRound(json.getJSONArray("starting")),
                    this._getRound(json.getJSONArray("continuing")),
                    this._getRound(json.getJSONArray("last")),
                    json.getInt("time"),
                    json.getInt("npcs_left")
            );
        } catch(Exception e) {
            Console.println("Couldn't build wave for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return wave;
    }

    /**
     * Builds and returns an spawn round.
     *
     * @param json Round json.
     *
     * @return List of NPCs to spawn in the round.
     */
    private List<NPC> _getRound(JSONArray json)
    {
        List<NPC> npcs = new ArrayList<>();

        for(int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            int amount = obj.getInt("amount");
            int id     = obj.getInt("npcs_id");

            npcs.addAll(GameManager.npcs.list(id, amount));
        }

        return npcs;
    }
}
