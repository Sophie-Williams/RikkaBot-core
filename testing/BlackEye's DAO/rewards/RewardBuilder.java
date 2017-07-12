package com.rikkabot.rikkabotcore.dao.rewards;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

/**
 * Reward builder.
 * ===============
 *
 * Builder for all rewards objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class RewardBuilder
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    public static List<Reward> build(JSONArray json)
    {
        List<Reward> rewards = new ArrayList<>();

        for(int i = 0; i < json.length(); i++) {
            Reward r = RewardBuilder._build(json.getJSONObject(i));

            if(r != null) {
                rewards.add(r);
            }
        }

        return rewards;
    }

    /**
     * Builds and returns a Reward object.
     *
     * @param json Reward json.
     *
     * @return Reward object for `json`.
     */
    private static Reward _build(JSONObject json)
    {
        Reward reward = null;

        try {
            reward = new Reward(
                    json.getInt("items_id"),
                    json.getDouble("probability"),
                    json.getInt("amount")
            );
        } catch(Exception e) {
            Console.println("Couldn't build rewards for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return reward;
    }
}
