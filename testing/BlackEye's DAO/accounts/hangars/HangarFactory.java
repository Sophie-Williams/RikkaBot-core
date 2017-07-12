package com.rikkabot.rikkabotcore.dao.accounts.hangars;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Hangar factory.
 * ===============
 *
 * Factory for the `accounts_hangars` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class HangarFactory extends Factory<Hangar>
{
    /**
     * Hangar builder.
     */
    @Getter(lazy = true)
    private HangarBuilder _builder = new HangarBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_hangars";

    /**
     * Returns all hangars of given account.
     *
     * @param id Account ID.
     *
     * @return Hangars of account.
     */
    public Map<Integer, Hangar> byAccountsID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("accounts_id", id +"");


        return super.where(params);
    }
}
