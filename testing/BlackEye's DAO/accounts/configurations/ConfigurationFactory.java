package com.rikkabot.rikkabotcore.dao.accounts.configurations;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Configuration factory.
 * ======================
 *
 * Factory for the `accounts_configurations` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class ConfigurationFactory extends Factory<Configuration>
{
    /**
     * Configuration builder.
     */
    @Getter(lazy = true)
    private ConfigurationBuilder _builder = new ConfigurationBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_configurations";

    /**
     * Returns all configurations of given hangar.
     *
     * @param id Hangar ID.
     *
     * @return Configurations of hangar.
     */
    public Map<Integer,Configuration> byHangarsID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("accounts_hangars_id", id +"");

        return super.where(params);
    }
}
