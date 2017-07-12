package com.rikkabot.rikkabotcore.dao.accounts.settings;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Settings factory.
 * =================
 *
 * Factory for the `accounts_settings` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class SettingsFactory extends Factory<Settings>
{
    /**
     * Builder object.
     */
    @Getter(lazy = true)
    private SettingsBuilder _builder = new SettingsBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_settings";

    /**
     * Finds and returns the settings by its account ID.
     *
     * @param id Account ID.
     *
     * @return Account's settings.
     */
    public Settings findByAccountID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("accounts_id", id +"");

        Map<Integer, Settings> results = super.where(params);

        if(results.size() <= 0) {
            return null;
        }

        Map.Entry<Integer, Settings> settings = results.entrySet().iterator().next();

        super._cache.put(settings.getKey(), settings.getValue());

        return settings.getValue();
    }
}
