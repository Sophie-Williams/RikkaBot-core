package com.rikkabot.rikkabotcore.dao.accounts;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Account builder.
 * ================
 *
 * Builds `Account` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class AccountBuilder implements Builder<Account>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Account build(JSONObject json)
    {
        Account account = null;

        try {
            // User might not have a clan.
            int clans_id = 0;
            if(!json.isNull("clans_id")) {
                clans_id = json.getInt("clans_id");
            }

            account = new Account(
                    json.getInt("id"),
                    json.getString("session_id"),
                    json.getInt("levels_id"),
                    json.getInt("factions_id"),
                    json.getInt("accounts_hangars_id"),
                    clans_id,
                    json.getInt("ranks_id"),
                    json.getString("name"),
                    json.getString("ban_date"),
                    json.getString("premium_date"),
                    json.getInt("uridium"),
                    json.getLong("credits"),
                    json.getDouble("jackpot"),
                    json.getLong("experience"),
                    json.getInt("honor")
            );
        } catch(Exception e) {
            Console.println("Couldn't build account for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());

            e.printStackTrace();
        }

        return account;
    }
}
