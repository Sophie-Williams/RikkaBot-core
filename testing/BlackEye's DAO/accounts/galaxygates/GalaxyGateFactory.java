package com.rikkabot.rikkabotcore.dao.accounts.galaxygates;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * GalaxyGate factory.
 * ===================
 *
 * Factory for the `accounts_galaxygates` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class GalaxyGateFactory extends Factory<GalaxyGate>
{
    /**
     * GalaxyGate builder.
     */
    @Getter(lazy = true)
    private GalaxyGateBuilder _builder = new GalaxyGateBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_galaxygates";

    /**
     * Returns all galaxygates of given account.
     *
     * @param id Account ID.
     *
     * @return GalaxyGates of account.
     */
    public Map<Integer,GalaxyGate> byAccountsID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("accounts_id", id +"");

        return super.where(params);
    }
}
