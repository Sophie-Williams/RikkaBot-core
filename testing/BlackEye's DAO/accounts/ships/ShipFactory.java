package com.rikkabot.rikkabotcore.dao.accounts.ships;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Ship factory.
 * =============
 *
 * Factory for the `accounts_ships` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class ShipFactory extends Factory<Ship>
{
    /**
     * Ship builder.
     */
    @Getter(lazy = true)
    private ShipBuilder _builder = new ShipBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_ships";
}
