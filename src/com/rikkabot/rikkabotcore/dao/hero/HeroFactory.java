package com.rikkabot.rikkabotcore.dao.hero;

import com.rikkabot.rikkabotcore.Main;
import com.rikkabot.rikkabotcore.dao.Factory;
import com.rikkabot.rikkabotcore.net.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

public class HeroFactory extends Factory<Hero> {
    /**
     * Login to the game and returns an instance of `Hero`.
     *
     * @param username Username of the account.
     * @param password Password of the account.
     *
     * @return An instance of hero.
     */
    public Hero login(String username, String password) {
        JSONObject response = Main.endpoint.find("httpLogin")
                                    .execute(new JSONArray(new String[] {
                                            username,
                                            password
                                        }
                                    ));

        return this.fromResponse(response);
    }

    /**
     * Builds and returns a hero object from an endpoint response.
     *
     * @param response Endpoint response.
     *
     * @return Hero instance for response.
     */
    public Hero fromResponse(JSONObject response) {
        if (response.getBoolean("isError")) {
            return null;
        }

        JSONObject r = response.getJSONObject("result");

        try {
            return this._buildFromResult(r);
        } catch (Exception e) {
            Console.println("Couldn't build Hero!");
            Console.print(e);
        }

        return null;
    }

    /**
     * Builds and returns a Hero instance from a response result.
     *
     * @param result Response result.
     *
     * @return Hero for response result.
     */
    private Hero _buildFromResult(JSONObject result) {
        int id = Integer.parseInt(result.getString("userID"));

        if (super.byID(id) != null) {
            return super.byID(id);
        }

        Hero h = new Hero(
                result.getString("username"),
                result.getString("password"),
                result.getString("sessionID"),
                result.getString("host"),
                id,
                Integer.parseInt(result.getString("mapID")),
                new HttpClient(),
                null
        );

        super.add(h.userId(), h);

        return h;
    }
}
