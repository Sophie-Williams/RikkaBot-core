package com.rikkabot.rikkabotcore.dao.accounts;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;
import com.rikkabot.rikkabotcore.dao.accounts.configurations.ConfigurationFactory;
import com.rikkabot.rikkabotcore.dao.accounts.galaxygates.GalaxyGateFactory;
import com.rikkabot.rikkabotcore.dao.accounts.hangars.HangarFactory;
import com.rikkabot.rikkabotcore.dao.accounts.items.ItemFactory;
import com.rikkabot.rikkabotcore.dao.accounts.settings.SettingsFactory;
import com.rikkabot.rikkabotcore.dao.accounts.ships.ShipFactory;

/**
 * Account factory.
 * ================
 *
 * Factory for the `accounts` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class AccountFactory extends Factory<Account>
{
    /////////////////////
    // Start factories //
    /////////////////////

    /**
     * GalaxyGates factory.
     */
    @Getter(lazy = true)
    private GalaxyGateFactory _galaxygates = new GalaxyGateFactory();

    /**
     * Items factory.
     */
    @Getter(lazy = true)
    private ItemFactory _items = new ItemFactory();

    /**
     * Ships factory.
     */
    @Getter(lazy = true)
    private ShipFactory _ships = new ShipFactory();

    /**
     * Hangars factory.
     */
    @Getter(lazy = true)
    private HangarFactory _hangars = new HangarFactory();

    /**
     * Configurations factory.
     */
    @Getter(lazy = true)
    private ConfigurationFactory _configurations = new ConfigurationFactory();

    /**
     * Settings factory.
     */
    @Getter(lazy = true)
    private SettingsFactory _settings = new SettingsFactory();

    ///////////////////
    // End factories //
    ///////////////////

    /**
     * Account builder.
     */
    @Getter(lazy = true)
    private AccountBuilder _builder = new AccountBuilder();

    /**
     * Returns API endpoint.
     *
     * @return API server endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts";

    /**
     * Finds and returns all the accounts of a given clan.
     *
     * @param id Clan ID.
     *
     * @return List of accounts that belongs to the clan.
     */
    public Map<Integer, Account> byClanID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("clans_id", id +"");

        return super.where(params);
    }
}
