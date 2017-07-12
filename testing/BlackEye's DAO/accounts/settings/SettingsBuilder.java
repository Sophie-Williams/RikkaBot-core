package com.rikkabot.rikkabotcore.dao.accounts.settings;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Settings builder.
 * =================
 *
 * Builds `Settings` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class SettingsBuilder implements Builder<Settings>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Settings build(JSONObject json)
    {
        Settings settings = null;

        try {
            // > B-but Manu-kun!
            // > C-can't you just call `json.getBoolean()`??
            // Thing is that booleans are stored as bits in the database,
            // therefor, the json from the API server will contain 1/0 instead of true/false
            // `json.getBoolean()` over 1/0 will throw an exception.
            settings = new Settings(
                    json.getInt("id"),
                    json.getString("boosten").equals("1"),
                    json.getString("dsplyDamage").equals("1"),
                    json.getString("dsplyAllLas").equals("1"),
                    json.getString("dsplyExplo").equals("1"),
                    json.getString("dsplyPlayerName").equals("1"),
                    json.getString("dsplyFirmIcon").equals("1"),
                    json.getString("dsplyAlphaBg").equals("1"),
                    json.getString("ignoreRES").equals("1"),
                    json.getString("ignoreBOX").equals("1"),
                    json.getString("convertGates").equals("1"),
                    json.getString("convertOppo").equals("1"),
                    json.getString("soundOnOff").equals("1"),
                    json.getString("bgmusicOnOff").equals("1"),
                    json.getString("dsplyStatus").equals("1"),
                    json.getString("dsplyBubble").equals("1"),
                    json.getInt("selectedLaser"),
                    json.getInt("selectedRocket"),
                    json.getString("dsplyDigits").equals("1"),
                    json.getString("dsplyChat").equals("1"),
                    json.getString("dsplyDrones").equals("1"),
                    json.getString("showStarsystem").equals("1"),
                    json.getString("ignoreCARGO").equals("1"),
                    json.getString("ignoreHostileCARGO").equals("1"),
                    json.getString("autochangeAmmo").equals("1"),
                    json.getString("enableFastBuy").equals("1")
            );
        } catch(Exception e) {
            Console.println("Couldn't build settings!");
            Console.debug(e.getMessage());
        }

        return settings;
    }
}
